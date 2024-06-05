package controleur;

import modele.Piece;
import vue.Plateau;

public class PieceControleur {
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
