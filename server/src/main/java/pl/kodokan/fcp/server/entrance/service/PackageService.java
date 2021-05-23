package pl.kodokan.fcp.server.entrance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.entrance.exception.InvalidPackageTypeId;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;
import pl.kodokan.fcp.server.entrance.repo.PackageTypeRepository;

@Service
public class PackageService {
    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PackageTypeRepository packageTypeRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Customer findCustomerById(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new CustomerNotPresent());
        return customer;
    }

    public PackageType findPackageById(Long id){
        PackageType packageType = packageTypeRepository.findById(id).orElseThrow(()->new InvalidPackageTypeId());
        return packageType;
    }

    public Long createPackage(Long customerID, Long packageTypeID){
        Customer customer = findCustomerById(customerID);
        PackageType packageType = findPackageById(packageTypeID);
        Package packageWithNoEndDate = packageRepository.findPackagesWithNoEndDate(customerID);
//        if(packageWithNoEndDate != null){
//            throw new
//        }
        return 0L;
    }
}
