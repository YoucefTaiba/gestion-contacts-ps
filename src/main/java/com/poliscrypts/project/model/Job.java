package com.poliscrypts.project.model;

import javax.persistence.*;

@Entity

public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;
	private String titre;

	private Boolean isFreelance = false;
	private Float tva = 0F;
	private Company company;
	
	public Job(String titre, Company company) {
		this.titre = titre;
		this.company = company;
	}

	public Job(String titre, Company company, Boolean isFreelance, Float tva) {
		this.titre = titre;
		this.company = company;
		this.isFreelance = isFreelance;
		this.tva = tva;
	}

	public String getTitre() {
		return titre;
	}

	public Company getCompany() {
		return company;
	}

	public Boolean getIsFreelance() {
		return isFreelance;
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

	public void setIsFreelance(Boolean isFreelance) {
		this.isFreelance = isFreelance;
	}

	public void setTva(Float tva) {
		this.tva = tva;
	}

}
