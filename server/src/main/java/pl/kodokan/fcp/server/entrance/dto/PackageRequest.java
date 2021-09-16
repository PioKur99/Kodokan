package pl.kodokan.fcp.server.entrance.dto;

import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
public class PackageRequest {
    private String fullName;
    private LocalDateTime endDate;
    private String packageName;
    //private Boolean isPaid;
    //private Boolean isActive;

    public Optional<String> getFullName(){
        return Optional.ofNullable(fullName);
    }
    public Optional<String> getPackageName(){
        return Optional.ofNullable(packageName);
    }
    public Optional<LocalDateTime> getEndDate(){
        return Optional.ofNullable(endDate);
    }
    //public Optional<Boolean> getIsPaid(){
    //   return Optional.ofNullable(isPaid);
    //}
    //public Optional<Boolean> getIsActive(){
    //    return Optional.ofNullable(isActive);
    //}
}
