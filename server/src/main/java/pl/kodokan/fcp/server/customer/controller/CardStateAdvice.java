package pl.kodokan.fcp.server.customer.controller;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.customer.dto.CardStateAdviceDTO;
import pl.kodokan.fcp.server.customer.exception.*;

@RestControllerAdvice
public class CardStateAdvice {
    @ExceptionHandler(CustomerNotPresent.class)
    ResponseEntity<CardStateAdviceDTO> customerNotPresent(CustomerNotPresent ex) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("Customer with this ID doesn't exist in database");
        adviceDTO.setErrorClass(ex.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MaximumCardState.class)
    ResponseEntity<CardStateAdviceDTO> maximumCardStateHandler(MaximumCardState exception) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("Card is already received, so you can't increment it");
        adviceDTO.setErrorClass(exception.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MinimumCardState.class)
    ResponseEntity<CardStateAdviceDTO> minimumCardStateHandler(MinimumCardState exception) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("Card state of this customer is unpaid, so you can't lower it");
        adviceDTO.setErrorClass(exception.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConversionFailedException.class)
    ResponseEntity<CardStateAdviceDTO> conversationFailed(ConversionFailedException ex) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("Make sure to use correct enum values");
        adviceDTO.setErrorClass(ex.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CardIDTaken.class)
    ResponseEntity<CardStateAdviceDTO> cardIDTaken(CardIDTaken ex) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("This card ID is already taken");
        adviceDTO.setErrorClass(ex.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClubCardAlreadyPresentException.class)
    ResponseEntity<CardStateAdviceDTO> cardAlreadyPresent(ClubCardAlreadyPresentException ex) {
        final CardStateAdviceDTO adviceDTO = new CardStateAdviceDTO();
        adviceDTO.setErrorMsg("This customer already has club card");
        adviceDTO.setErrorClass(ex.getClass().getName());
        return new ResponseEntity<>(adviceDTO, HttpStatus.BAD_REQUEST);
    }
}
