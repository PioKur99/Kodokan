package pl.kodokan.fcp.server.common.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class BaseEntity {

    //po co ten klucz główny? xd
    /*@Getter @Setter
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;*/
}
