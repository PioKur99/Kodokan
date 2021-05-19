package pl.kodokan.fcp.server.entrance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.exception.PackageAlreadyPaidException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotPresent;
import pl.kodokan.fcp.server.entrance.mapper.PackageMapper;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.PackageRepository;
import pl.kodokan.fcp.server.entrance.repo.PackageTypeRepository;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class TicketsService {

    @Autowired
    PackageTypeRepository packageTypeRepository;
    @Autowired
    PackageRepository packageRepository;

    @Autowired
    PackageMapper mapper;

    private Package findById(Long id){
        return packageRepository.findById(id).orElseThrow(()->new PackageNotPresent());
    }

    public List<PackageDTO> getPartnerSystemPackages(){
        List<PackageType> res = packageTypeRepository.findAllByWithPartnerSystem(true);
        List<PackageDTO> dto = new ArrayList<>();
        for(PackageType o : res){
            dto.add(mapper.toDTO(o));
        }
        return dto;
    }

    public Long payForTicket(Long id){
        Package p = findById(id);
        if(p.isPaid()){
            throw new PackageAlreadyPaidException();
        }
        p.setPaid(true);
        return id;
    }


}
