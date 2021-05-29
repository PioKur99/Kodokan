package pl.kodokan.fcp.server.customer.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.dto.CardStateAdviceDTO;
import pl.kodokan.fcp.server.customer.exception.*;

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

    @ExceptionHandler(GenderDoesntMatchRelationException.class)
    ResponseEntity<CardStateAdviceDTO> genderDoesntMatchRelation(GenderDoesntMatchRelationException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(GenderDoesntMatchRelationException.class));
        dto.setErrorMsg("Customer's gender doesn't fit with provided role in family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleInFamilyTaken.class)
    ResponseEntity<CardStateAdviceDTO> roleInFamilyTaken(RoleInFamilyTaken ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(RoleInFamilyTaken.class));
        dto.setErrorMsg("This family already has a member with provided role");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
