package pl.kodokan.fcp.server.entrance.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.kodokan.fcp.server.entrance.dto.AdviceDTO;
import pl.kodokan.fcp.server.entrance.exception.InvalidPackageTypeId;
import pl.kodokan.fcp.server.entrance.exception.PackageAlreadyPaidException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotPresent;

@ControllerAdvice
public class TicketsAdvice {
    @ExceptionHandler(PackageNotPresent.class)
    public ResponseEntity<AdviceDTO> packageNotPresent(PackageNotPresent ex){
        AdviceDTO dto = new AdviceDTO();
        dto.setMessage("Package with this ID doesn't exists in database");
        dto.setLocalization(String.valueOf(PackageNotPresent.class));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PackageAlreadyPaidException.class)
    public ResponseEntity<AdviceDTO> packageAlreadyPaid(PackageAlreadyPaidException ex){
        AdviceDTO dto = new AdviceDTO();
        dto.setMessage("Package is already paid");
        dto.setLocalization(String.valueOf(PackageAlreadyPaidException.class));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPackageTypeId.class)
    public ResponseEntity<AdviceDTO> invalidPackageTypeId(InvalidPackageTypeId ex){
        AdviceDTO dto = new AdviceDTO();
        dto.setMessage("Provided invalid package type ID");
        dto.setLocalization(String.valueOf(InvalidPackageTypeId.class));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
