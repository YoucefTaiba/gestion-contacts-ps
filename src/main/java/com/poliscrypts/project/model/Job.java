package com.poliscrypts.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String titre;
	@Column(name = "company_id")
	private Long company ;
	private Boolean freelance = false;
	private Float tva = 0F;

	public Job() {
	}

	public Job(String titre, Long company) {
		this.titre = titre;
		this.company  = company;
	}

	public Job(String titre, Long company, Boolean freelance, Float tva) {
		this(  titre,   company);
		this.freelance = freelance;
		this.tva = tva;
	}

	public Long getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public Long getCompany() {
		return company;
	}

	public Boolean isFreelance() {
		return freelance;
	}

	public Float getTva() {
		return tva;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public void setFreelance(Boolean isFreelance) {
		this.freelance = isFreelance;
	}

	public void setTva(Float tva) {
		this.tva = tva;
	}

}
