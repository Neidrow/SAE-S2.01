package controleur;

import javafx.scene.paint.Color;
import modele.Joueur;
import modele.Piece;
import modele.Utilisateur;
import vue.Plateau;

import java.util.ArrayList;
import java.util.List;

public class PlateauControleur {

    private Plateau plateau;
    private Piece selectionnePiece;

    public void PlateauController(Plateau plateau) {
        this.plateau = plateau;
        // Ajoutez ici des écouteurs d'événements ou d'autres initialisations si nécessaire
    }

    public void handleMouseClick(int row, int col) {
        System.out.println("Case cliquée : Ligne " + row + ", Colonne " + col);
        if (selectionnePiece != null) {
            if (mouvementValide(selectionnePiece, row, col)) {
                Piece mangerPion = peutEtreMange(selectionnePiece, row, col);
                if (mangerPion != null) {
                    movePiece(selectionnePiece, row, col);
                    eneleverPiece(mangerPion.getLigne(), mangerPion.getCol());
                    System.out.println("Pion mangé");
                } else {
                    movePiece(selectionnePiece, row, col);
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

    public boolean mouvementValide(Piece piece, int newRow, int newCol) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.mouvementValide(piece, newRow, newCol);
    }

    public Piece peutEtreMange(Piece piece, int newRow, int newCol) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.peutEtreMange(piece, newRow, newCol);
    }

    public void verificationFinPartie() {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.verificationFinPartie();
    }

    public List<int[]> getMouvementPossible(Piece piece) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        return plateau.getMouvementPossible(piece);
    }

    public void movePiece(Piece selectionnePiece, int row, int col) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.movePiece(selectionnePiece, row, col);
    }

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

    public void effacerSurbrillance() {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.effacerSurbrillance();
    }

    private void eneleverPiece(int row, int col) {
        // Méthode déjà implémentée dans Plateau, pas besoin de réécrire ici
        plateau.eneleverPiece(row, col);
    }
}
