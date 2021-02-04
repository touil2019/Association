package com.Association.web;


import com.Association.dao.CommentairesRepository;
import com.Association.dao.MembreRepository;
import com.Association.métier.IAssociationMetier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentairesController {

    private static final Logger logger = LogManager.getLogger(CommentairesController.class);


    @Autowired
    private IAssociationMetier iAssociationMetier;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private CommentairesRepository commentairesRepository;
}

