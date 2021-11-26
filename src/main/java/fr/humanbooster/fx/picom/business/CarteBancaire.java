package fr.humanbooster.fx.picom.business;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CarteBancaire {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Client client;
	
	private String numeroCarte;
	
	private int anneExpiration;
	
	private byte moisExpiration;
	
	private String cryptogramme;

	public CarteBancaire() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getNumeroCarte() {
		return numeroCarte;
	}

	public void setNumeroCarte(String numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public int getAnneExpiration() {
		return anneExpiration;
	}

	public void setAnneExpiration(int anneExpiration) {
		this.anneExpiration = anneExpiration;
	}

	public byte getMoisExpiration() {
		return moisExpiration;
	}

	public void setMoisExpiration(byte moisExpiration) {
		this.moisExpiration = moisExpiration;
	}

	public String getCryptogramme() {
		return cryptogramme;
	}

	public void setCryptogramme(String cryptogramme) {
		this.cryptogramme = cryptogramme;
	}

	@Override
	public String toString() {
		return "CarteBancaire [id=" + id + ", client=" + client + ", numeroCarte=" + numeroCarte + ", anneExpiration="
				+ anneExpiration + ", moisExpiration=" + moisExpiration + ", cryptogramme=" + cryptogramme + "]";
	}
	
}