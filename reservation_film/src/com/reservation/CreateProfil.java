package com.reservation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.reservation.Profil.CatalogueProfil;
import com.reservation.Profil.Profil;

public class CreateProfil extends JFrame {
	
	JPanel panelN;
	JPanel panelC;
	JPanel panelS;
	JLabel label;
	JLabel errorText = new JLabel("");
	JTextField textFieldName;
	
	Profil profil;
	
	
	JButton btnCreate;

	public CreateProfil() {
		panelN = new JPanel();
		panelS = new JPanel();
		panelC = new JPanel();
		label = new JLabel("Your Name : ");
		btnCreate = new JButton("Create Profil");
		textFieldName = new JTextField(12);
		
		this.add(panelN, BorderLayout.NORTH);
		this.add(panelC, BorderLayout.CENTER);
		this.add(panelS, BorderLayout.SOUTH);
		this.setSize(400, 200);
		
		
		panelN.add(label);
		panelN.add(textFieldName);

		panelC.add(errorText);
		
		panelS.add(btnCreate);
		

		this.setVisible(true);
		
		
		btnCreate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText();
				
				if(name.equals("")) {
					errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez inscrire votre nom de profil</p></html>");							
					CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
					errorText.setText("");
					});
					return;
				}
				
				profil = new Profil(name, 0);
				
				CatalogueProfil catalogueProfil=new CatalogueProfil();
				catalogueProfil.addProfil(profil);
				
				
				
				textFieldName.setText("");
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Profil Created");
				
				
				
			}
			
		});
		
		
		
	}
}
