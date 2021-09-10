package pl.kodokan.fcp.server.entrance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.entrance.exception.PackageCannotBeDeleteException;
import pl.kodokan.fcp.server.entrance.exception.PackageNotFoundException;

@RestControllerAdvice
public class PackageAdvice {
    @ExceptionHandler(PackageNotFoundException.class)
    public ResponseEntity<String> handlePackageNotFoundException(PackageNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(PackageCannotBeDeleteException.class)
    public ResponseEntity<String> handlePackageCannotBeDeleteException(PackageCannotBeDeleteException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
