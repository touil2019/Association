package com.Association.security;

import com.Association.entities.Membre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service("userService")
public class UsersService<MembreRepository> implements UserDetailsService {

    private final MembreRepository membreRepository;

    @Autowired
    public UsersService(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String pseudo) throws UsernameNotFoundException {
        Membre membre = membreRepository.findByPseudo(pseudo);
        if (membre == null) {
            throw new UsernameNotFoundException("No user present with pseudo : " + pseudo);
        }
        else {
            return membre;
        }
    }
}