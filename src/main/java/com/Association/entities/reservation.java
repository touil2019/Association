package com.Association.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMembre;

    private Date dateReservation;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_Membre")
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "id_EvenementCulturelles")
    private evenementCulturelles evenementCulturelles;


}
