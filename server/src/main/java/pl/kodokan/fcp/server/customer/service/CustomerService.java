package pl.kodokan.fcp.server.customer.service;

import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.entity.Customer;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.repository.CustomerRepository;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository repo;
    private final PeselService peselService;
    private final EmailService emailService;

    private static final double SCALE = 0.5;

    public CustomerService(CustomerRepository customerRepository, PeselService peselService, EmailService emailService) {
        this.repo = customerRepository;
        this.peselService = peselService;
        this.emailService = emailService;
    }

    Customer save(Customer customer) {
        return repo.save(customer);
    }

    public Long addCustomer(Customer customer) {
        //todo: walidacja in progress

        if (!peselService.isCorrect(customer.getUserDetails().getIdentity_number()))
            throw new IncorrectPeselException();
        if (peselService.isGenderCorrect(customer.getUserDetails().getIdentity_number()) != customer.getUserDetails().isGender())
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

    //TODO: metoda do usuniecia, zapis na czas testow kompresji/wykrywania twarzy ze zdjecia
    public Customer getCustomer(Long id) {
        Optional<Customer> customer = repo.findById(id);

        if (customer.isPresent()) {
            try {
                ByteArrayInputStream bis = new ByteArrayInputStream(customer.get().getUserDetails().getImage());
                BufferedImage bufferedImage = ImageIO.read(bis);
                ImageIO.write(bufferedImage, "jpg", new File("output.jpg"));
            } catch (IOException exception) {
                System.out.println("Error while reading/writing image!");
            }
            return customer.get();
        } else {
            return null;
        }
    }
}
