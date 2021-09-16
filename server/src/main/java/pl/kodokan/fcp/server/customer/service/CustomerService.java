package pl.kodokan.fcp.server.customer.service;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.dto.CustomerDTO;
import pl.kodokan.fcp.server.customer.dto.CustomerToEditDTO;
import pl.kodokan.fcp.server.customer.dto.DeleteCustomerDTO;
import pl.kodokan.fcp.server.customer.dto.GetCustomerDTO;
import pl.kodokan.fcp.server.customer.exception.*;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.customer.repo.FamilyRepository;
import pl.kodokan.fcp.server.entrance.model.Package;
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
import java.util.List;

import static pl.kodokan.fcp.server.user.model.Gender.FEMALE;
import static pl.kodokan.fcp.server.user.model.Gender.MALE;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FamilyRepository familyRepository;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final PeselValidator peselValidator;
    private final CustomerMapper customerMapper;
    private final CustomerToEditMapper customerToEditMapper;
    private final GetCustomerMapper getCustomerMapper;
    private final DeleteCustomerMapper deleteCustomerMapper;

    private static final double SCALE = 0.5;

    Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Long createCustomer(CustomerDTO dto) {

        Customer customer = customerMapper.toEntity(dto);

        if (!peselValidator.isCorrect(customer.getUserData().getIdentityNumber()))
            throw new IncorrectPeselException();
        if (peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == FEMALE)
            throw new IncorrectGenderException();
        if (!peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == MALE)
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

        UserData userData = customer.getUserData();
        Address newAddress = customer.getUserData().getAddress();

        userData.setAddress(null);
        userRepository.save(userData);
        newAddress.setUserData(userData);
        addressRepository.save(newAddress);

        customer.setMainDiscipline(null);
        customer.setFamily(null);
        customer.setClubCard(null);

        return save(customer).getId();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotPresent::new);
    }

    @Transactional
    public Long editCustomer(CustomerToEditDTO dto) {

        Customer customer = customerToEditMapper.toEntity(dto);
        Customer customerToUpdate = this.findById(customer.getId());

        if (customer.getUserData().getEmail() != null) {
            //if (customerRepository.findAllEmails().stream().anyMatch(n -> n.equals((customer.getUserData().getEmail()))))
            //    throw new RepeatedEmailException();
            customerToUpdate.getUserData().setEmail(customer.getUserData().getEmail());
        }

        if (customer.getUserData().getFirstName() != null)
            customerToUpdate.getUserData().setFirstName(customer.getUserData().getFirstName());

        if (customer.getUserData().getLastName() != null)
            customerToUpdate.getUserData().setLastName(customer.getUserData().getLastName());

        if (customer.getUserData().getGender() != null)
            customerToUpdate.getUserData().setGender(customer.getUserData().getGender());

        if (customer.getMainDiscipline() != null)
            customerToUpdate.setMainDiscipline(customer.getMainDiscipline());

        if (customer.getUserData().getIdentityNumber() != null) {
            if (!peselValidator.isCorrect(customer.getUserData().getIdentityNumber()))
                throw new IncorrectPeselException();
            if (peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == FEMALE ||
            !peselValidator.isMale(customer.getUserData().getIdentityNumber()) && customer.getUserData().getGender() == MALE)
                throw new IncorrectGenderException();
            //if (customerRepository.findAllPesels().stream().anyMatch(n -> n.equals(customer.getUserData().getIdentityNumber())))
            //    throw new RepeatedPeselException();

            customerToUpdate.getUserData().setIdentityNumber(customer.getUserData().getIdentityNumber());
        }

        if (customer.getUserData().getPhone() != null)
            customerToUpdate.getUserData().setPhone(customer.getUserData().getPhone());

        if (customer.getUserData().getImage() != null) {
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

                    customerToUpdate.getUserData().setImage(baos.toByteArray());
                } else {
                    customer.getUserData().setImage(null);
                }
            } catch (IOException ex) {
                throw new ErrorReadingImageException();
            }
        }

        if (customer.getUserData().getAddress().getCity() != null)
            customerToUpdate.getUserData().getAddress().setCity(customer.getUserData().getAddress().getCity());

        if (customer.getUserData().getAddress().getVoivodeship() != null)
            customerToUpdate.getUserData().getAddress().setVoivodeship(customer.getUserData().getAddress().getVoivodeship());

        if (customer.getUserData().getAddress().getPostalCode() != null)
            customerToUpdate.getUserData().getAddress().setPostalCode(customer.getUserData().getAddress().getPostalCode());

        if (customer.getUserData().getAddress().getAddressLine() != null)
            customerToUpdate.getUserData().getAddress().setAddressLine(customer.getUserData().getAddress().getAddressLine());

        return save(customerToUpdate).getId();
    }

    @Transactional
    public GetCustomerDTO getCustomer(Long id) {
        Customer customer = this.findById(id);
        return getCustomerMapper.toDTO(customer);
    }

    @Transactional
    public DeleteCustomerDTO deleteCustomer(Long id) {
        Customer customer = this.findById(id);
        List<Package> packages = customer.getPackages();

        if (packages != null && !packages.isEmpty()) {
            throw new CannotDeleteCustomerException();
        }

        if (customer.getFamily() != null)
            customer.getFamily().removeChild(customer);
        if(familyRepository.findByMother_Id(id).isPresent())
            familyRepository.findByMother_Id(id).get().setMother(null);
        if(familyRepository.findByFather_Id(id).isPresent())
            familyRepository.findByFather_Id(id).get().setFather(null);


        customerRepository.deleteById(id);
        return deleteCustomerMapper.toDTO(customer);
    }
}
