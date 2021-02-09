package com.Association.métier;

import com.Association.dao.EvenementCulturelRepository;
import com.Association.dao.MembreRepository;
import com.Association.dao.ReservationRepository;

import com.Association.entities.Evenement;
import com.Association.entities.Membre;
import com.Association.entities.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AssociationMetierImpl implements IAssociationMetier {

    @Autowired
    private MembreRepository membreRepository;



    @Autowired
    private EvenementCulturelRepository evenementCulturelRepository;

    @Autowired
    private ReservationRepository reservationRepository;



    @Override
    public Membre findByPseudo(String pseudo) {
        return membreRepository.findByPseudo(pseudo);
    }

    @Override
    public Membre findByEmail(String email) {
        return membreRepository.findByEmail(email);
    }


    @Override
    public Membre userConnected() {
        Membre membre=(Membre) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return membre;
    }

    @Override
    public List<Membre> listMembre(Long id) {
        return membreRepository.listMembre(id);
    }

    @Override
    public Optional<Evenement> findById(Long id) {
        return evenementCulturelRepository.findById(id);
    }

    @Override
    public List<Evenement> findAllByThemeAndDateEvenementAfter(String theme, Date dateEvenement) {
        return evenementCulturelRepository.findAllByThemeAndDateEvenementAfter(theme, dateEvenement);
    }

    @Override
    public List<Evenement> listeEvenementParTheme(String theme) {
        return evenementCulturelRepository.listeEvenementParTheme(theme);
    }

    @Override
    public List<Reservation> reservationsParMembre(Long id) {
        return reservationRepository.findAllByMembre_Id(id) ;
    }

    @Override
    public Boolean membreParticipeDeja(Long idEvenement, Long idMembre) {

        Optional<Reservation> reservation = reservationRepository.findByEvenement_IdAndMembre_Id(idEvenement,idMembre);
        if(reservation.isPresent()){
            return true;
        }else return false;
    }

  @Override
    public List<Membre> findAllByEnabledIsFalse() {
        return membreRepository.findAllByEnabledIsFalse();
    }

}
