package com.reservation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

	public static void main(String[] args) {
		
		
		JFrame frame = new JFrame("Réservation de film/séries");
		frame.setSize(800,600);
		frame.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(new Rectangle(0, 0, 800, 100));
		topPanel.setSize(800, 300);
		frame.getContentPane().add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(new Rectangle(0, 300, 800, 300));		
		bottomPanel.setSize(800, 300);
		frame.getContentPane().add(bottomPanel);
		
		JLabel labelName = new JLabel("Inscrivez votre nom !");
		labelName.setBounds(200, 650, 200, 30);
		bottomPanel.add(labelName);
		
		JTextField inputName = new JTextField(10);	
		inputName.setBounds(200, 675, 200, 30);
		bottomPanel.add(inputName);
		
		JLabel labelFilm = new JLabel("Inscrivez le film/series");
		labelFilm.setBounds(200, 710, 200, 30);
		bottomPanel.add(labelFilm);
	
		JTextField inputFilm = new JTextField(10);
		bottomPanel.add(inputFilm);
		inputFilm.setBounds(200, 735, 200, 30);
		
		JButton btnConfirmer = new JButton("Confirmer");
		bottomPanel.add(btnConfirmer);

		
		
		String[] columnNames = {
				"Nom",
                "Film/series",
                };
		
		Object[][] data = new Object[20][2];
		
		JTable table = new JTable(data, columnNames);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		table.setFillsViewportHeight(true);
		topPanel.add(scrollPane);
		
		frame.getRootPane().setDefaultButton(btnConfirmer);
		btnConfirmer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//e.getSource();
				String name = inputName.getText();
				String aEcouter = inputFilm.getText();
				for(int i = 0 ; i < data.length ; i++){
					for(int j = 0 ; j < data[i].length; j++) {		
						if(data[i][0] == null) {
							data[i][0] = name;
							data[i][1] = aEcouter;
							inputFilm.setText("");
							inputName.setText("");
							scrollPane.repaint();
							return;
						}
					}
				}
				
				
			}
			
		});
		
		frame.setVisible(true);

	}

}
