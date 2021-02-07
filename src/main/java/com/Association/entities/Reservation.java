package com.Association.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long id;

    private Date dateReservation;

    @ManyToOne
    @JoinColumn(name = "id_membre")
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

    public Reservation() {
    }

    public Reservation(Date dateReservation, Membre membre, Evenement evenement) {

        this.dateReservation = dateReservation;
        this.membre = membre;
        this.evenement = evenement;
    }


    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }


    public Membre getMembre() {
        return membre;
    }


    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
