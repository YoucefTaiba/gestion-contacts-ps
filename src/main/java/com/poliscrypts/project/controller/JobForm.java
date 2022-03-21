package com.poliscrypts.project.controller;

public class JobForm {
	private String titre;
	private Long companyId;

	private Boolean freelance = false;
	private Float tva = 0F;

	public JobForm(String titre, Long companyId) {
		super();
		this.titre = titre;
		this.companyId = companyId;
	}

	public JobForm(String titre, Long companyId, Boolean freelance, Float tva) {
		this(titre, companyId);
		this.tva = tva;
		this.freelance = freelance;
	}

	public String getTitre() {
		return titre;
	}

	public Boolean isFreelance() {
		return freelance;
	}

	public Float getTva() {
		return tva;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
 

	public void setFreelance(Boolean freelance) {
		this.freelance = freelance;
	}

	public void setTva(Float tva) {
		this.tva = tva;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

}
