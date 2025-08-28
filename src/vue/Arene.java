package vue;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controle.Controle;
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
	private Controle controle;
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
	
	public String gettxtChat() {
		return txtChat.getText();
	}
	public void settxtChat(String chat) {
		this.txtChat.setText(chat);
	}
	public void ajoutChat(String phrase) {
		this.txtChat.setText(this.txtChat.getText()+phrase+"\r\n");
	}
	public void txtSaisie_KeyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!txtSaisie.getText().equals("")) {
				this.controle.evenementArene(this.txtSaisie.getText());
				this.txtSaisie.setText("");
			}
		}
	}
	/**
	 * Create the frame.
	 */
	public Arene(Controle controle) {
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
		txtSaisie.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				txtSaisie_KeyPressed(e);
			}
		});
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
		
		this.controle = controle;
		
	}
}