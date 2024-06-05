/**
 * PlateauControleur.java									25/05/2024
 * Iut de Rodez, pas de copyright
 */
package controleur;

import javafx.scene.paint.Color;
import modele.Piece;
import vue.Plateau;
import java.util.List;

/**
 * <p>Contrôleur pour la manipulation des éléments sur le plateau de jeu.</p>
 * @author Amjed SEHIL et Xavier TABORDA
 */
public class PlateauControleur {

    private Plateau plateau;
    private Piece selectionnePiece;

    /**
     * <p>Initialise le contrôleur avec le plateau de jeu spécifié.</p>
     * 
     * @param plateau Le plateau de jeu à contrôler.
     */
    public void PlateauController(Plateau plateau) {
        this.plateau = plateau;
        // Ajoutez ici des écouteurs d'événements ou d'autres initialisations si nécessaire
    }
    
    /**
     * <p>Gère le clic de la souris sur une case du plateau de jeu.</p>
     * 
     * <p>Vérifie si une pièce est sélectionnée, puis valide le mouvement vers la case cliquée.
     * Si le mouvement est autorisé, déplace la pièce et gère les actions associées telles que la capture
     * d'une pièce adverse et la promotion en dame.</p>
     * 
     * @param row L'indice de ligne de la case cliquée.
     * @param col L'indice de colonne de la case cliquée.
     */
    public void handleMouseClick(int row, int col) {
        System.out.println("Case cliquée : Ligne " + row + ", Colonne " + col);
        if (selectionnePiece != null) {
            if (mouvementValide(selectionnePiece, row, col)) {
                Piece mangerPion = peutEtreMange(selectionnePiece, row, col);
                if (mangerPion != null) {
                	deplacerPiece(selectionnePiece, row, col);
                    eneleverPiece(mangerPion.getLigne(), mangerPion.getCol());
                    System.out.println("Pion mangé");
                } else {
                	deplacerPiece(selectionnePiece, row, col);
                    System.out.println("Déplacement du pion de (" + selectionnePiece.getLigne() + ", " + selectionnePiece.getCol() + ") à (" + row + ", " + col + ")");
                }

                // Vérifier si la pièce doit être promue en dame
                if ((selectionnePiece.getProprietaire() == plateau.getUtilisateur().getJoueur1() && row == 0) ||
                        (selectionnePiece.getProprietaire() == plateau.getUtilisateur().getJoueur2() && row == plateau.getTaille() - 1)) {
                    selectionnePiece.transformationDame();
                }

                deselectionnePiece(); // Désélectionner la pièce une fois le mouvement terminé
                effacerSurbrillance();
                verificationFinPartie();
            } else {
                System.out.println("Mouvement non autorisé.");
            }
        } else {
            System.out.println("Veuillez d'abord sélectionner un pion");
        }
    }

    /**
     * <p>Vérifie si le mouvement d'une pièce vers une nouvelle position est valide.</p>
     * 
     * @param piece La pièce à déplacer.
     * @param newRow La nouvelle ligne de destination.
     * @param newCol La nouvelle colonne de destination.
     * @return {@code true} si le mouvement est valide, {@code false} sinon.
     */    
    public boolean mouvementValide(Piece piece, int newRow, int newCol) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.mouvementValide(piece, newRow, newCol);
    }

    /**
     * <p>Détermine si une pièce peut être capturée par une autre pièce lors d'un mouvement.</p>
     * 
     * @param piece La pièce qui effectue le mouvement.
     * @param newRow La ligne de destination du mouvement.
     * @param newCol La colonne de destination du mouvement.
     * @return La pièce qui peut être capturée, ou {@code null} s'il n'y a aucune pièce à capturer.
     */
    public Piece peutEtreMange(Piece piece, int newRow, int newCol) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.peutEtreMange(piece, newRow, newCol);
    }

    /**
     * <p>Gère la vérification de fin de partie.</p>
     * 
     * <p>Vérifie si l'un des joueurs n'a plus de pièces sur le plateau et affiche le résultat.</p>
     */
    public void verificationFinPartie() {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.verificationFinPartie();
    }

    /**
     * <p>Renvoie la liste des mouvements possibles pour une pièce donnée.</p>
     * 
     * @param piece La pièce pour laquelle trouver les mouvements possibles.
     * @return La liste des mouvements possibles sous forme de coordonnées [ligne, colonne].
     */
    public List<int[]> getMouvementPossible(Piece piece) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.getMouvementPossible(piece);
    }

    /**
     * <p>Déplace une pièce sélectionnée vers une nouvelle position.</p>
     * 
     * @param selectionnePiece La pièce sélectionnée à déplacer.
     * @param row La ligne de destination du déplacement.
     * @param col La colonne de destination du déplacement.
     */
    public void deplacerPiece(Piece selectionnePiece, int row, int col) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.deplacerPiece(selectionnePiece, row, col);
    }

    /**
     * <p>Sélectionne une pièce pour effectuer un mouvement.</p>
     * 
     * @param piece La pièce à sélectionner.
     */
    public void selectionnePiece(Piece piece) {
        if (selectionnePiece != null) {
            deselectionnePiece();
        }
        selectionnePiece = piece;
        if (selectionnePiece.estDame()) {
            selectionnePiece.setStroke(Color.PURPLE); // Si la pièce est une dame, la bordure est dorée
        } else {
            selectionnePiece.setStroke(Color.RED);
        }
        plateau.afficherMouvementPossible(getMouvementPossible(selectionnePiece));
    }

    /**
     * <p>Désélectionne la pièce actuellement sélectionnée.</p>
     */
    public void deselectionnePiece() {
        if (selectionnePiece != null) {
            if (selectionnePiece.estDame()) {
                selectionnePiece.setStroke(Color.PURPLE); // Si la pièce est une dame et qu'elle est désélectionnée, la bordure est dorée
            } else {
                selectionnePiece.setStroke(Color.GRAY);
            }
            selectionnePiece = null;
            effacerSurbrillance();
        }
    }

    /**
     * <p>Efface la surbrillance des cases pour indiquer les mouvements possibles.</p>
     */
    public void effacerSurbrillance() {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.effacerSurbrillance();
    }
    
    /**
     * <p>Supprime une pièce du plateau.</p>
     * 
     * @param row La ligne de la pièce à supprimer.
     * @param col La colonne de la pièce à supprimer.
     */
    private void eneleverPiece(int row, int col) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.eneleverPiece(row, col);
    }
}
