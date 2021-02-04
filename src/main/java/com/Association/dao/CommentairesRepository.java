package com.Association.dao;

import com.Association.entities.Commentaires;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentairesRepository extends JpaRepository<Commentaires,Long> {

    @Query(value="SELECT c from Commentaires c inner join fetch c.membre m where m.idMembre=:idMembre order by c.date desc",
    countQuery = "select count (c) from Commentaires inner join c.membre m where m.idMembre=:idMembre")
    List<Commentaires> listeCommentaireParMembre(@Param("idMembre") Long idMembre);

}
