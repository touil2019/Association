package com.Association.dao;

import com.Association.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

   List<Reservation> findReservationsByEvenement(Long id);

   List<Reservation> findAllByMembre_Id(Long id);

   Optional <Reservation> findByEvenement_IdAndMembre_Id(Long idEvenement, Long idMembre);

}
