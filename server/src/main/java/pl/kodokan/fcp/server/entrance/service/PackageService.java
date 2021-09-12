package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.customer.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.model.Family;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.entrance.controller.PackageDetails;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.dto.PackageRequest;
import pl.kodokan.fcp.server.entrance.dto.PackageResponse;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;
import pl.kodokan.fcp.server.entrance.exception.PackageCannotBeDeleteException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotFoundException;
import pl.kodokan.fcp.server.entrance.exception.*;
import pl.kodokan.fcp.server.entrance.repo.*;
import pl.kodokan.fcp.server.entrance.mapper.PackageMapper;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class PackageService {

    @Autowired
    PackageMapper mapper;

    @Autowired
    PackageTypeRepository packageTypeRepository;
    private final PackageRepository packageRepository;
    private final CustomerRepository customerRepository;
    private final EntranceService entranceService;

    public Customer findCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotPresent());
        return customer;
    }

    public PackageType findPackageById(Long id){
        PackageType packageType = packageTypeRepository.findById(id).orElseThrow(()->new InvalidPackageTypeId());
        return packageType;
    }

    private List<Package> filterByEndDate(List<Package> packageList, LocalDateTime endDate) {
        return packageList
                .stream()
                .filter(aPackage -> !aPackage.getEndDateTime().isBefore(endDate))
                .collect(Collectors.toList());

    }

    private List<Package> filterByPackageName(List<Package> packageList, String packageName) {
        return packageList
                .stream()
                .filter(aPackage -> aPackage.getPackageType().getName().equals(packageName))
                .collect(Collectors.toList());
    }

    private boolean isCustomerInPackage(List<Customer> customers, String fullName) {
        for (Customer customer : customers) {
            String name = customer.getUserData().getFirstName() + " " + customer.getUserData().getLastName();
            if (name.equals(fullName))
                return true;
        }
        return false;
    }

    private List<Package> filterByFullName(List<Package> packageList, String fullName) {
        return packageList
                .stream()
                .filter(aPackage ->
                        isCustomerInPackage(aPackage.getCustomers(), fullName)
                ).collect(Collectors.toList());
    }

    private List<Package> filterByIsActive(List<Package> packageList, Boolean isActive) {
        return packageList.stream()
                .filter(aPackage -> !aPackage.getEndDateTime().isBefore(LocalDateTime.now()))
                .filter(aPackage -> aPackage.isPaid())
                .filter(aPackage -> aPackage.getPackageType().getEntranceLimit() > aPackage.countEntrances())
                .collect(Collectors.toList());
    }

    public List<PackageResponse> findAllWithParameters(PackageRequest packageRequest) {
        List<Package> packageList = packageRepository.findAll();
        if (packageList != null) {
            if (packageRequest.getFullName().isPresent() && !packageRequest.getFullName().isEmpty())
                packageList = filterByFullName(packageList, packageRequest.getFullName().get());
            if (packageRequest.getPackageName().isPresent() && !packageRequest.getPackageName().isEmpty())
                packageList = filterByPackageName(packageList, packageRequest.getPackageName().get());
            if (packageRequest.getEndDate().isPresent() && packageRequest.getEndDate() != null)
                packageList = filterByEndDate(packageList, packageRequest.getEndDate().get());
            if (packageRequest.getIsActive().isPresent() && packageRequest.getIsActive() != null)
                packageList = filterByIsActive(packageList, packageRequest.getIsActive().get());
            if (packageList.size() != 0)
                return new PackageResponse().toDto(packageList);
            else
                throw new PackageNotFoundException("There are no records in the database that meet the conditions");
        } else {
            throw new PackageNotFoundException("There are no records in the database");
        }
    }

    private Package findById(Long id){
        return packageRepository.findById(id).orElseThrow(()->new PackageNotPresentException());
    }

    public List<PackageDTO> getPartnerSystemPackages(boolean bool){
        return packageTypeRepository.findAllByWithPartnerSystem(bool).stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Long payForTicket(Long id){
        Package p = findById(id);
        if(p.isPaid()){
            throw new PackageAlreadyPaidException();
        }
        p.pay();
        return id;
    }

    @Transactional
    public Long deleteById(Long packageId) {
        Package pack = packageRepository.findById(packageId)
                .orElseThrow(() -> new PackageNotFoundException("There are no package with provided id: " + packageId));
        for (Customer customer : pack.getCustomers()) {
            deletePackagesFromCustomer(customer, packageId);
        }
        packageRepository.deleteById(packageId);
        return packageId;
    }

    @Transactional
    public void deletePackagesFromCustomer(Customer customer, Long packageId) {
        Optional<Package> packg = customer
                .getPackages()
                .stream()
                .filter(pack -> pack.getId().equals(packageId))
                .findFirst();
        if (packg.isPresent()) {
            if (packg.get().countEntrances() != 0) {
                throw new PackageCannotBeDeleteException("Number of entrances in customer with id: " + customer.getId() + " is not equal 0");
            }
            customer.deletePackageById(packg.get());
            customerRepository.save(customer);
        }
    }

    public PackageDetails getPackage(Long packageId) {
        Optional<Package> aPackage = packageRepository.findById(packageId);

        if (aPackage.isEmpty()) {
            throw new NoValidPackageException();
        }
        PackageType packageType = aPackage.get().getPackageType();

        PackageDetails packageDetails = PackageDetails.builder()
                .name(packageType.getName())
                .price(packageType.getPrice())
                .isPaid(aPackage.get().isPaid())
                .endDate(aPackage.get().getEndDateTime() == null ? "" : aPackage.get().getEndDateTime().toString())
                .purchaseDate(aPackage.get().getPurchaseDateTime().toString())

                .duration(Duration.between(LocalDateTime.now(), aPackage.get().getEndDateTime() == null ?
                        LocalDateTime.MAX : aPackage.get().getEndDateTime()).toDays())
                .build();

        Integer usedEntries = 0;
        List<Customer> owners = aPackage.get().getCustomers();
        for (Customer customer : owners) {
            usedEntries += entranceService.countAll(customer.getId(), aPackage.get().getId());
            packageDetails.addOwner(customer.getId(), customer.getUserData().getFirstName(), customer.getUserData().getLastName());
        }

        packageDetails.setUsedEntries(usedEntries);
        packageDetails.setRemainingEntries(packageType.getEntranceLimit() - usedEntries);

        return packageDetails;
    }

    public Long createPackage(Long customerID, Long packageTypeID){
        Customer customer = findCustomerById(customerID);
        PackageType packageType = findPackageById(packageTypeID);
        List<Package> packageWithNoEndDate = packageRepository.findPackagesWithNoEndDate(customerID);
        if(packageWithNoEndDate != null && !packageWithNoEndDate.isEmpty() && packageType.getEntranceLimit() > 1){
            throw new TwoTimePackagesException();
        }

        Package newPackage = new Package();
        newPackage.addCustomer(customer);
        newPackage.setPackageType(packageType);
        newPackage.setPaid(false);
        newPackage.setEndDateTime(null);
        newPackage.setPurchaseDateTime(LocalDateTime.now());

        Family customersFamily;


        packageRepository.save(newPackage);
        return newPackage.getId();
    }
}
