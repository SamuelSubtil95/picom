package fr.humanbooster.fx.picom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.humanbooster.fx.picom.business.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

}
