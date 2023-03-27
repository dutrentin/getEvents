package br.com.poc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//@Entity
//@Table(name = "EVENT")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_EVENT")
    @SequenceGenerator(name = "SEQ_EVENT", sequenceName = "SEQ_EVENT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENT")
    private Integer id;

    @Column(name = "DT_INSTANT_CREATE")
    private Date instantCreateEvent;

    @Column(name = "DS_PAYLOAD", length = 500)
    private String payload;



}
