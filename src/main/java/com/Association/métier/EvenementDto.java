package com.Association.métier;

import javax.persistence.Column;
import java.util.Date;

public class EvenementDto {

    private String theme;

    private String nom;

    private String dateEvenement;

    private Integer nombreParticipantMax;

    private String description;



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(String dateEvenement) {
        this.dateEvenement = dateEvenement;
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
}
