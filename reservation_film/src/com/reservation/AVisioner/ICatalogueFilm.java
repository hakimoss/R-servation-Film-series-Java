package com.reservation.AVisioner;

import java.util.List;

public interface ICatalogueFilm {
	public List<Film> produitsParMc(String mc);
	public void addFilm(Film f);
	public void deleteFilm(Film f);
	public List<Film> getAllFilm();
}
