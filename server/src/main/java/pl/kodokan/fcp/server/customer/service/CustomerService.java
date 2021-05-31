package pl.kodokan.fcp.server.customer.service;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.user.model.Address;
import pl.kodokan.fcp.server.user.model.UserData;
import pl.kodokan.fcp.server.user.repo.AddressRepository;
import pl.kodokan.fcp.server.user.repo.UserRepository;

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

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PeselValidator peselValidator;
    private final CustomerMapper customerMapper;

    private static final double SCALE = 0.5;

    Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Long createCustomer(CustomerDTO dto) {

        Customer customer = customerMapper.toEntity(dto);
        Address newAddress = customer.getUserData().getAddress();
        newAddress.setUserData(customer.getUserData());
        addressRepository.save(newAddress);
        UserData userData = new UserData();
        userData.setEmail(dto.getEmail());
        userData.setPassword(dto.getPassword());
        userData.setAddress(customer.getUserData().getAddress());
        userRepository.save(userData);

        customer.setMainDiscipline(null);
        customer.setFamily(null);
        customer.setClubCard(null);

        customer.getUserData().getAddress().setUserData(customer.getUserData());

        if (!peselValidator.isCorrect(customer.getUserData().getIdentityNumber()))
            throw new IncorrectPeselException();
        if (peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == MALE)
            throw new IncorrectGenderException();
        if (customerRepository.findAllPesels().stream().anyMatch(n -> n.equals(customer.getUserData().getIdentityNumber())))
            throw new RepeatedPeselException();
        if(!EmailValidator.getInstance().isValid(customer.getUserData().getEmail()))
            throw new IncorrectEmailException();
        if (customerRepository.findAllEmails().stream().anyMatch(n -> n.equals((customer.getUserData().getEmail()))))
            throw new RepeatedEmailException();

        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(customer.getUserData().getImage());
            BufferedImage originalImage = ImageIO.read(bis);

            if (originalImage != null) {
                int scaledWidth = (int) Math.round(originalImage.getWidth() * SCALE);
                int scaledHeight = (int) Math.round(originalImage.getHeight() * SCALE);

                BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = scaledImage.createGraphics();
                graphics2D.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
                graphics2D.dispose();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(scaledImage, "jpg", baos);

                customer.getUserData().setImage(baos.toByteArray());
            } else {
                customer.getUserData().setImage(null);
            }
        } catch (IOException ex) {
            throw new ErrorReadingImageException();
        }
        return save(customer).getId();
    }
}
