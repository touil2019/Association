package com.Association;

import com.Association.dao.MembreRepository;
import com.Association.entities.Membre;
import com.Association.security.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AssociationApplication implements CommandLineRunner {

	@Autowired
	MembreRepository membreRepository;

	public static void main(String[] args) {

		SpringApplication.run(AssociationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@PostConstruct
	public void postConstruct(){

		Membre actif = new Membre("membre","membre","membre@gmail.com","membre","membre",34,"18 Rue Du Colonnel FABIEN","1 étage porte droite","75011","PARIS");
		Set<RoleEnum> actifRole = new HashSet<>();
		actifRole.add(RoleEnum.ROLE_ACTIF);
		actif.setRoles(actifRole);
		membreRepository.save(actif);

		Membre admin = new Membre("admin","admin","admin@gmail.com","admin","admin",43,"18 Rue Du Philibert HOFFMANN",null,"75012","PARIS");
		Set<RoleEnum> adminRole = new HashSet<>();
		adminRole.add(RoleEnum.ROLE_ADMIN);
		adminRole.add(RoleEnum.ROLE_ACTIF);
		adminRole.add(RoleEnum.ROLE_BIENFAITEUR);
		adminRole.add(RoleEnum.ROLE_HONORAIRE);
		admin.setRoles(adminRole);
		membreRepository.save(admin);
	}
}
