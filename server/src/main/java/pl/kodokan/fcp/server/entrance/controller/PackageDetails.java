package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class PackageDetails {
    private String name;
    private BigDecimal price;
    private boolean isPaid;
    private String endDate;
    private String purchaseDate;
    private Integer usedEntries;
    private Integer remainingEntries;
    private Long duration;
    private List<SimpleCustomerDetails> owners;

    public void addOwner(Long customerId, String firstName, String lastName) {
        if(owners == null)
            owners = new ArrayList<>();
        owners.add(new SimpleCustomerDetails(customerId, firstName, lastName));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    private static class SimpleCustomerDetails {
        private final Long customerId;
        private final String firstName;
        private final String lastName;
    }

}
