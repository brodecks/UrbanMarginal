package controle;

<<<<<<< HEAD
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
			this.frmArene = new Arene();
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

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case CONNEXION:
			if(!(this.leJeu instanceof JeuServeur)) {
				this.leJeu = new JeuClient(this);
				this.leJeu.connexion(connection);
				this.frmEntreeJeu.dispose();
				this.frmArene = new Arene();
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
=======
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
public class Controle implements AsyncResponse{
	
	private static int PORT = 6666;
	private EntreeJeu frmEntreeJeu ;
	private Arene frmArene;
	private ChoixJoueur frmChoixJoueur;
	private String typeJeu;
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
			this.typeJeu = "serveur";
			new ServeurSocket(this, PORT);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			this.frmArene.setVisible(true);
		}else {
			this.typeJeu = "client";
			new ClientSocket(this,info, PORT);
		}
	}
	
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		frmChoixJoueur.setVisible(false);
		Arene frmArene = new Arene();
		frmArene.setVisible(true);
	}

	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
		case "connexion":
			if(this.typeJeu.equals("client")) {
				this.frmEntreeJeu.dispose();
				this.frmChoixJoueur = new ChoixJoueur(this);
				this.frmChoixJoueur.setVisible(true);
				this.frmArene = new Arene();
			}
		case "reception":
			
		case "deconnexion":
			
		}
>>>>>>> branch 'master' of https://github.com/brodecks/UrbanMarginal.git
	}
}