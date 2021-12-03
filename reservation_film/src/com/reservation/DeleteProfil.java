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

public class DeleteProfil extends JFrame {
	JPanel panelN;
	JPanel panelC;
	JPanel panelS;
	JLabel label;
	JTextField textFieldId;
	JButton btnDelete;
	JLabel errorText = new JLabel("");
	
	
	public DeleteProfil() {
		panelN=new JPanel();
		panelC=new JPanel();
		panelS=new JPanel();
		
		this.add(panelN, BorderLayout.NORTH);
		this.add(panelC, BorderLayout.CENTER);
		this.add(panelS, BorderLayout.SOUTH);
		this.setSize(400, 200);
		
		
		label=new JLabel("Delete by ID :");
		textFieldId = new JTextField(12);
		
		
		panelN.add(label);
		panelN.add(textFieldId);
		
		panelC.add(errorText);
		
		btnDelete = new JButton("Delete");
		panelS.add(btnDelete);

		
		this.setVisible(true);
		

		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String idString = textFieldId.getText();
				
				if(idString.equals("")) {
					errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez inscire l'ID du profil</p></html>");							
					CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
					errorText.setText("");
					});
					return;
				}
				
				int idValueDelete = Integer.parseInt(textFieldId.getText());

				Profil deleteProfil = new Profil();
				deleteProfil.setIdProfil(idValueDelete);
				CatalogueProfil profil=new CatalogueProfil();
				profil.deleteProfil(deleteProfil);
				
				textFieldId.setText("");
				setVisible(false);
				JOptionPane.showMessageDialog(null, "Profil Deleted");
	
				
			}
			
		});
		
	}
}
