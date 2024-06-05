package controleur;

import vue.Piece;

import modele.GestionJeu;
import modele.LogiqueJeu;

/**
 * La classe Controleur gère les interactions entre l'interface utilisateur et le modèle de jeu.
 */
public class GestionnaireJeu {
    private GestionJeu gestionJeu; // Référence vers le modèle

    public GestionnaireJeu() {
        this.gestionJeu = new GestionJeu(); // Initialisation du modèle
    }

    /**
     * Cette méthode initialise une nouvelle partie avec les paramètres par défaut et met à jour l'interface graphique en conséquence.
     */
    public void initialiserPartie() {
        gestionJeu.commencerJeu(); // Appel de la méthode pour démarrer une nouvelle partie du modèle
        // Mettre à jour l'interface graphique en conséquence...
    }

    /**
     * Cette méthode charge une partie sauvegardée et met à jour l'interface graphique avec l'état de la partie chargée.
     * @param nomFichier Le nom du fichier de sauvegarde.
     */
    public void chargerPartie(String nomFichier) {
        gestionJeu.chargerPartieSauvegardee(nomFichier); // Appel de la méthode du modèle pour charger une partie
        // Mettre à jour l'interface graphique avec l'état de la partie chargée...
    }

    /**
     * Cette méthode sauvegarde l'état actuel de la partie dans un fichier.
     * @param nomFichier Le nom du fichier de sauvegarde.
     */
    public void sauvegarderPartie(String nomFichier) {
        gestionJeu.sauvegarderPartie(nomFichier); // Appel de la méthode du modèle pour sauvegarder la partie
    }

    /**
     * Cette méthode gère les actions à prendre lorsque la partie se termine,
     * telles que l'affichage d'un message de victoire ou de match nul.
     */
    public void gererFinPartie() {
    	boolean victoireN = LogiqueJeu.verificationVictoire();
    	
    	/**
    	 * Vérification de victoire pour savoir si c'est les blanc ou les noirs
    	 * qui ont gagnées
    	 */
    	if (victoireN) {
    		System.out.print("Les Blancs ont gagnés");
    	} else {
    		System.out.print("Les Noirs ont gagnés");
    	}
        // Afficher le message de fin de partie dans l'interface graphique...
    }

    /**
     * Cette méthode lie les événements de l'interface utilisateur aux méthodes correspondantes du modèle et de la vue.
     */
    public void lierEvenementsInterface() {
        // Lier les événements de l'interface utilisateur aux méthodes du modèle et de la vue
    }

    /**
     * Cette méthode est appelée lorsque l'utilisateur clique sur une pièce sur le plateau de jeu.
     * Elle détermine les mouvements possibles pour cette pièce et les affiche à l'utilisateur.
     *
     * @param piece La pièce sur laquelle l'utilisateur a cliqué.
     */
    public void gererClicPiece(Piece piece) {
        // Gérer le clic sur une pièce et afficher les mouvements possibles
    }
}