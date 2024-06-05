/**
 * PlateauControleur.java									25/05/2024
 * Iut de Rodez, pas de copyright
 */
package controleur;

import modele.Piece;
import vue.Plateau;

/**
 * <p>Contrôleur pour la manipulation des pièces sur le plateau de jeu.</p>
 * @author Amjed SEHIL et Xavier TABORDA
 */
public class PieceControleur {
	
	/**
	 * <p>Gère le clic de la souris sur une pièce du plateau de jeu.</p>
	 * 
	 * <p>Vérifie si le joueur actuel peut manipuler la pièce cliquée. Si oui, sélectionne ou désélectionne la pièce
	 * en fonction de son état actuel.</p>
	 * 
	 * @param piece La pièce sur laquelle le clic de souris a été effectué.
	 * @param plateau Le plateau de jeu sur lequel se trouve la pièce.
	 */
	 @SuppressWarnings("static-method")
    public void handleMouseClick(Piece piece, Plateau plateau) {
	        if (plateau.getUtilisateur().getTourJoueur() == piece.getProprietaire()) {
	            if (plateau.getSelectionnePiece() == piece) {
	                plateau.deselectionnePiece();
	                System.out.println("Piece désélectionnée (Ligne " + piece.getLigne() + ", Colonne " + piece.getCol() + ")");
	            } else {
	                plateau.selectionnePiece(piece);
	                System.out.println("Piece sélectionnée (Ligne " + piece.getLigne() + ", Colonne " + piece.getCol() + ")");
	            }
	        } else {
	            System.out.println("Veuillez attendre votre tour");
	        }
	    }
	}
