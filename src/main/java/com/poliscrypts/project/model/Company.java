package com.poliscrypts.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companys")
public class Company   {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
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
	public void setId(Long id) {
		this.id = id;
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
