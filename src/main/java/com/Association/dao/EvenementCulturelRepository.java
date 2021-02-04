package com.Association.dao;

import com.Association.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvenementCulturelRepository extends JpaRepository<Evenement,Long> {


    @Query(value="SELECT e from Evenement e inner join fetch e.theme e where e.theme=:Art order by e.dateEvenement desc",
            countQuery = "select count (e) from Evenement inner join e.theme e where e.theme=:Art")
    List<Evenement> listeEvenementThemeArt(@Param("Art") String theme);

    @Query(value="SELECT e from Evenement e inner join fetch e.theme e where e.theme=:Musique order by e.dateEvenement desc",
            countQuery = "select count (e) from Evenement inner join e.theme e where e.theme=:Musique")
    List<Evenement> listeEvenementThemeMusique(@Param("Musique") String theme);

    @Query(value="SELECT e from Evenement e inner join fetch e.theme e where e.theme=:Conference order by e.dateEvenement desc",
            countQuery = "select count (e) from Evenement inner join e.theme e where e.theme=:Conference")
    List<Evenement> listeEvenementThemeConference(@Param("Conference") String theme);
}
