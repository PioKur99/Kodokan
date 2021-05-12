package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;

@Getter
public enum Discipline {

    // TODO Add other disciplines
    KRAV_MAGA("Krav Maga"),
    MUAY_THAI("Muay Thai");

    private final String name;

    Discipline(String name) {
        this.name = name;
    }
}
