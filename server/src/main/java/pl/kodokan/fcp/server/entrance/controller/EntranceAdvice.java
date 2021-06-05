package pl.kodokan.fcp.server.entrance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.entrance.exception.EntranceNotFoundException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.entrance.exception.EntranceNotFoundException;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;

@RestControllerAdvice
public class EntranceAdvice {

    @ExceptionHandler(EntranceNotFoundException.class)
    public ResponseEntity<String> handleEntranceNotFoundException(EntranceNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoValidPackageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noValidPackageException(NoValidPackageException ex) {return "No valid package!";}
}
