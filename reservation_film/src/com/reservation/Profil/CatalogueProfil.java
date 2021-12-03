package com.reservation.Profil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class CatalogueProfil implements ICatalogueProfil {
	
	@Override
	public List<Profil> profilParMc(String mc) {
		List<Profil> profils = new ArrayList<Profil>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("select * from profils where name like ?");
			ps.setString(1, "%"+mc+"%");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Profil p = new Profil();
				p.setIdProfil(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setScore(rs.getInt("score"));
				profils.add(p);
				
			}
			ps.close();conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return profils;
	}

	@Override
	public void addProfil(Profil p) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("insert into profils(name,score) value(?,?)");
			ps.setString(1, p.getName());
			ps.setInt(2, p.getScore());
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteProfil(Profil p) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("delete from profils where id = ?");
			
			ps.setInt(1, p.getIdProfil());
			ps.execute();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Profil> allProfil() {
		List<Profil> profils = new ArrayList<Profil>();
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("select * from profils");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				Profil p = new Profil();
				p.setIdProfil(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setScore(rs.getInt("score"));
				profils.add(p);
			}
		
			ps.close();conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return profils;		
	}

}
