/*
 * GestionnaireAide.java				31/05/24
 * IUT de Rodez, pas de copyright 
 */

package vue;


/**
 * Gestion des mrddages s'aide
 */

import controleur.Mouvement;
import modele.Piece;

public class GestionAide {
	
	/**
	 * Affiche les coups possibles par un pion sélectionné lors de
	 * ce tour
	 */
    public void aideMouvement(Piece pieceSelectionnee) {
    	
        // Afficher les coups possibles par un pion sélectionné
        System.out.println("Affichage des coups possibles pour la pièce sélectionnée : ");
        for (Mouvement mouvement : pieceSelectionnee.getMouvementsPossibles()) {
            System.out.println("Déplacement possible vers la case : (" + mouvement.getX() + ", " + mouvement.getY() + ")");
        }
    }

    /**
     * Lorsqu'un joueur à gagné, fait apparaitre une fenêtre avec le
     * nom de l'équipe gagnante
     */
    public void victoire() {

        // Afficher quel joueur a gagné lorsque les conditions de victoire sont remplies
        System.out.println("Victoire d'un joueur !");

    }

    /**
     * si le joueur essai de faire un coup qui n'est pas autorisé par
     * le jeu, afficher une fenêtre signalant que le coup demandé est
     * impossible
     */
    public void interdiction() {
        // Afficher quand un joueur fait un coup impossible
        System.out.println("Coup impossible !");
    }
}
