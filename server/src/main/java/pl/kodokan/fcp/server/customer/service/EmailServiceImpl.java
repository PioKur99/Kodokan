package pl.kodokan.fcp.server.customer.service;

import org.apache.commons.validator.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.userDetails.entity.Email;

@Service
public class EmailServiceImpl implements EmailService {
    @Override
    public boolean isCorrect(Email email) {
        EmailValidator validator = EmailValidator.getInstance();
        boolean temp = validator.isValid(email.getValue());
        return temp;
    }
}
