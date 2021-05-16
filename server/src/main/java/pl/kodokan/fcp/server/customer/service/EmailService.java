package pl.kodokan.fcp.server.customer.service;

import pl.kodokan.fcp.server.userDetails.entity.Email;

public interface EmailService {
    boolean isCorrect(String email);
}
