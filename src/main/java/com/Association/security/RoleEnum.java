package com.Association.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public enum RoleEnum implements GrantedAuthority {


    ROLE_ACTIF,
    ROLE_ADMIN,
    ROLE_BIENFAITEUR,
    ROLE_HONORAIRE;


    public String getAuthority() {
        return name();
    }
}

    class MyAuthoritiesMapper implements GrantedAuthoritiesMapper {

        public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
            Set<RoleEnum> roles = EnumSet.noneOf(RoleEnum.class);

            for (GrantedAuthority a : authorities) {
                if ("ADMIN".equals(a.getAuthority())) {
                    roles.add(RoleEnum.ROLE_ADMIN);
                    roles.add(RoleEnum.ROLE_ACTIF);
                    roles.add(RoleEnum.ROLE_BIENFAITEUR);
                    roles.add(RoleEnum.ROLE_HONORAIRE);
                } else if ("ACTIF".equals(a.getAuthority())) {
                    roles.add(RoleEnum.ROLE_ACTIF);
                } else if ("BIENFAITEUR".equals(a.getAuthority())) {
                    roles.add(RoleEnum.ROLE_BIENFAITEUR);
                } else if ("HONORAIRE".equals(a.getAuthority())) {
                    roles.add(RoleEnum.ROLE_HONORAIRE);
                }
            }

            return roles;
        }
    }


