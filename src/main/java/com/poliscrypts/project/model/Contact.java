/**
 * 
 */
package com.poliscrypts.project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author youcef
 *
 */
@Entity

@Table(name = "contacts")
public class Contact {

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
	private Set<Job> jobs = new HashSet<Job>();

	public Contact() {
	}
	public Contact(Long id,String nom, String prenom, String adresse) {
		this.id =id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}
	public Contact(String nom, String prenom, String adresse) {

		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
	}

	public Long getId() {
		return id;
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
		return jobs;
	}

	public void setJobs(Set<Job> workingAt) {
		this.jobs = workingAt;
	}

	public boolean equals(Contact contact) {
		return (this.nom.equalsIgnoreCase(contact.getNom())) && (this.prenom.equalsIgnoreCase(contact.getPrenom()));
	} 

}
