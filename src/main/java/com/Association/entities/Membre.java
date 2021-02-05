package com.Association.entities;

import com.Association.security.BCryptManager;
import com.Association.security.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Membre implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_membre")
    private Long idMembre;

    @Column(name = "pseudo", nullable = false, unique = true)
    @NotEmpty(message = "votre pseudo doit contenir au minimum 2 caractères")
    @Size(min = 2)
    private String pseudo;

    @NotNull
    @Column(name = "password", nullable = false)
    @Size(min = 4)
    private String password;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Veuillez saisir une adresse mail valide")
    private String email;

    @NotNull
    @Size(min = 2)
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotNull
    @Size(min = 2)
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotNull
    private Integer age;

    @NotNull
    private String adresse;


    private String complementAdresse;

    @NotNull
    private String codePostale;

    @NotNull
    private String ville;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)

    @JoinTable(indexes = {
            @Index(name = "INDEX_USER_ROLE", columnList = "id_Membre") }, name = "roles", joinColumns = @JoinColumn(name = "id_Membre"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;


   @ManyToMany
   @JoinTable( name = "membre_evenement_associations",
           joinColumns = @JoinColumn( name = "id_membre" ),
           inverseJoinColumns = @JoinColumn( name = "id_evenement" ) )
    private Collection<Evenement> evenements;

    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Reservation> reservations;

    @OneToMany(mappedBy = "membre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Commentaires> commentaires;

    public Membre() {
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.roles = new HashSet<>();
        this.roles.add(RoleEnum.ROLE_ACTIF);
    }

    public Membre( @NotEmpty(message = "votre pseudo doit contenir au minimum 2 caractères") @Size(min = 2) String pseudo, @NotNull @Size(min = 4) String password, @NotNull @Email(message = "Veuillez saisir une adresse mail valide") String email, @NotNull @Size(min = 2) String nom, @NotNull @Size(min = 2) String prenom, @NotNull Integer age, @NotNull String adresse, @NotNull String complementAdresse, @NotNull String codePostale, @NotNull String ville) {

        this.pseudo = pseudo;
        this.password = BCryptManager.passwordEncoder().encode(password);
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.adresse = adresse;
        this.complementAdresse = complementAdresse;
        this.codePostale = codePostale;
        this.ville = ville;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
    }

    public Long getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(Long id) {
        this.idMembre = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roles = StringUtils
                .collectionToCommaDelimitedString(getRoles().stream().map(Enum::name).collect(Collectors.toList()));
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    public void setPassword(String password) {
        if (!password.isEmpty()) {
            this.password = BCryptManager.passwordEncoder().encode(password);
        }
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getComplementAdresse() {
        return complementAdresse;
    }

    public void setComplementAdresse(String complementAdresse) {
        this.complementAdresse = complementAdresse;
    }

    public String getCodePostale() {
        return codePostale;
    }

    public void setCodePostale(String codePostale) {
        this.codePostale = codePostale;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Collection<Evenement> getEvenements() {
        return evenements;
    }

    public void setEvenements(Collection<Evenement> evenements) {
        this.evenements = evenements;
    }

    public Collection<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Collection<Commentaires> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Collection<Commentaires> commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Membre{" +
                "idMembre=" + idMembre +
                ", pseudo='" + pseudo + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", adresse='" + adresse + '\'' +
                ", complementAdresse='" + complementAdresse + '\'' +
                ", codePostale='" + codePostale + '\'' +
                ", ville='" + ville + '\'' +
                ", roles=" + roles +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", evenements=" + evenements +
                ", Reservations=" + reservations +
                ", commentaires=" + commentaires +
                '}';
    }
}
