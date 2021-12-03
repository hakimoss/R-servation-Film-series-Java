package com.reservation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;

import com.reservation.AVisioner.CatalogueFilm;
import com.reservation.AVisioner.Film;
import com.reservation.AVisioner.FilmModel;
import com.reservation.Profil.CatalogueProfil;
import com.reservation.Profil.Profil;

public class CatalogueSWING extends JFrame {
	private JLabel jLabelMC=new JLabel("Mot Cl�:");
	private JTextField jTextFieldMC=new JTextField(12);
	private JButton jButtonChercher= new JButton("Chercher");
	private JTable jTableFilm=new JTable();
	private FilmModel jtableModel;
	
	private JLabel jLabelNameAdd=new JLabel("Votre nom :");
	private JTextField jTextFieldNameAdd=new JTextField(12);
	private JLabel jLabelFilmAdd=new JLabel("Votre Film/S�ries :");
	private JTextField jTextFieldFilmAdd=new JTextField(12);
	private JLabel jLabelSourceAdd=new JLabel("La source :");
	private String[] sourceString = { "Neflix", "Disney", "Prime", "Streaming", "Autres" };
	CatalogueProfil allProfil=new CatalogueProfil();
	
	private JComboBox<String> sourcesList = new JComboBox(sourceString);	
	private JButton jButtonAdd=new JButton("Add");
	private JLabel errorText = new JLabel("");
	
	private JTextField idDelete = new JTextField(12);
	private JButton btnDelete = new JButton("delete");
	
	public JFrame frame;
	
	Page2 page2=new Page2();
	CreateProfil createProfilPage;
	DeleteProfil deleteProfilPage;

	/// Menu initialisation
	JMenuBar menuBar;
	JMenu menu, profilMenu;
	JMenuItem addRemoveMenu, homeMenu, createProfilMenu, manageProfilMenu, deleteProfilMenu;
	JPopupMenu popupMenu;

	
	public CatalogueSWING() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		JPanel jPanelN=new JPanel();
		jPanelN.setLayout(new FlowLayout());
		jPanelN.add(jLabelMC);
		jPanelN.add(jTextFieldMC);
		jPanelN.add(jButtonChercher);
		this.add(jPanelN, BorderLayout.NORTH);
		
		JPanel jPanelC=new JPanel();
		jtableModel = new FilmModel();
		jTableFilm = new JTable(jtableModel);
		JScrollPane jScrollPane=new JScrollPane(jTableFilm);
		jPanelC.setLayout(new GridLayout());
		jPanelC.add(jScrollPane);
		this.add(jPanelC, BorderLayout.CENTER);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());		
		
		JPanel jPanelS=new JPanel();
		
		List<String> profilList = allProfil.allProfil().stream()
				.map(Profil::getName)
				.collect(Collectors.toList());
		String[] stringArray = profilList.toArray(new String[0]);
		
		JComboBox<String> profilFinal = new JComboBox(stringArray);	
		
		
		
		jPanelS.add(jLabelNameAdd);
		jPanelS.add(profilFinal);
		jPanelS.add(jLabelFilmAdd);
		jPanelS.add(jTextFieldFilmAdd);
		jPanelS.add(jLabelSourceAdd);
		jPanelS.add(sourcesList);
		jPanelS.add(jButtonAdd);
		jPanelS.add(errorText);		
		
		jPanelS.add(idDelete);
		jPanelS.add(btnDelete);
		
		this.add(jPanelS, BorderLayout.SOUTH);
		
	
		menuBar = new JMenuBar();
		
		//build du menu
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);
		
		//ajout au menu un groupe
		addRemoveMenu = new JMenuItem("Add/Remove",KeyEvent.VK_T);
		addRemoveMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		addRemoveMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(addRemoveMenu);
		
		
		homeMenu = new JMenuItem("View List",KeyEvent.VK_T);
		homeMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
		homeMenu.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(homeMenu);
		
		popupMenu = new JPopupMenu();

		profilMenu = new JMenu("Profil");
		
		createProfilMenu = new JMenuItem("Create");
		profilMenu.add(createProfilMenu);
		manageProfilMenu = new JMenuItem("Manage");
		profilMenu.add(manageProfilMenu);
		deleteProfilMenu = new JMenuItem("Delete");
		profilMenu.add(deleteProfilMenu);

		
		menu.add(profilMenu);
		

		this.setJMenuBar(menuBar);
		
		JRootPane jRootPane = new JRootPane();
		jRootPane.setDefaultButton(jButtonAdd); 
		

		this.setVisible(true);
		
		frame=this;

		homeMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(jPanelC);
				frame.remove(jPanelS);
				frame.remove(jPanelN);
				
				frame.add(page2);
				
				frame.repaint();				
				frame.validate();										
			}
			
		});
		
		addRemoveMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(page2);

				frame.add(jPanelC);
				frame.add(jPanelN);
				frame.add(jPanelS);
				frame.add(jPanelS, BorderLayout.SOUTH);

				frame.repaint();
				frame.validate();		
				
			}
			
		});
		
		
		createProfilMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createProfilPage=new CreateProfil();
				
			}
			
		});
		
		deleteProfilMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteProfilPage=new DeleteProfil();
				
			}
			
		});
		
		// add film
		
		jButtonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String valueName = (String) profilFinal.getSelectedItem();
				String valueFilm = jTextFieldFilmAdd.getText();
				String sourceFilm = (String) sourcesList.getSelectedItem();
				if(valueName == null) {
					errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez cr�e un profil</p></html>");							
					CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
						errorText.setText("");
					});
					return;
				}
				if (valueFilm.equals("") || sourceFilm.equals("")) {
					errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez remplir tous les champs</p></html>");							
					CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
						errorText.setText("");
					});
					return;
				}
				
						
				Film filmAjouter = new Film(valueName, valueFilm, sourceFilm);
				
				CatalogueFilm film=new CatalogueFilm();
				film.addFilm(filmAjouter);
				
				
				jtableModel.setData(film.getAllFilm());
				jTextFieldNameAdd.setText("");
				jTextFieldFilmAdd.setText("");
				
				JOptionPane.showMessageDialog(null, "Database Updated");
			}
			
		});
		
		
		// delete Film 
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int idValueDelete = Integer.parseInt(idDelete.getText());				

				Film deleteFilm = new Film();
				deleteFilm.setIdFilm(idValueDelete);
				CatalogueFilm film=new CatalogueFilm();
				film.deleteFilm(deleteFilm);
				
				jtableModel.setData(film.getAllFilm());
				idDelete.setText("");
				frame.repaint();				
			}
			
		});

		
		jButtonChercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mc=jTextFieldMC.getText();
				CatalogueFilm film=new CatalogueFilm();
				List<Film> films=film.produitsParMc(mc);
				jtableModel.setData(films);
				
				jTextFieldMC.setText("");			
			}
			
		});
	
	}
	
}
