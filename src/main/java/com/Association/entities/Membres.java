package com.Association.entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Membres implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Membre")
    private Long id;
}
