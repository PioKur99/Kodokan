package pl.kodokan.fcp.server.entrance.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.entrance.dto.AdviceDTO;
import pl.kodokan.fcp.server.entrance.exception.PackageAlreadyPaidException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotPresentException;

@RestControllerAdvice
public class PacakageAdvice {
    @ExceptionHandler(PackageNotPresentException.class)
    public ResponseEntity<AdviceDTO> packageNotPresent(PackageNotPresentException ex){
        AdviceDTO dto = new AdviceDTO();
        dto.setMessage("Package with this ID doesn't exists in database");
        dto.setLocalization(String.valueOf(PackageNotPresentException.class));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PackageAlreadyPaidException.class)
    public ResponseEntity<AdviceDTO> packageAlreadyPaid(PackageAlreadyPaidException ex){
        AdviceDTO dto = new AdviceDTO();
        dto.setMessage("Package is already paid");
        dto.setLocalization(String.valueOf(PackageAlreadyPaidException.class));
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
