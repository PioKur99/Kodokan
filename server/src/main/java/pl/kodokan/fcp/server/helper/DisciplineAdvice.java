package pl.kodokan.fcp.server.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.dto.DisciplineAdviceDTO;
import pl.kodokan.fcp.server.exception.CustomerNotExist;

@RestControllerAdvice
public class DisciplineAdvice {

    @ExceptionHandler(CustomerNotExist.class)
    public ResponseEntity<DisciplineAdviceDTO> handleCustomerNotExist(CustomerNotExist exception){
        final DisciplineAdviceDTO adviceDTO = new DisciplineAdviceDTO();
        adviceDTO.setErrorMsg("Customer does not exist in db");
        adviceDTO.setLocalizedMsg(exception.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<DisciplineAdviceDTO> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        final DisciplineAdviceDTO adviceDTO = new DisciplineAdviceDTO();
        adviceDTO.setErrorMsg(exception.getMessage());
        adviceDTO.setLocalizedMsg(exception.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }
}
