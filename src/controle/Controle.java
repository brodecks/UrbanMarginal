package controle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import modele.Jeu;
import modele.JeuServeur;
import modele.JeuClient;
import outils.connexion.AsyncResponse;
import outils.connexion.ClientSocket;
import outils.connexion.Connection;
import outils.connexion.ServeurSocket;
import vue.Arene;
import vue.ChoixJoueur;
import vue.EntreeJeu;

/**
 * Contrôleur et point d'entrée de l'applicaton 
 *
 */
public class Controle implements AsyncResponse, global{
	
	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;
	/**
	 * Méthode de démarrage
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this) ;
		this.frmEntreeJeu.setVisible(true);
	}	
	
	public void evenementEntreeJeu(String info) {
		if(info.equals("serveur")) {
			new ServeurSocket(this, PORT);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene(this, SERVEUR);
			((JeuServeur)this.leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}else {
			new ClientSocket(this,info, PORT);
		}
	}
	
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);
		((JeuClient)this.leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+numPerso);
	}
	public void evenementJeuServeur(String ordre, Object info) {
		switch(ordre) {
		case AJOUTMUR:
			frmArene.ajoutMurs(info);
			break;
		case AJOUTPANELMURS:
			this.leJeu.envoi((Connection)info, this.frmArene.getjpnMurs());
			break;
		case AJOUTJLABELJEU:
			this.frmArene.ajoutJLabelJeu((JLabel) info);
			break;
		case MODIFPANELJEU:
			this.leJeu.envoi((Connection)info, this.frmArene.getjpnJeu());
			break;
		case AJOUTPHRASE:
			this.frmArene.ajoutChat((String)info);
			((JeuServeur)this.leJeu).envoi(this.frmArene.gettxtChat());
			break;
		}			
	}
	
	public void evenementJeuClient(String ordre, Object info) {
		switch(ordre) {
		case AJOUTPANELMURS:
			this.frmArene.setjpnMurs((JPanel)info);
			break;
		case MODIFPANELJEU:
			this.frmArene.setjpnJeu((JPanel)info);
			break;
		case MODIFCHAT:
			this.frmArene.settxtChat((String)info);
			break;
		}
	}
	
	public void evenementArene(Object info) {
		if(info instanceof String) {
			((JeuClient)this.leJeu).envoi(CHAT+STRINGSEPARE+info);
		}else if(info instanceof Integer) {
			((JeuClient)this.leJeu).envoi(ACTION+STRINGSEPARE+info);
		}
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION:
			if(!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene(this, CLIENT);
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
			} else {
				this.leJeu.connexion(connection);
			}
			break;
		case RECEPTION:
			this.leJeu.reception(connection, info);
			break;
		case DECONNEXION:
			break;
		}
	}
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
}