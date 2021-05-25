package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.service.CustomerService;
import pl.kodokan.fcp.server.entrance.dto.PackageRequest;
import pl.kodokan.fcp.server.entrance.dto.PackageResponse;
import pl.kodokan.fcp.server.entrance.exception.PackageCannotBeDeleteException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotFoundException;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PackageService {

    private final PackageRepository packageRepository;
    private final CustomerRepository customerRepository;

    private List<Package> filterByEndDate(List<Package> packageList, LocalDateTime endDate) {
        return packageList
                .stream()
                .filter(aPackage -> !aPackage.getEndDateTime().isBefore(endDate))
                .collect(Collectors.toList());

    }
    private List<Package> filterByPackageName(List<Package> packageList, String packageName){
        return packageList
                .stream()
                .filter(aPackage -> aPackage.getPackageType().getName().equals(packageName))
                .collect(Collectors.toList());
    }
    private boolean isCustomerInPackage(List<Customer> customers,String fullName){
        for(Customer customer : customers){
            String name = customer.getUserData().getFirstName() + " " + customer.getUserData().getLastName();
            if(name.equals(fullName))
                return true;
        }
        return false;
    }
    private List<Package> filterByFullName(List<Package> packageList, String fullName){
        return packageList
                .stream()
                .filter(aPackage ->
                        isCustomerInPackage(aPackage.getCustomers(),fullName)
                ).collect(Collectors.toList());
    }
    private List<Package> filterByIsActive(List<Package> packageList, Boolean isActive) {
        return packageList.stream()
                .filter(aPackage -> !aPackage.getEndDateTime().isBefore(LocalDateTime.now()))
                .filter(aPackage -> aPackage.isPaid())
                .filter(aPackage -> aPackage.getPackageType().getEntranceLimit()>aPackage.countEntrances())
                .collect(Collectors.toList());
    }

    public List<PackageResponse> findAllWithParameters(PackageRequest packageRequest) {
        List<Package> packageList = packageRepository.findAll();
        if(packageList != null){
            if(packageRequest.getFullName().isPresent())
                packageList = filterByFullName(packageList,packageRequest.getFullName().get());
            if(packageRequest.getPackageName().isPresent())
                packageList = filterByPackageName(packageList,packageRequest.getPackageName().get());
            if(packageRequest.getEndDate().isPresent())
                packageList = filterByEndDate(packageList,packageRequest.getEndDate().get());
            if(packageRequest.getIsActive().isPresent())
                packageList = filterByIsActive(packageList,packageRequest.getIsActive().get());
            if(packageList.size() != 0)
            return new PackageResponse().toDto(packageList);
            else
                throw new PackageNotFoundException("There are no records in the database that meet the conditions");
        }
        else{
            throw new PackageNotFoundException("There are no records in the database");
        }
    }


    @Transactional
    public Long deleteById(Long packageId) {
        Package pack = packageRepository.findById(packageId)
                .orElseThrow(() -> new PackageNotFoundException("There are no package with provided id: "+ packageId ));
        for(Customer customer : pack.getCustomers()){
           deletePackagesFromCustomer(customer,packageId);
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
        if(packg.isPresent()) {
            if (packg.get().countEntrances() != 0) {
                throw new PackageCannotBeDeleteException("Number of entrances in customer with id: " + customer.getId() + " is not equal 0");
            }
            customer.deletePackageById(packg.get());
            customerRepository.save(customer);
        }
    }
}
