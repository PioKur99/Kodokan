package pl.kodokan.fcp.server.customer.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.userDetails.entity.Email;

@Service
public class EmailService implements EmailService {
    @Override
    public boolean isCorrect(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
