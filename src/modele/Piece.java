/**
 * Piece.java									25/05/2024
 * Iut de Rodez, pas de copyright
 */
package modele;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import vue.Plateau;

/**
 * <p>Représente une pièce dans le jeu.</p>
 * <p>Une pièce est une forme circulaire affichée sur le plateau de jeu.</p>
 * @author Amjed SEHIL et Rodrigo TABORDA
 */
public class Piece extends Circle {

	private int ligne;
	private int col;
	private Plateau plateau;
	private boolean estDame;
	private Joueur proprietaire;

	/**
	 * <p>Initialise une pièce avec la ligne, la colonne, la taille de la cellule, le plateau et le joueur propriétaire spécifiés.</p>
	 * 
	 * @param ligne La ligne où se trouve la pièce.
	 * @param col La colonne où se trouve la pièce.
	 * @param cellTaille La taille de la cellule.
	 * @param plateau Le plateau de jeu.
	 * @param proprietaire Le joueur propriétaire de la pièce.
	 */

	public Piece(int ligne, int col, double cellTaille, Plateau plateau, Joueur proprietaire) {
        this.ligne = ligne;
        this.col = col;
        this.plateau = plateau;
        this.proprietaire = proprietaire;


        setRadius(0.4 * cellTaille); // Définir le rayon en fonction de la moitié de la taille de la cellule
        setFill(proprietaire.getColor());
        setStroke(Color.GRAY); // Ajout de la bordure noire
        setStrokeWidth(3.0); // Largeur de la bordure
        setOnMouseClicked(e -> handleMouseClick());
    }

	/**
	 * <p>Gère le clic de la souris sur la pièce.</p>
	 * <p>Vérifie si le joueur peut sélectionner ou désélectionner la pièce en fonction de son tour.</p>
	 */
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

	/**
	 * <p>Renvoie la ligne où se trouve la pièce.</p>
	 * 
	 * @return La ligne de la pièce.
	 */
	public int getLigne() {
		return ligne;
	}

	/**
	 * <p>Définit la ligne où se trouve la pièce.</p>
	 * 
	 * @param ligne La nouvelle ligne de la pièce.
	 */
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	
	/**
	 * <p>Renvoie la colonne où se trouve la pièce.</p>
	 * 
	 * @return La colonne de la pièce.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * <p>Définit la colonne où se trouve la pièce.</p>
	 * 
	 * @param col La nouvelle colonne de la pièce.
	 */
	public void setCol(int col) {
		this.col = col;
	}

	/**
	 * <p>Vérifie si la pièce est une dame.</p>
	 * 
	 * @return true si la pièce est une dame, sinon false.
	 */
	public boolean estDame() {
		return estDame;
	}

	/**
	 * <p>Transforme la pièce en dame.</p>
	 * <p>Si la pièce n'était pas déjà une dame, elle est désormais transformée en dame.</p>
	 */
	public void transformationDame() {
	    if (!estDame) {
	        estDame = true;
	        setStrokeWidth(4.0); // Définir l'épaisseur de la bordure

	        setStroke(Color.PURPLE); // Ajout de la bordure noire
	    }
	}

	/**
	 * <p>Renvoie le joueur propriétaire de la pièce.</p>
	 * 
	 * @return Le joueur propriétaire de la pièce.
	 */
	public Joueur getProprietaire() {
		return proprietaire;
	}
}
