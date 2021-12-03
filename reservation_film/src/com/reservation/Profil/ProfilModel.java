package com.reservation.Profil;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.reservation.AVisioner.Film;

public class ProfilModel extends AbstractTableModel {
	
	private String[] nomColonnes=new String[] {"id", "NOM", "Score"};
	private List<String[]> tableValues=new ArrayList<String[]>();

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableValues.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nomColonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableValues.get(rowIndex)[columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return nomColonnes[column];
	}
	
	public void setData(List<Profil> profils) {
		tableValues = new ArrayList<>();
		for(Profil p:profils) {
			tableValues.add(new String[] {
				""+p.getIdProfil(),
				p.getName(),
				""+p.getScore(),
		
			});
		}
		fireTableChanged(null);
	}

}
