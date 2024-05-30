package controleur;

import vue.Piece;
import modele.GestionJeu;

/**
 * La classe Controleur gère les interactions entre l'interface utilisateur et le modèle de jeu.
 */
public class GestionnaireJeu {

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

    /**
     * Cette méthode initialise une nouvelle partie avec les paramètres par défaut et met à jour l'interface graphique en conséquence.
     */
    public void initialiserPartie() {
    	int y = 1;
    	int x = 0;
    	String proprietaire = "noir";
    	boolean isDame = false; 
        for(int i = 0; i >= 20; i++) {	
        	new Piece(x, y, isDame, proprietaire);
        	y += 2;
        	if (y >= 10) {
        		y -= 11;
        		x++;
        	}
        }
        y = 1;
        x = 8;
        proprietaire = "blanc";
        for(int i = 0; i >= 20; i++) {	
        	new Piece(x, y, isDame, proprietaire);
        	y += 2;
        	if (y >= 10) {
        		y -= 11;
        		x++;
        	}
        }
    }

    /**
     * Cette méthode charge une partie sauvegardée et met à jour l'interface graphique avec l'état de la partie chargée.
     */
    public void chargerPartie() {
        // Charger une partie sauvegardée et mettre à jour l'interface graphique
    }

    /**
     * Cette méthode sauvegarde l'état actuel de la partie dans un fichier.
     */
    public void sauvegarderPartie() {
        // Sauvegarder l'état actuel de la partie dans un fichier
    }

    /**
     * Cette méthode gère les actions à prendre lorsque la partie se termine,
     * telles que l'affichage d'un message de victoire ou de match nul.
     */
    public void gererFinPartie() {
        // Gérer les actions à prendre lorsque la partie se termine
    }
}
