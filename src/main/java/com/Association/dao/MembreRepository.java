package com.Association.dao;

import com.Association.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembreRepository extends JpaRepository<Membre,Long> {


    /**
     * requete sql pour retourner le pseudo d un membre
     * @param pseudo
     * @return
     */
    @Query("select p from Membre p where p.pseudo=:pseudo")
    public Membre findByPseudo(@Param("pseudo") String pseudo);

}

