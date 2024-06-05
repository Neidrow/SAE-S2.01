/**
 * Joueur.java						26/05/2024
 * Iut de Rodez, pas de copyright
 */
package modele;

 
import java.util.ArrayList;

import java.util.List;

import javafx.scene.paint.Color;

/**
 * <p>Représente un joueur dans le jeu.</p>
 * @author Amjed SEHIL et Xavier TABORDA
 */
public class Joueur {

	private Color couleur;
	private String pseudo;
	private List<Piece> pieces;

	/**
	 * <p>Initialise un joueur avec la couleur et le 
	 * pseudo spécifiés.</p>
	 * 
	 * @param couleur La couleur du joueur.
	 * @param joueurPseudo Le pseudo du joueur.
	 */
	public Joueur(Color couleur, String joueurPseudo) {
		this.couleur = couleur;
		this.pseudo = joueurPseudo;
		pieces = new ArrayList<>();
	}

	/**
	 * <p>Renvoie la couleur du joueur.</p>
	 * 
	 * @return La couleur du joueur.
	 */
	public Color getColor() {
		return couleur;
	}
	/**
	 * <p>Renvoie le pseudo du joueur.</p>
	 * 
	 * @return Le pseudo du joueur.
	 */
	public String getName() {
		return pseudo;
	}
	
	/**
	 * <p>Renvoie la liste des pièces possédées par le joueur.</p>
	 * 
	 * @return La liste des pièces du joueur.
	 */
	public List<Piece> getPieces() {
		return pieces;
	}
}