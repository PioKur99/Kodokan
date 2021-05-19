package pl.kodokan.fcp.server.entrance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.entrance.dto.PackageDTO;
import pl.kodokan.fcp.server.entrance.mapper.PackageMapper;
import pl.kodokan.fcp.server.entrance.model.PackageType;
import pl.kodokan.fcp.server.entrance.repo.PackageTypeRepository;

import java.util.*;

@Service
public class TicketsService {

    @Autowired
    PackageTypeRepository packageTypeRepository;

    @Autowired
    PackageMapper mapper;

    public List<PackageDTO> getPartnerSystemPackages(){
        List<PackageType> res = packageTypeRepository.findAllByWithPartnerSystem(true);
        List<PackageDTO> dto = new ArrayList<>();
        for(PackageType o : res){
            dto.add(mapper.toDTO(o));
        }
        return dto;
    }


}
