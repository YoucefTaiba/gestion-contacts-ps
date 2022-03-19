/**
 * 
 */
package com.poliscrypts.project.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * @author youcef
 *
 */
@Entity
public class Contact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	// nom de contact
	private String nom;
	// prenom
	private String prenom;
	// Freelnace
	private String adresse;
	@OneToMany(targetEntity = Job.class)
	private Set<Job> job = new HashSet<Job>();

	
	public Contact() {
		super(); 
	}

	public Contact(String nom, String prenom, String adresse) {

		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Set<Job> getJobs() {
		return job;
	}

	public void setJobs(Set<Job> workingAt) {
		this.job = workingAt;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse +  "]";
	}

}
