package br.com.poc.entity;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "PREFIX")
public class Prefix implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_PREFIX")
    @SequenceGenerator(name = "SEQ_PREFIX", sequenceName = "SEQ_PREFIX", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PREFIX")
    private Integer id;

    @Column(name = "DS_PREFIX", length = 255)
    private String description;

}
