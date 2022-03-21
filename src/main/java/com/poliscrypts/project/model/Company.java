package com.poliscrypts.project.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "companys")
public class Company   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String nom;
	private String adresse; 
	private float tva;
	public Company() { 
		 
	}
	public Company(String nom, String adresse, float tva) {
		this.nom = nom;
		this.adresse = adresse;
		this.tva = tva; 
	}
	public Company(Long id, String nom, String adresse, float tva) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.tva = tva; 
	}
 
 

	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}
 

	public float getTva() {
		return tva;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
 

	public void setTva(float tva) {
		this.tva = tva;
	}

}
