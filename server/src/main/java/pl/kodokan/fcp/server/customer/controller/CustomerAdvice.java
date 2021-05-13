package pl.kodokan.fcp.server.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.exception.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Set;

@RestControllerAdvice
public class CustomerAdvice {

    @ExceptionHandler(IncorrectPeselException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectPeselHandler(IncorrectPeselException ex) {return "Pesel is incorrect!";}

    @ExceptionHandler(IncorrectGenderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectGenderHandler(IncorrectGenderException ex) {return "Gender is incorrect!";}

    @ExceptionHandler(RepeatedPeselException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String repeatedPeselHandler(RepeatedPeselException ex) {return "Pesel already exists!";}

    @ExceptionHandler(RepeatedEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String repeatedEmailHandler(RepeatedEmailException ex) {return "Email already exists!";}

    @ExceptionHandler(IncorrectEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String incorrectEmailException(IncorrectEmailException ex) {return "Email is incorrect!";}

    //TODO: jezeli w oficjalnych wersjach nie będzie adnotacji notNull to łapanie tego wyjątku okazać się może zbędne
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String constraintViolationException(ConstraintViolationException ex) {

        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            message.append(violation.getMessage().concat("; "));
        }

        return message.toString();
    }

    @ExceptionHandler(ErrorReadingImageException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String errorReadingImageException(ErrorReadingImageException ex) {return "Error while reading customer image!";}
}
