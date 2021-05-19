package pl.kodokan.fcp.server.helper;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.kodokan.fcp.server.dto.CardStateAdviceDTO;
import pl.kodokan.fcp.server.exception.CustomerNotPresent;
import pl.kodokan.fcp.server.exception.MaximumCardState;
import pl.kodokan.fcp.server.exception.MinimumCardState;

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
}
