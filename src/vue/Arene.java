package vue;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import controle.global;

/**
 * frame de l'arène du jeu
 * @author emds
 *
 */
public class Arene extends JFrame implements global{

	/**
	 * Panel général
	 */
	private JPanel contentPane;
	
	private JPanel jpnMurs;
	private JPanel jpnJeu;
	/**
	 * Zone de saisie du t'chat
	 */
	private JTextField txtSaisie;
	/**
	 * Zone d'affichage du t'chat
	 */
	private JTextArea txtChat ;

	public void ajoutMurs(Object mur) {
		jpnMurs.add((JLabel)mur);
		jpnMurs.repaint();
	}
	
	public void setjpnMurs(JPanel info) {
		this.jpnMurs.add(info);
		this.jpnMurs.repaint();
	}
	public JPanel getjpnMurs() {
		return jpnMurs;
	}
	
	public void ajoutJLabelJeu(JLabel jeu) {
		this.jpnJeu.add(jeu);
		this.jpnJeu.repaint();
	}
	
	public JPanel getjpnJeu() {
		return jpnJeu;
	}
	public void setjpnJeu(JPanel info) {
		this.jpnJeu.removeAll();
		this.jpnJeu.add(info);
		this.jpnJeu.repaint();
	}
	/**
	 * Create the frame.
	 */
	public Arene() {
		// Dimension de la frame en fonction de son contenu
		this.getContentPane().setPreferredSize(new Dimension(LARGEURARENE, HAUTEURARENE + 25 + 140));
	    this.pack();
	    // interdiction de changer la taille
		this.setResizable(false);
		
		setTitle("Arena");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		jpnJeu = new JPanel();
		jpnJeu.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnJeu.setOpaque(false);
		jpnJeu.setLayout(null);		
		contentPane.add(jpnJeu);
		
		jpnMurs = new JPanel();
		jpnMurs.setBounds(0, 0, LARGEURARENE, HAUTEURARENE);
		jpnMurs.setOpaque(false);
		jpnMurs.setLayout(null);
		contentPane.add(jpnMurs);
	
		txtSaisie = new JTextField();
		txtSaisie.setBounds(0, 600, 800, 25);
		contentPane.add(txtSaisie);
		txtSaisie.setColumns(10);
		
		JScrollPane jspChat = new JScrollPane();
		jspChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspChat.setBounds(0, 625, 800, 140);
		contentPane.add(jspChat);
		
		txtChat = new JTextArea();
		jspChat.setViewportView(txtChat);
		
		JLabel lblFond = new JLabel("");
		String chemin = "fonds/fondarene.jpg";
		URL resource = getClass().getClassLoader().getResource(chemin);
		lblFond.setIcon(new ImageIcon(resource));		
		lblFond.setBounds(0, 0, 800, 600);
		contentPane.add(lblFond);
		
	}
}