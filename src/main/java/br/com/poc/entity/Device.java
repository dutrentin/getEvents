package br.com.poc.entity;


import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "DEVICE")
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_DEVICE")
    @SequenceGenerator(name = "SEQ_DEVICE", sequenceName = "SEQ_DEVICE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DEVICE")
    private Integer id;

    @Column(name = "DS_DEVICE", length = 255)
    private String description;

}
