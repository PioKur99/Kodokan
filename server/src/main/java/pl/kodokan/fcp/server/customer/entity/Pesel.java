package pl.kodokan.fcp.server.customer.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Pesel {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
