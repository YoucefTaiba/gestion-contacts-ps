package com.poliscrypts.project.model;

import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String titre;

	private Boolean freelance = false;
	private Float tva = 0F;
	private Company company;

	public Job(String titre, Company company) {
		this.titre = titre;
		this.company = company;
	}

	public Job(String titre, Company company, Boolean freelance, Float tva) {
		this.titre = titre;
		this.company = company;
		this.freelance = freelance;
		this.tva = tva;
	}

	public String getTitre() {
		return titre;
	}

	public Company getCompany() {
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

	public void setCompany(Company company) {
		this.company = company;
	}

	public void setFreelance(Boolean isFreelance) {
		this.freelance = isFreelance;
	}

	public void setTva(Float tva) {
		this.tva = tva;
	}

}
