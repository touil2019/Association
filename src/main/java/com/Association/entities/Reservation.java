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

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_membre")
    private Membre membre;

    @ManyToOne
    @JoinColumn(name = "id_evenement")
    private Evenement evenement;

    public Reservation() {
    }

    public Reservation(Date dateReservation, String description, Membre membre, Evenement evenement) {

        this.dateReservation = dateReservation;
        this.description = description;
        this.membre = membre;
        this.evenement = evenement;
    }



    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  public Membre getMembre() {
        return membre;
    }


    public void setMembre(Membre membre) {
        this.membre = membre;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
