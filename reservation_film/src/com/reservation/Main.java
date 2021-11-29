package com.reservation;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");

			PreparedStatement ps = conn.prepareStatement("select * from reservation");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString(3) + "\t" + rs.getString("sources"));
			}
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		
		
		
		
	
		JFrame frame = new JFrame("Réservation de film/séries");
		frame.setSize(800,600);
		frame.setLayout(null);
		
		JPanel topPanel = new JPanel();
		topPanel.setBounds(new Rectangle(0, 0, 800, 100));
		topPanel.setSize(800, 300);
		frame.getContentPane().add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBounds(new Rectangle(0, 300, 800, 100));		
		bottomPanel.setSize(800, 100);
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
		
		JLabel labelSource = new JLabel("Inscrivez la sources");
		bottomPanel.add(labelSource);
		
		String[] sourceString = { "Neflix", "Disney", "Prime", "Streaming", "Autres" };
		JComboBox<String> sourcesList = new JComboBox(sourceString);
		bottomPanel.add(sourcesList);
		
		JPanel lastPanel = new JPanel();
		frame.getContentPane().add(lastPanel);
		lastPanel.setBounds(new Rectangle(0, 400, 800, 100));		
		lastPanel.setSize(800, 100);
		
		
		JLabel errorText = new JLabel("");
		lastPanel.add(errorText);
		
		JButton btnConfirmer = new JButton("Confirmer");
		lastPanel.add(btnConfirmer);
		
	
		String[] columnNames = {
				"Nom",
                "Film/series",
                "Source",
                };
		
		Object[][] data = new Object[20][3];
		
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
				String sourceFilm = (String) sourcesList.getSelectedItem();
				
				for(int i = 0 ; i < data.length ; i++){
					for(int j = 0 ; j < data[i].length; j++) {		
						if(inputName.getText().equals("") || inputFilm.getText().equals("")){
							errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez remplir tous les champs</p></html>");							
							CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
								errorText.setText("");
							});
							return;
						}
						if(data[i][0] == null) {
							data[i][0] = name;
							data[i][1] = aEcouter;
							data[i][2] = sourceFilm;
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
