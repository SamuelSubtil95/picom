package fr.humanbooster.fx.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Administrateur;

public interface AdministrateurDao extends JpaRepository<Administrateur, Long> {

}
