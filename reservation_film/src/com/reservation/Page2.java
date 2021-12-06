package com.reservation;

import java.awt.Toolkit;



import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class Page2 extends JPanel {
	
	//JPanel panelPage2=new JPanel();
	JLabel labelTest=new JLabel("Bonjour");
	
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem addRemoveMenu, homeMenu;

	
	public Page2() {
		
		
		//panelPage2.setLayout(new FlowLayout());
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());		
		this.add(labelTest);
		//this.setVisible(true);

		
	}
	


	
}
