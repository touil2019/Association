package com.Association.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class Evenement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evenement")
    private Long id;

    private String theme;

    private Date dateEvenement;

    private Integer NombreParticipant;

    private Integer nombreParticipantMax;

    private String description;



    @ManyToMany
    @JoinTable( name = "membre_evenement_associations",
            joinColumns = @JoinColumn( name = "id_evenement" ),
            inverseJoinColumns = @JoinColumn( name = "id_membre" ) )
    private Collection<Membre> membres;

    @OneToMany(mappedBy = "evenement", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Reservation> reservations;




    public Evenement() {
    }

    public Evenement(String theme, Date dateEvenement, Integer nombreParticipantMax, String description) {
        this.theme = theme;
        this.dateEvenement = dateEvenement;
        this.nombreParticipantMax = nombreParticipantMax;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public Integer getNombreParticipant() {
        return NombreParticipant;
    }

    public void setNombreParticipant(Integer nombreParticipant) {
        NombreParticipant = nombreParticipant;
    }

    public Integer getNombreParticipantMax() {
        return nombreParticipantMax;
    }

    public void setNombreParticipantMax(Integer nombreParticipantMax) {
        this.nombreParticipantMax = nombreParticipantMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Membre> getMembres() {
        return membres;
    }

    public void setMembres(Collection<Membre> membres) {
        this.membres = membres;
    }

   public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "evenementCulturelles{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", dateEvenement=" + dateEvenement +
                ", NombreParticipant=" + NombreParticipant +
                ", nombreParticipantMax=" + nombreParticipantMax +
                ", description='" + description + '\'' +
                ", membres=" + membres +
                 '}';
    }
}