package com.Association.dao;

import com.Association.entities.Commentaires;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentairesRepository extends JpaRepository<Commentaires,Long> {
}
