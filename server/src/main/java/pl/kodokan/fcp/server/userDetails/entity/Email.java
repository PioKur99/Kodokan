package pl.kodokan.fcp.server.userDetails.entity;


import javax.persistence.Embeddable;

@Embeddable
public class Email {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}