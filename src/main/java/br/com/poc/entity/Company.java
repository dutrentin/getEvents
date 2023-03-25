package br.com.poc.entity;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "COMPANY")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_COMPANY")
    @SequenceGenerator(name = "SEQ_COMPANY", sequenceName = "SEQ_COMPANY", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_COMPANY")
    private Integer id;

    @Column(name = "DS_COMPANY", length = 20)
    private String descriptionType;

}
