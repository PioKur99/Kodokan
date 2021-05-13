package pl.kodokan.fcp.server.customer.model;

import lombok.Getter;

@Getter
public enum Discipline {

    KRAV_MAGA("Krav Maga"),
    MUAY_THAI("Muay Thai"),
    KICK_BOXING("Kick boxing"),
    BJJ("Brazylijskie Jiu Jitsu"),
    MMA("Mieszane Sztuki Walki"),
    BOKS("Boks"),
    GRAPPLING("Grappling"),
    JUDO("Judo");

    private final String name;

    Discipline(String name) {
        this.name = name;
    }
}
