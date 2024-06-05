package modele;

import controleur.Joueur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import vue.Plateau;

public class Piece extends Circle {

	private int ligne;
	private int col;
	private Plateau plateau;
	private boolean estDame;
	private Joueur proprietaire;


	public Piece(int ligne, int col, double cellTaille, Plateau plateau, Joueur proprietaire) {
        this.ligne = ligne;
        this.col = col;
        this.plateau = plateau;
        this.proprietaire = proprietaire;

        // Calculer les coordonnées du centre de la cellule
        double centerX = cellTaille / 2.0;
        double centerY = cellTaille / 2.0;

        setCenterX(centerX); // Positionner le centre du cercle horizontalement
        setCenterY(centerY); // Positionner le centre du cercle verticalement

        setRadius(0.4 * cellTaille); // Définir le rayon en fonction de la moitié de la taille de la cellule
        setFill(proprietaire.getColor());
        setStroke(Color.BLACK); // Ajout de la bordure noire
        setStrokeWidth(3.0); // Largeur de la bordure
        setOnMouseClicked(e -> handleMouseClick());
    }

	public void handleMouseClick() {
        if (plateau.getUtilisateur().getTourJoueur() == proprietaire) {
            if (plateau.getSelectionnePiece() == this) {
                plateau.deselectionnePiece();
                System.out.println("Piece désélectionnée (Ligne " + ligne + ", Colonne " + col + ")");
            } else {
                plateau.selectionnePiece(this);
                System.out.println("Piece sélectionnée (Ligne " + ligne + ", Colonne " + col + ")");
            }
        } else {
            System.out.println("Veuillez attendre votre tour");
        }
    }

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean estDame() {
		return estDame;
	}

	public void transformationDame() {
	    if (estDame) {
	        estDame = true;
	        setStroke(Color.GOLD); // Définir la bordure de la pièce en doré
	        setStrokeWidth(10.0); // Définir l'épaisseur de la bordure
	    }
	}


	public Joueur getProprietaire() {
		return proprietaire;
	}
}
