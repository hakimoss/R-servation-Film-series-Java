package com.reservation.Profil;

import java.io.Serializable;

public class Profil implements Serializable {
	private int idProfil;
	private String name;
	private int score;
	
	public int getIdProfil() {
		return idProfil;
	}
	public void setIdProfil(int idProfil) {
		this.idProfil = idProfil;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public Profil() {
		super();
	}
	public Profil(String name, int score) {
		super();
		//this.idFilm = idFilm;
		this.name = name;
		this.score = score;

	}
	
	
}
