package com.reservation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import com.reservation.AVisioner.CatalogueFilm;
import com.reservation.AVisioner.Film;
import com.reservation.AVisioner.FilmModel;
import com.reservation.Profil.CatalogueProfil;
import com.reservation.Profil.Profil;

public class CatalogueSWING extends JFrame {
	private JLabel jLabelMC=new JLabel("Mot Cl?:");
	private JTextField jTextFieldMC=new JTextField(12);
	private JButton jButtonChercher= new JButton("Chercher");
	private JTable jTableFilm=new JTable();
	private FilmModel jtableModel;
	
	private JLabel jLabelNameAdd=new JLabel("Nom :");
	private JTextField jTextFieldNameAdd=new JTextField(12);
	private JLabel jLabelFilmAdd=new JLabel("Film/S?ries :");
	private JTextField jTextFieldFilmAdd=new JTextField(12);
	private JLabel jLabelSourceAdd=new JLabel("Source :");
	private String[] sourceString = { "Neflix", "Disney", "Prime", "Streaming", "Autres" };
	
	
	private boolean isSeries = false;
	private JLabel jLabelSaisonAdd=new JLabel("Nombre de saison :");
	private JTextField jTextFieldSaisonAdd=new JTextField(6);
	private JLabel jLabelEpisodeParSaisonAdd=new JLabel("Nombre d'?pisode par saison :");
	private JTextField jTextFieldEpisodeParSaisonAdd=new JTextField(6);
	private JLabel jLabelMinuteParEpisode=new JLabel("Nombre de minute par ?pisode:");
	private JTextField jTextFieldMinuteParEpisode=new JTextField(6);
	
	private boolean fullScreenBool = false;
	private boolean separateRowBool = false;

	
	CatalogueProfil allProfil=new CatalogueProfil();
	
	private JComboBox<String> sourcesList = new JComboBox(sourceString);	
	private JButton jButtonAdd=new JButton("Add");
	
	private JTextField idDelete = new JTextField(12);
	private JButton btnDelete = new JButton("delete");
	
	public JFrame frame;
	
	Page2 page2=new Page2();
	ManageProfil manageProfil=new ManageProfil();
	CreateProfil createProfilPage;
	DeleteProfil deleteProfilPage;

	/// Menu initialisation
	JMenuBar menuBar;
	JMenu menu, settings, profilMenu;
	JMenuItem addRemoveMenu, homeMenu, createProfilMenu, manageProfilMenu, deleteProfilMenu;
	JMenuItem settingsFullScreen, settingsSeparateRow;
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
		jPanelS.setBounds(0,200,getWidth(), 200);
		
		
		jPanelS.add(jLabelNameAdd);
		jPanelS.add(profilFinal);
		jPanelS.add(jLabelFilmAdd);
		jPanelS.add(jTextFieldFilmAdd);
		jPanelS.add(jLabelSourceAdd);
		jPanelS.add(sourcesList);
		
		jPanelS.add(jLabelSaisonAdd);
		jPanelS.add(jTextFieldSaisonAdd);
		jPanelS.add(jLabelEpisodeParSaisonAdd);
		jPanelS.add(jTextFieldEpisodeParSaisonAdd);
		jPanelS.add(jLabelMinuteParEpisode);
		jPanelS.add(jTextFieldMinuteParEpisode);
		
		jPanelS.add(jButtonAdd);
	
		
		jPanelN.add(idDelete);
		jPanelN.add(btnDelete);
		
		this.add(jPanelS, BorderLayout.SOUTH);
		
	
		menuBar = new JMenuBar();
		
		//onglet (menu)
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(menu);
		
		//onglet (settings)
		settings = new JMenu("Settings");
		settings.setMnemonic(KeyEvent.VK_A);
		settings.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
		menuBar.add(settings);
		
		//groupe Settings
		settingsFullScreen = new JMenuItem("Full Screen",KeyEvent.VK_T);
		settingsFullScreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.ALT_MASK));
		settingsFullScreen.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		settings.add(settingsFullScreen);
		
		
		settingsSeparateRow = new JMenuItem("Separate row",KeyEvent.VK_T);
		settingsSeparateRow.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, ActionEvent.ALT_MASK));
		settingsSeparateRow.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		settings.add(settingsSeparateRow);
		
		//groupe Menu
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
		
		jPanelN.setBackground(Color.lightGray);
		jPanelC.setBackground(Color.lightGray);
		jPanelS.setBackground(Color.lightGray);
		
		jTableFilm.setBackground(Color.lightGray);
		//jtableModel.setBackground(Color.black);
	
		
		
		
		this.setJMenuBar(menuBar);
		
		
		
		JRootPane jRootPane = new JRootPane();
		jRootPane.setDefaultButton(jButtonAdd); 	
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		frame=this;
		
		// display list when open 
		CatalogueFilm film=new CatalogueFilm();
		jtableModel.setData(film.getAllFilm());
		
		settingsSeparateRow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(separateRowBool==false) {
					separateRowBool= true;
				} else {
					separateRowBool=false;
				}
				
				CatalogueFilm film=new CatalogueFilm();
				jtableModel.setData(film.getAllFilm());
			}
			
		});
		
		jTableFilm.setDefaultRenderer(Object.class, new DefaultTableCellRenderer()
		{
		    @Override
		    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		    {
		    	if(separateRowBool == true) {
		    		final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
			        return c;
		    	} else {
		    		final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			        c.setBackground(row % 2 == 0 ? Color.WHITE : Color.WHITE);
			        return c;
		    	}
		        
		    }
		});
		
		settingsFullScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if(fullScreenBool == false) {
					fullScreenBool = true;
					
					frame.dispose();
					frame.setUndecorated(true);
					frame.setVisible(true);

				} else {
					fullScreenBool = false;
			
					frame.dispose();
					frame.setUndecorated(false);
					frame.setVisible(true);
				}
				
			}
			
		});
		
		manageProfilMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.remove(jPanelC);
				frame.remove(jPanelS);
				frame.remove(jPanelN);
				
				frame.add(manageProfil);
				
				frame.repaint();				
				frame.validate();	
				
			}
			
		});

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
				frame.remove(manageProfil);

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
				
				String valueSaison = jTextFieldSaisonAdd.getText();
				String valueEpisode = jTextFieldEpisodeParSaisonAdd.getText();
				String valueMinute = jTextFieldMinuteParEpisode.getText();
				
				if(valueName == null) {
					JOptionPane.showMessageDialog(null, "Veuillez cr?e un profil et le selectionner");
					return;
				}
				if (valueFilm.equals("") || sourceFilm.equals("") || valueSaison.equals("") || valueEpisode.equals("") || valueMinute.equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez remplire tous les champs");
					return;
				}
				
				int intValueSaison = Integer.parseInt(valueSaison);
				int intValueEpisode = Integer.parseInt(valueEpisode);
				int intValueMinute = Integer.parseInt(valueMinute);
				
				
				
				int MinutePourSaison = calculeMinuteSaison(intValueSaison, intValueEpisode, intValueMinute);
						
				Film filmAjouter = new Film(valueName, valueFilm, sourceFilm, MinutePourSaison);
				
				CatalogueFilm film=new CatalogueFilm();
				film.addFilm(filmAjouter);
				

				try {
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
					PreparedStatement ps = conn.prepareStatement("update profils set score = '"+(getScore(valueName)+MinutePourSaison)+"' where name = '"+valueName+"'");		
					ps.executeUpdate();
					ps.close();
					conn.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				jtableModel.setData(film.getAllFilm());
				jTextFieldNameAdd.setText("");
				jTextFieldFilmAdd.setText("");
				jTextFieldSaisonAdd.setText("");
				jTextFieldEpisodeParSaisonAdd.setText("");
				jTextFieldMinuteParEpisode.setText("");
				
				JOptionPane.showMessageDialog(null, "Film ajout?");
			}
			
		});
		
		
		// delete Film 
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int verifyInput = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment suprimer le film test ?");
				
				if(verifyInput==0) {
					
					
					String idDeleteString = idDelete.getText();		
					
					if(idDeleteString.matches("-?\\d+")) {
						int idValueDelete = Integer.parseInt(idDelete.getText());
						String nameToDelete = getNameParId(idValueDelete);
						int score = getScore(nameToDelete);
						int duration = getDuration(idValueDelete);
						int totalApres = score - duration; 

						try {
							//Class.forName("com.mysql.jdbc.Driver");
							Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
							PreparedStatement ps = conn.prepareStatement("update profils set score = '"+totalApres+"' where name = '"+nameToDelete+"'");
							//ps.setInt(1, MinutePourSaison);
							ps.executeUpdate();
							ps.close();
							conn.close();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						Film deleteFilm = new Film();
						deleteFilm.setIdFilm(idValueDelete);
						CatalogueFilm film=new CatalogueFilm();
						film.deleteFilm(deleteFilm);
						

						jtableModel.setData(film.getAllFilm());
						idDelete.setText("");
						frame.repaint();			
					} else {
						//error demande de int
						JOptionPane.showMessageDialog(null, "Veuillez indiquez l'id du film ou de la s?ries");
					}
						
				}
			
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
	
	private int calculeMinuteSaison(int nbSaison, int nbEpisode, int nbMinuteParEpisode) {
		int totalEpisode = nbSaison * nbEpisode;
		int totalMinute = totalEpisode * nbMinuteParEpisode;
		
		
	
		return totalMinute;
	}
	
	private int getScore(String name) {
		
		int scoreData = 0;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("select score from profils where name = '"+name+"'");
			//ps.setInt(1, MinutePourSaison);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				scoreData = rs.getInt("score");
			}
			ps.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return scoreData;
	}
	
	private String getNameParId(int idFilm) {
		String name = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("select name from reservation where id = '"+idFilm+"'");
			//ps.setInt(1, MinutePourSaison);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				name = rs.getString("name");
			}
			ps.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return name;
	}
	
	private int getDuration(int idFilm) {
		int duration = 0;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/reservation_film_series", "root", "");
			PreparedStatement ps = conn.prepareStatement("select dure from reservation where id = '"+idFilm+"'");
			//ps.setInt(1, MinutePourSaison);
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				duration = rs.getInt("dure");
			}
			ps.close();
			conn.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return duration;
	}
	

	

}

