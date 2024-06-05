package modele;

import java.util.List;

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
		setRadius(0.4 * cellTaille);
		setFill(proprietaire.getColor());
		setOnMouseClicked(e -> handleMouseClick());
	}

	public void handleMouseClick() {
		if (plateau.getUtilisateur().getTourJoueur() == proprietaire) {
			plateau.effacerSurbrillance(); // Effacer les anciennes surbrillances
			plateau.setselectionnePiece(this);
			List<int[]> mouvementPossible = plateau.getMouvementPossible(this);
            plateau.afficherMouvementPossible(mouvementPossible);
			System.out.println("Piece sélectionnée (Ligne " + ligne + ", Colonne " + col + ")");
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
		if (!estDame) {
			estDame = true;

			setStroke(Color.GOLD);
			setStrokeWidth(2.0);
		}
	}

	public Joueur getProprietaire() {
		return proprietaire;
	}
}