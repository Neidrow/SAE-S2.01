package modele;

 
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Joueur {

	private Color couleur;
	private String pseudo;
	private List<Piece> pieces;

	public Joueur(Color couleur, String joueurPseudo) {
		this.couleur = couleur;
		this.pseudo = joueurPseudo;
		pieces = new ArrayList<>();
	}

	public Color getColor() {
		return couleur;
	}

	public String getName() {
		return pseudo;
	}

	public List<Piece> getPieces() {
		return pieces;
	}
}