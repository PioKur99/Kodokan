package pl.kodokan.fcp.server.customer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.controller.CustomerDto;
import pl.kodokan.fcp.server.customer.controller.CustomerMapper;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.repository.CustomerRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;
    private final PeselService peselService;
    private final EmailService emailService;
    private final CustomerMapper customerMapper;

    private static final double SCALE = 0.5;

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    @Transactional
    public Long createCustomer(CustomerDto dto) {

        Customer customer = customerMapper.toEntity(dto);

        if (!peselService.isCorrect(customer.getUserDetails().getIdentity_number()))
            throw new IncorrectPeselException();
        if (peselService.isMale(customer.getUserDetails().getIdentity_number()) != customer.getUserDetails().isGender())
            throw new IncorrectGenderException();
        if (repo.findAllPesels().stream().anyMatch(n -> n.getValue().equals(customer.getUserDetails().getIdentity_number().getValue())))
            throw new RepeatedPeselException();
        if (!emailService.isCorrect(customer.getUserDetails().getEmail()))
            throw new IncorrectEmailException();
        if (repo.findAllEmails().stream().anyMatch(n -> n.getValue().equals((customer.getUserDetails().getEmail().getValue()))))
            throw new RepeatedEmailException();

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(customer.getUserDetails().getImage());
            BufferedImage originalImage = ImageIO.read(bis);

            int scaledWidth = (int) Math.round(originalImage.getWidth() * SCALE);
            int scaledHeight = (int) Math.round(originalImage.getHeight() * SCALE);

            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            graphics2D.dispose();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(scaledImage, "jpg", baos);

            customer.getUserDetails().setImage(baos.toByteArray());

        } catch (IOException ex) {
            throw new ErrorReadingImageException();
        }

        //TODO: Gdyby podjście z ustawianiem pól na null podczas mapowania się nie przyjęło (aktualnie działa), tutaj będą one ustawiane ręcznie na zasadzie np: customer.setCard_id(null);
        return save(customer).getId();
    }
}
