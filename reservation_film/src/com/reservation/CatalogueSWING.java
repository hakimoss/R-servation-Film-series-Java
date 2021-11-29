package com.reservation;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.reservation.AVisioner.CatalogueFilm;
import com.reservation.AVisioner.Film;
import com.reservation.AVisioner.FilmModel;

public class CatalogueSWING extends JFrame {
	private JLabel jLabelMC=new JLabel("Mot Clé:");
	private JTextField jTextFieldMC=new JTextField(12);
	private JButton jButtonChercher= new JButton("Chercher");
	private JTable jTableFilm=new JTable();
	private FilmModel jtableModel;
	
	private JLabel jLabelNameAdd=new JLabel("Votre nom :");
	private JTextField jTextFieldNameAdd=new JTextField(12);
	private JLabel jLabelFilmAdd=new JLabel("Votre Film/Séries :");
	private JTextField jTextFieldFilmAdd=new JTextField(12);
	private JLabel jLabelSourceAdd=new JLabel("La source :");
	private String[] sourceString = { "Neflix", "Disney", "Prime", "Streaming", "Autres" };
	private JComboBox<String> sourcesList = new JComboBox(sourceString);	
	private JButton jButtonAdd=new JButton("Add");
	private JLabel errorText = new JLabel("");
	
	private JTextField idDelete = new JTextField(12);
	private JButton btnDelete = new JButton("delete");
	
	
	
	
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
		jPanelS.add(jLabelNameAdd);
		jPanelS.add(jTextFieldNameAdd);
		jPanelS.add(jLabelFilmAdd);
		jPanelS.add(jTextFieldFilmAdd);
		jPanelS.add(jLabelSourceAdd);
		jPanelS.add(sourcesList);
		jPanelS.add(jButtonAdd);
		jPanelS.add(errorText);
		
		
		jPanelS.add(idDelete);
		jPanelS.add(btnDelete);
		
		this.add(jPanelS, BorderLayout.SOUTH);
		
		
		
		JRootPane jRootPane = new JRootPane();
		jRootPane.setDefaultButton(jButtonAdd); 
		
		this.setVisible(true);
		
		// add film
		
		jButtonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String valueName = jTextFieldNameAdd.getText();
				String valueFilm = jTextFieldFilmAdd.getText();
				String sourceFilm = (String) sourcesList.getSelectedItem();
								
				if (valueName.equals("") || valueFilm.equals("") || sourceFilm.equals("")) {
					errorText.setText("<html><p style='color:red;font-weight:900;'>Veuillez remplir tous les champs</p></html>");							
					CompletableFuture.delayedExecutor(3, TimeUnit.SECONDS).execute(() -> {
						errorText.setText("");
					});
					return;
				}
				Film filmAjouter = new Film(valueName, valueFilm, sourceFilm);
				
				CatalogueFilm film=new CatalogueFilm();
				film.addFilm(filmAjouter);

				
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
				
			}
			
		});
		
		
		
		jButtonChercher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String mc=jTextFieldMC.getText();
				CatalogueFilm film=new CatalogueFilm();
				List<Film> films=film.produitsParMc(mc);
				jtableModel.setData(films);
				
			}
			
		});
		
		
		
	}
	
	public static void main(String[] args) {
		new CatalogueSWING();
	}
	
	
}
