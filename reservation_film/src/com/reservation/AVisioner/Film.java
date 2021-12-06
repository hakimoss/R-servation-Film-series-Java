package com.reservation.AVisioner;

import java.io.Serializable;

public class Film implements Serializable {
	private int idFilm;
	private String name;
	private String nomFilm;
	private String source;
	private int nombreMinute;

	public int getIdFilm() {
		return idFilm;
	}
	public void setIdFilm(int idFilm) {
		this.idFilm = idFilm;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNomFilm() {
		return nomFilm;
	}
	public void setNomFilm(String nomFilm) {
		this.nomFilm = nomFilm;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getNombreMinute() {
		return nombreMinute;
	}
	public void setNombreMinute(int nombreMinute) {
		this.nombreMinute = nombreMinute;
	}
	public Film() {
		super();
	}
	public Film(String name, String nomFilm, String source, int nombreMinute) {
		super();
		//this.idFilm = idFilm;
		this.name = name;
		this.nomFilm = nomFilm;
		this.source = source;
		this.nombreMinute = nombreMinute;
	}
	
	
}
