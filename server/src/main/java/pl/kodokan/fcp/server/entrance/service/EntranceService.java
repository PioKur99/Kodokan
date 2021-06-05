package pl.kodokan.fcp.server.entrance.service;

import lombok.AllArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.customer.model.Customer;
import pl.kodokan.fcp.server.customer.repo.CustomerRepository;
import pl.kodokan.fcp.server.entrance.controller.EntranceDto;
import pl.kodokan.fcp.server.entrance.controller.EntranceMapperImpl;
import pl.kodokan.fcp.server.entrance.exception.NoValidPackageException;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageFreeze;
import pl.kodokan.fcp.server.entrance.repo.EntranceRepository;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class EntranceService {

    private final EntranceRepository entranceRepository;
    private final EntranceMapperImpl entranceMapper;
    private final CustomerRepository customerRepository;

    Entrance save(Entrance entrance) {
        return entranceRepository.save(entrance);
    }

    public List<Long> findAll(Long customerId, Long packageId) {
        final Optional<List<Long>> entrancesId = entranceRepository.findAllByCustomerIdAndPackgId(customerId, packageId);
        if (!entrancesId.get().isEmpty()) {
            return entrancesId.get();
        } else {
            throw new EntranceNotFoundException("Entrance with customerId " + customerId + " packageId " + packageId + " doesn't exist.");
        }
    }

    public Integer countAll(Long customerId, Long packageId) {
        return entranceRepository.countAllByCustomerIdAndPackgId(customerId, packageId)
                .orElseThrow(() -> new EntranceNotFoundException("There is no entrances with customerId " + customerId + " packageId " + packageId + " in db."));
    }

    public Long addEntrance(EntranceDto entranceDto) {

        LocalDateTime now = LocalDateTime.now();

        Package toEntrance;

        Entrance entrance = entranceMapper.toEntity(entranceDto);

        Optional<Customer> customer = customerRepository.findById(entrance.getCustomer().getId());
        if (customer.isEmpty()) {
            throw new NullPointerException("No customer with given ID!");
    private EntranceRepository entranceRepository;
    public List<Long> findAll(Long customerId, Long packageId) {
        final Optional<List<Long>> entrancesId = entranceRepository.findAllByCustomerIdAndPackgId(customerId, packageId);
        if(!entrancesId.get().isEmpty()){
            return entrancesId.get();
        }

        //jednorazowka - mozesz miec kilka, to taki karnet, ktory ma limit wejsc = 1, ten ma priorytet
        //normalny - w danym okresie czasu tylko jeden aktywny karnet

        List<Package> customerPackages = customer.get().getPackages();

        List<Package> customerValidPackages = customerPackages.stream()
                .filter(n -> n.isPaid() || !n.getPackageType().isPaymentMandatory())
                .filter(n -> n.getEndDateTime().isAfter(now))
                .filter(n -> n.getPackageType().getEntranceLimit() > countAll(entranceDto.getCustomerId(), n.getId()))
                .collect(Collectors.toList());


        if (customerValidPackages.isEmpty()) {

            //Czy jest karnet z pusta data?
            //jezeli karnet jest z pusta data to ustawiamy date koncowa na dzien odbicia + validityDays
            Optional<Package> packageWithEmptyEndDate = customerPackages.stream()
                    .filter(n -> n.getEndDateTime() == null)
                    .findFirst();

            if (packageWithEmptyEndDate.isPresent()) {
                packageWithEmptyEndDate.get().setEndDateTime(now.plusDays(packageWithEmptyEndDate.get()
                        .getPackageType().getValidityDays()));
            } else {
                throw new NoValidPackageException();
            }

            toEntrance = packageWithEmptyEndDate.get();

        } else {

            //Sprawdzam, czy jest jakis okresowy, jak jest to na niego wchodzi, jak nie wchodzi na dowolna jednorazowke
            if (customerValidPackages.stream().anyMatch(n -> n.getPackageType().getEntranceLimit() > 1)) {

                toEntrance = customerValidPackages.stream().filter(n -> n.getPackageType().getEntranceLimit() > 1).findFirst().get();

                //Jezeli karnet jest zawieszony to odwieszamy ew. zmieniajac odpowiednie daty
                //Zakladam, ze moze byc tylko jedno zawieszenie z czasem przed now
                //roznice miedzy data koncowa zawieszenia a aktualną odejmuje od daty waznosci karnetu
                PackageFreeze packageFreeze = toEntrance.getFreezes().stream()
                        .filter(n -> n.getEndDateTime().isAfter(now))
                        .findFirst().get();

                Duration dateDiffrance = Duration.between(now, packageFreeze.getEndDateTime());
                toEntrance.setEndDateTime(toEntrance.getEndDateTime().minusDays(dateDiffrance.toDays()));
                packageFreeze.setEndDateTime(now);


                //TODO: Alternatywnie, gdyby powyzsze nie dzialalo zgodnie z oczekiwaniami
                /*List<PackageFreeze> freezes = entrance.getPackg().getFreezes();
                for (PackageFreeze freeze : freezes) {
                    if (now.isBefore(freeze.getEndDateTime())) {
                        freeze.setEndDateTime(now);
                        break;
                    }
                }*/
        else{
            throw new EntranceNotFoundException("Entrance with customerId " + customerId + " packageId " + packageId + " doesn't exist.");
        }
    }

            } else {
                toEntrance = customerValidPackages.get(0);
            }
        }

        entrance.setPackg(toEntrance);

        //Zarejestrowano wejscie, przypisania dzieja sie w maperze
        return save(entrance).getId();
    public Integer countAll(Long customerId, Long packageId) {
        return entranceRepository.countAllByCustomerIdAndPackgId(customerId, packageId)
                .orElseThrow(() -> new EntranceNotFoundException("There is no entrances with customerId " + customerId + " packageId " + packageId + " in db."));
    }

    public List<EntranceWithDetails> getFilteredEntrances(EntranceFilter entranceFilter) {
        List<Entrance> toFilter = entranceRepository.findAll();

        if (toFilter.isEmpty()) {
            return Collections.emptyList();
        }

        //TODO: Pewnie mozna jakos ladniej zrobic filtrowanie, jeszcze do przemyslenia
        if (!entranceFilter.getName().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getCustomer().getUserData().getFirstName().contains(entranceFilter.getName()))
                    .collect(Collectors.toList());
        if (!entranceFilter.getSurname().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getCustomer().getUserData().getLastName().contains(entranceFilter.getSurname()))
                    .collect(Collectors.toList());
        ;
        if (!entranceFilter.getDate().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getDateTime().toString().equals(entranceFilter.getDate()))
                    .collect(Collectors.toList());
        ;
        if (!entranceFilter.getPackageName().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getPackg().getPackageType().getName().contains(entranceFilter.getPackageName()))
                    .collect(Collectors.toList());
        ;
        if (!entranceFilter.getTraining().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getTraining().getTrainingName().getName().contains(entranceFilter.getTraining()))
                    .collect(Collectors.toList());
        ;
        if (!entranceFilter.getCardId().isEmpty())
            toFilter = toFilter.stream()
                    .filter(n -> n.getCustomer().getClubCard().getId().toString().equals(entranceFilter.getCardId()))
                    .collect(Collectors.toList());

        List<EntranceWithDetails> toReturn = new ArrayList<>();
        for (Entrance entrance : toFilter) {
            EntranceWithDetails entranceWithDetails = EntranceWithDetails.builder()
                    .name(entrance.getCustomer().getUserData().getFirstName())
                    .surname(entrance.getCustomer().getUserData().getLastName())
                    .date(entrance.getDateTime().toLocalDate().toString())
                    .hour(entrance.getDateTime().toLocalTime().toString())
                    .packageName(entrance.getPackg().getPackageType().getName())
                    .training(entrance.getTraining().getTrainingName().getName())
                    .build();
            toReturn.add(entranceWithDetails);
        }

        return toReturn;
    }

    public Long deleteEntrance(Long toDelete) {
        if (toDelete == null)
            throw new EntranceNotFoundException("ID cannot be null!");

        Optional<Entrance> entranceToDelete = entranceRepository.findById(toDelete);
        if (entranceToDelete.isPresent())
            entranceRepository.deleteById(toDelete);
        else
            throw new EntranceNotFoundException("No entrance with given ID!");

        return toDelete;
    }
}