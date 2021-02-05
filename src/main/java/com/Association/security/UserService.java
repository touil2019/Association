package com.Association.security;

import com.Association.dao.MembreRepository;
import com.Association.entities.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {


    @Autowired
    private MembreRepository membreRepository;

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Membre membre = membreRepository.findByPseudo(pseudo);
        if (membre== null) {
            throw new UsernameNotFoundException("No user present with username : " + pseudo);
        }
        else {
            return membre;
        }
    }
}