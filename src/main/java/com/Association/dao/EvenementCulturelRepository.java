package com.Association.dao;

import com.Association.entities.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EvenementCulturelRepository extends JpaRepository<Evenement,Long> {


    @Query(value="SELECT e from Evenement e where e.theme=:theme order by e.dateEvenement asc")
    List<Evenement> listeEvenementParTheme(@Param("theme") String theme);

    List<Evenement> findAllByThemeAndDateEvenementAfter(String theme,Date dateEvenement);


    Optional<Evenement> findById(Long id);

    @Query("select e from Evenement e where e.id= 1 order by e.dateEvenement asc")
    Optional<Evenement> monEventArt();
}
