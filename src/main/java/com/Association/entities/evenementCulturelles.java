package com.Association.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class evenementCulturelles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;

    private Date dateEvenement;

    private Integer NombreParticipant;

    private Integer NombreParticipantMax;

    private String description;

    @ManyToOne
    @JoinColumn(name = "id_Membre")
    private Membre membre;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<reservation> reservations;


    public evenementCulturelles() {
    }

    public evenementCulturelles(Long id, String theme, Date dateEvenement, Integer nombreParticipant, Integer nombreParticipantMax, String description, Membre membre) {
        this.id = id;
        this.theme = theme;
        this.dateEvenement = dateEvenement;
        NombreParticipant = nombreParticipant;
        NombreParticipantMax = nombreParticipantMax;
        this.description = description;
        this.membre = membre;
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
        return NombreParticipantMax;
    }

    public void setNombreParticipantMax(Integer nombreParticipantMax) {
        NombreParticipantMax = nombreParticipantMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "evenementCulturellesRepository{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", dateEvenement=" + dateEvenement +
                ", NombreParticipant=" + NombreParticipant +
                ", NombreParticipantMax=" + NombreParticipantMax +
                ", description='" + description + '\'' +
                ", membre=" + membre +
                '}';
    }
}
