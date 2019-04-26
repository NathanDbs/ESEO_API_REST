package com.dao;

public class VilleDAO {

	private String commune;
	private String code_postal;
	private String libelle;

	public String getNom_commune() {
		return commune;
	}

	public void setNom_commune(String commune) {
		this.commune = commune;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getLibelle_acheminement() {
		return libelle;
	}

	public void setLibelle_acheminement(String libelle_acheminement) {
		this.libelle = libelle_acheminement;
	}

}
