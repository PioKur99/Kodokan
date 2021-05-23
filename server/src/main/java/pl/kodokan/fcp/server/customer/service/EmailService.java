package pl.kodokan.fcp.server.customer.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;

@Service
public class EmailService  {

    public boolean isCorrect(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
