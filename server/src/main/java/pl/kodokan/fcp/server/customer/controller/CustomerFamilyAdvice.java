package pl.kodokan.fcp.server.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.dto.CardStateAdviceDTO;
import pl.kodokan.fcp.server.customer.exception.CustomerAlreadyInFamilyException;
import pl.kodokan.fcp.server.customer.exception.CustomerDoesntHaveFamilyException;
import pl.kodokan.fcp.server.customer.exception.TheSameCustomerIDException;

@RestControllerAdvice
public class CustomerFamilyAdvice {
    @ExceptionHandler(CustomerAlreadyInFamilyException.class)
    ResponseEntity<CardStateAdviceDTO> customerAlreadyInFamily(CustomerAlreadyInFamilyException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(CustomerAlreadyInFamilyException.class));
        dto.setErrorMsg("This customer is already in some other family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TheSameCustomerIDException.class)
    ResponseEntity<CardStateAdviceDTO> theSameCustomerID(TheSameCustomerIDException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(TheSameCustomerIDException.class));
        dto.setErrorMsg("Provided two same IDs");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerDoesntHaveFamilyException.class)
    ResponseEntity<CardStateAdviceDTO> customerDoesntHaveFamily(CustomerDoesntHaveFamilyException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(CustomerDoesntHaveFamilyException.class));
        dto.setErrorMsg("Customer doesn't have family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
