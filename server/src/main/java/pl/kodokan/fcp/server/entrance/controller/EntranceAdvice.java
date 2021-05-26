package pl.kodokan.fcp.server.entrance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.entrance.exception.NoEntranceWithGivenId;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class EntranceAdvice {

    @ExceptionHandler(NoValidPackageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noValidPackageException(NoValidPackageException ex) {return "No valid package!";}

    @ExceptionHandler(NoEntranceWithGivenId.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String noEntranceWithGivenId(NoEntranceWithGivenId ex) {return "Entrance with given ID does not exist!";}
}
