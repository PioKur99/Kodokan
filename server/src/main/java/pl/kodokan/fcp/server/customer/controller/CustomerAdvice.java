package pl.kodokan.fcp.server.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.exception.IncorrectGenderException;
import pl.kodokan.fcp.server.customer.exception.IncorrectPeselException;

@RestControllerAdvice
public class CustomerAdvice {

    @ExceptionHandler(IncorrectPeselException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectPeselHandler(IncorrectPeselException ex) {return "Pesel is incorrect!";}

    @ExceptionHandler(IncorrectGenderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectGenderHandler(IncorrectGenderException ex) {return "Gender is incorrect!";}
}
