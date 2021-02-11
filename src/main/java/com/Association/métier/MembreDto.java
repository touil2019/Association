package com.Association.métier;


import com.Association.entities.Membre;
import com.Association.security.RoleEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MembreDto {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("pseudo")
    private String pseudo;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
    @JsonProperty("nom")
    private String nom;
    @JsonProperty("prenom")
    private String prenom;
    @JsonProperty("role")
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public MembreDto(Membre membre) {
     this.id= membre.getId();
     this.nom= membre.getNom();
     this.prenom= membre.getPrenom();
     this.pseudo= membre.getPseudo();
     this.email= membre.getEmail();
     if(membre.getRoles().contains(RoleEnum.ROLE_ACTIF)){
         this.role= "Actif";
     }else if(membre.getRoles().contains(RoleEnum.ROLE_BIENFAITEUR)){
         this.role= "Bienfaiteur";
     } else if(membre.getRoles().contains(RoleEnum.ROLE_HONORAIRE)) {
         this.role= "Honoraire";
     }
    }
    public MembreDto() {

    }
}
