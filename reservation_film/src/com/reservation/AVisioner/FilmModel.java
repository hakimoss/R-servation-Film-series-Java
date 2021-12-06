package com.reservation.AVisioner;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class FilmModel extends AbstractTableModel {
	
	private String[] nomColonnes=new String[] {"id", "NOM", "FILM", "SOURCES", "DURÉ(minute)"};
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
	public void setData(List<Film> films) {
		tableValues = new ArrayList<>();
		for(Film f:films) {
			tableValues.add(new String[] {
				""+f.getIdFilm(),
				f.getName(),
				f.getNomFilm(),
				f.getSource(),
				""+f.getNombreMinute()
				
				
			});
		}
		fireTableChanged(null);
	}
	
	

}
