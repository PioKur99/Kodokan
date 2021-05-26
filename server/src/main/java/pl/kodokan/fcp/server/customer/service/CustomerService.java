package pl.kodokan.fcp.server.customer.service;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static pl.kodokan.fcp.server.user.model.Gender.MALE;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final PeselValidator peselValidator;
    private final CustomerMapper customerMapper;

    private static final double SCALE = 0.5;

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    @Transactional
    public Long createCustomer(CustomerDTO dto) {

        Customer customer = customerMapper.toEntity(dto);
        customer.setMainDiscipline(null);
        customer.setFamily(null);
        customer.setClubCard(null);

        if (!peselValidator.isCorrect(customer.getUserData().getIdentityNumber()))
            throw new IncorrectPeselException();
        if (peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == MALE)
            throw new IncorrectGenderException();
        if (repo.findAllPesels().stream().anyMatch(n -> n.equals(customer.getUserData().getIdentityNumber())))
            throw new RepeatedPeselException();
        if(EmailValidator.getInstance().isValid(customer.getUserData().getEmail()))
            throw new IncorrectEmailException();
        if (repo.findAllEmails().stream().anyMatch(n -> n.equals((customer.getUserData().getEmail()))))
            throw new RepeatedEmailException();

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(customer.getUserData().getImage());
            BufferedImage originalImage = ImageIO.read(bis);

            int scaledWidth = (int) Math.round(originalImage.getWidth() * SCALE);
            int scaledHeight = (int) Math.round(originalImage.getHeight() * SCALE);

            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            graphics2D.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", baos);

            customer.getUserData().setImage(baos.toByteArray());

        } catch (IOException ex) {
            throw new ErrorReadingImageException();
        }

        //TODO: Gdyby podjście z ustawianiem pól na null podczas mapowania się nie przyjęło (aktualnie działa), tutaj będą one ustawiane ręcznie na zasadzie np: customer.setCard_id(null);
        return save(customer).getId();
    }
}
