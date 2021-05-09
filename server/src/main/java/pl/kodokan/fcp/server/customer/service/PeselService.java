package pl.kodokan.fcp.server.customer.service;

import pl.kodokan.fcp.server.customer.exception.IncorrectPeselException;
import pl.kodokan.fcp.server.userDetails.entity.Pesel;

public interface PeselService {
    //todo: czy warto informowac uzytkownika dlaczego numer pesel jest niepoprawny? Np niepoprawny msc albo suma kontrolna
    boolean isCorrect(Pesel pesel);
    boolean isGenderCorrect(Pesel pesel);
}
