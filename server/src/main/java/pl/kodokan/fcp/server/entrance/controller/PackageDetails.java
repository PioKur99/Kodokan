package pl.kodokan.fcp.server.entrance.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PackageDetails {
    private String name;
    private BigDecimal price;
    private boolean isPaid;
    private LocalDateTime endDate;
    private LocalDateTime purchaseDate;
    private Integer usedEntries;
    private Integer remainingEntries;
    private Long duration;
    private List<SimpleCustomerDetails> owners;

    private class SimpleCustomerDetails {
        private String customerId;
        private String firstName;
        private String lastName;
    }

}
