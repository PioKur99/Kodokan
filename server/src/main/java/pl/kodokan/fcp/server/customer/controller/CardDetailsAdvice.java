package pl.kodokan.fcp.server.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.exception.*;

@RestControllerAdvice
public class CardDetailsAdvice {

    @ExceptionHandler(CustomerNotPresent.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String customerNotFoundHandler(CustomerNotPresent ex) {return "Customer not found!";}

    @ExceptionHandler(MissingDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String missingDataHandler(MissingDataException ex) {return "Missing data exception!";}
}
