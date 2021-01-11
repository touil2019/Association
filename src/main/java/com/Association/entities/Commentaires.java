package com.Association.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
public class Commentaires implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_membre")
    private Membre membre;

    private String contenu;

    private Date date;

    public Commentaires() {
    }

    public Commentaires(Long id, Membre membre, String contenu, Date date) {
        this.id = id;
        this.membre = membre;
        this.contenu = contenu;
        this.date = date;
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

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "commentaires{" +
                "id=" + id +
                ", membre=" + membre +
                ", contenu='" + contenu + '\'' +
                ", date=" + date +
                '}';
    }
}
