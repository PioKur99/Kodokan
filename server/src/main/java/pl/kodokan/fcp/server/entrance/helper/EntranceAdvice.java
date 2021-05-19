package pl.kodokan.fcp.server.entrance.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.entrance.exception.EntranceNotFoundException;

@RestControllerAdvice
public class EntranceAdvice {

    @ExceptionHandler(EntranceNotFoundException.class)
    public ResponseEntity<String> handleEntranceNotFoundException(EntranceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
