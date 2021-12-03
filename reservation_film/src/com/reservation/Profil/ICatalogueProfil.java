package com.reservation.Profil;

import java.util.List;


public interface ICatalogueProfil {
	public List<Profil> profilParMc(String mc);
	public void addProfil(Profil p);
	public void deleteProfil(Profil p);
}
