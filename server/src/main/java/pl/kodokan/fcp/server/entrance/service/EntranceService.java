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
import java.time.Duration;
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

    Entrance save(Entrance entrance) { return entranceRepository.save(entrance); }

    public Long addEntrance(EntranceDto entranceDto) {

        LocalDateTime now = LocalDateTime.now();

        Package toEntrance;

        Entrance entrance = entranceMapper.toEntity(entranceDto);

        Optional<Customer> customer = customerRepository.findById(entrance.getCustomer().getId());
        if (customer.isPresent() == false) {
            throw new NullPointerException("No customer with given ID!");
        }

        //jednorazowka - mozesz miec kilka, to taki karnet, ktory ma limit wejsc = 1, ten ma priorytet
        //normalny - w danym okresie czasu tylko jeden aktywny karnet

        List<Package> customerPackages = customer.get().getPackages();

        List<Package> customerValidPackages = customerPackages.stream()
                                .filter(n -> n.isPaid() == true || n.getPackageType().isPaymentMandatory() == false)
                                .filter(n -> n.getEndDateTime().isAfter(now))
        //Czy przekroczono limit wejsc? Z taska bedzie do wywolania request ktory zwroci ile bylo wejsc
                                //.filter(n -> n.getPackageType().getEntranceLimit() > tegoCoMiZwrociCosTam)
                                .collect(Collectors.toList());



        if (customerValidPackages.isEmpty() == true) {

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
                toEntrance.setEndDateTime(toEntrance.getEndDateTime().minusDays(dateDiffrance.toDays() ));
                packageFreeze.setEndDateTime(now);


                //TODO: Alternatywnie, gdyby powyzsze nie dzialalo zgodnie z oczekiwaniami
                /*List<PackageFreeze> freezes = entrance.getPackg().getFreezes();
                for (PackageFreeze freeze : freezes) {
                    if (now.isBefore(freeze.getEndDateTime())) {
                        freeze.setEndDateTime(now);
                        break;
                    }
                }*/

            } else {
                toEntrance = customerValidPackages.get(0);
            }
        }

        entrance.setPackg(toEntrance);

        //Zarejestrowano wejscie, przypisania dzieja sie w maperze
        return save(entrance).getId();
    }
}
