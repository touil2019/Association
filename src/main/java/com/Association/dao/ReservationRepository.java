package com.Association.dao;

import com.Association.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Query(value="SELECT r from Reservation r inner join fetch r.evenement r where r.evenement.theme=:idEvenement  order by r.dateReservation desc",
            countQuery = "select count (r) from Reservation inner join r.evenement r where r.idEvenement=:idEvenement")
    List<Reservation> listeReservationparIdEvenement(@Param("idEvenement") Long idEvenement);
}
