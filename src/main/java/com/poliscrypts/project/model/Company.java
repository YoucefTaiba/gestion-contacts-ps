package com.poliscrypts.project.model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "company")
public class Company implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String nom;
	private String adresse;
	private String email;
	private float tva; 

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Company(Long id, String nom, String adresse, float tva) {

		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.tva = tva;
	}

	public Company(Long id, String nom, String adress, String email, float tva) {
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.adresse = adress;
		this.tva = tva;
	}

	public String getNom() {
		return nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public String getEmail() {
		return email;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTva(float tva) {
		this.tva = tva;
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", tva=" + tva + "]";
	}

}
