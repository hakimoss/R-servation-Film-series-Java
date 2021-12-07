package com.reservation;

import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Graphics;
import java.awt.Image;

import com.reservation.Profil.CatalogueProfil;
import com.reservation.Profil.ProfilModel;

public class ManageProfil extends JPanel {
	
	private JTable jTableProfil;
	private ProfilModel jtableModel;

	ManageProfil() {
		jtableModel = new ProfilModel();
		jTableProfil = new JTable(jtableModel);
		JScrollPane jTableScroll=new JScrollPane(jTableProfil);
		
		CatalogueProfil profil=new CatalogueProfil();
		jtableModel.setData(profil.allProfil());
		
		
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());	
		this.add(jTableScroll);

	}
	
	
	@Override
	  protected void paintComponent(Graphics g) {

	    super.paintComponent(g);
	        Image bgImage = null;
	        try {
				bgImage = ImageIO.read(new File("img/rick.png"));	
			    Image temp=bgImage.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH);
				g.drawImage(temp , 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        getAllProfil();
			
	}

	public void getAllProfil() {
		CatalogueProfil profil=new CatalogueProfil();
		jtableModel.setData(profil.allProfil());
	}
}
