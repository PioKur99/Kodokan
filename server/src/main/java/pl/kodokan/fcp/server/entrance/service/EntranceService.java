package pl.kodokan.fcp.server.entrance.service;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import pl.kodokan.fcp.server.entrance.model.Entrance;
import pl.kodokan.fcp.server.entrance.model.Package;
import pl.kodokan.fcp.server.entrance.model.PackageFreeze;
import pl.kodokan.fcp.server.entrance.repo.EntranceRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class EntranceService {

    private final EntranceRepository entranceRepository;

    public EntranceService(EntranceRepository entranceRepository) {
        this.entranceRepository = entranceRepository;
    }

    Entrance save(Entrance entrance) { return entranceRepository.save(entrance); }

    public Long addEntrance(Entrance entrance) {

        LocalDateTime now = LocalDateTime.now();
        //Czy karnet jest oplacony? //TODO: Czy karnet jest uprzywilejowany?
        if (!entrance.getPackg().isPaid()) {

        }
        //Czy obecna data jest przed koncowa?
        if (now.isBefore(entrance.getPackg().getEndDateTime())) {

        }
        //Czy przekroczono limit wejsc?
        if (entrance.getPackg().getPackageType().getEntranceLimit() == 0) {

        }
        //Jezeli karnet jest zawieszony to odwieszamy ew. zmieniajac odpowiednie daty
        //Zakladam, ze moze byc tylko jedno zawieszenie z czasem przed now
        entrance.getPackg().getFreezes().stream()
                .filter(n -> n.getEndDateTime().isAfter(now))
                .findFirst().get().setEndDateTime(now);

        //TODO: Alternatywnie, gdyby powyzsze nie dzialalo zgodnie z oczekiwaniami
        /*List<PackageFreeze> freezes = entrance.getPackg().getFreezes();
        for (PackageFreeze freeze : freezes) {
            if (now.isBefore(freeze.getEndDateTime())) {
                freeze.setEndDateTime(now);
                break;
            }
        }*/

        //Czy jest karnet z pusta data? Jezeli wiecej niz jeden - bierzemy dowolna jednorazowke
        //TODO: To jeszcze do zweryfikowania jak otrzymam info zwrotne od Lukasza
        Optional<Package> packageWithEmptyEndDate = entrance.getCustomer().getPackages().stream()
                .filter(n -> n.getEndDateTime() == null && n.getPackageType().getEntranceLimit() == 1)
                .findFirst();

        if (!packageWithEmptyEndDate.isPresent()) {
            //TODO: Wyjatek - wejscie nie mozliwe (lub wchodzi na multisport/ok system nie wiem o co chodzi)
        } else {
            packageWithEmptyEndDate.get().setEndDateTime(now);
        }

        //Zarejestrowano wejscie, przypisania dzieja sie w maperze
        return save(entrance).getId();
    }
}
