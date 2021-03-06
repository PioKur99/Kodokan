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
        dto.setErrorMsg("This customer already has a family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProvidedIDsAreTheSameException.class)
    ResponseEntity<CardStateAdviceDTO> theSameCustomerID(ProvidedIDsAreTheSameException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(ProvidedIDsAreTheSameException.class));
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

    @ExceptionHandler(RoleInFamilyTakenException.class)
    ResponseEntity<CardStateAdviceDTO> roleInFamilyTaken(RoleInFamilyTakenException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(RoleInFamilyTakenException.class));
        dto.setErrorMsg("This family already has a member with provided role");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HomoAlertException.class)
    ResponseEntity<CardStateAdviceDTO> homoAlert(HomoAlertException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(HomoAlertException.class));
        dto.setErrorMsg("Marriage can only be between a man and a woman");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotAddGrandParentsException.class)
    ResponseEntity<CardStateAdviceDTO> cannotAddGrandparents(CannotAddGrandParentsException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(CannotAddGrandParentsException.class));
        dto.setErrorMsg("Cannot add grandparents to family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SiblingsException.class)
    ResponseEntity<CardStateAdviceDTO> siblingsEx(SiblingsException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(SiblingsException.class));
        dto.setErrorMsg("Cannot add siblings of family's parent. If you want to create family with these two customers, please delete customers from theirs families, create a new one and add them as children");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CannotAddGrandkidsException.class)
    ResponseEntity<CardStateAdviceDTO> cannotAddGrandkids(CannotAddGrandkidsException ex){
        CardStateAdviceDTO dto = new CardStateAdviceDTO();
        dto.setErrorClass(String.valueOf(CannotAddGrandkidsException.class));
        dto.setErrorMsg("Cannot add grandchildren to family");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
