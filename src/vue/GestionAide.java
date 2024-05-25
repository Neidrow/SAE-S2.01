package vue;

import controleur.Mouvement;

public class GestionAide {
    public void aideMouvement(Piece pieceSelectionnee) {
        // Afficher les coups possibles par un pion sélectionné
        System.out.println("Affichage des coups possibles pour la pièce sélectionnée : ");
        for (Mouvement mouvement : pieceSelectionnee.getMouvementsPossibles()) {
            System.out.println("Déplacement possible vers la case : (" + mouvement.getX() + ", " + mouvement.getY() + ")");
        }
    }

    public void victoire() {
        // Afficher quel joueur a gagné lorsque les conditions de victoire sont remplies
        System.out.println("Victoire d'un joueur !");
    }

    public void interdiction() {
        // Afficher quand un joueur fait un coup impossible
        System.out.println("Coup impossible !");
    }
}
