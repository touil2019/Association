package com.Association.métier;

import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;

import java.util.List;

public class EventParticipantsDto {
private Evenement evenement;
private List<Reservation> reservations;

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public EventParticipantsDto(Evenement evenement, List<Reservation> reservations) {
        this.evenement = evenement;
        this.reservations = reservations;
    }
}
