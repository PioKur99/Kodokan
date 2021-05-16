package pl.kodokan.fcp.server.customer.service;

import pl.kodokan.fcp.server.userDetails.entity.Pesel;

public interface PeselService {
    boolean isCorrect(Pesel pesel);
    boolean isMale(Pesel pesel);
}
