package modele;

import java.util.ArrayList;
import java.util.List;

import controleur.Mouvement;

/**
 * Représente un pion dans le jeu, tout en pouvant changer son état au cours de la partie.
 * 
 * @author groupe41
 */
public class Piece {
    
    private int x;
    private int y;
    private boolean isDame;
    private String proprietaire;
    
    /** Est égale à la coordonnée de la case la plus éloignée */
    public static final int CASE_MAX_PLATEAU = 9;
    
    /** Est égale à la coordonnée de la case la plus proche */
    public static final int CASE_MIN_PLATEAU = 0;

    /**
     * Initialise un pion avec ses coordonnées, son type et son propriétaire.
     *
     * @param x           La coordonnée horizontale du pion (0 <= x <= 9)
     * @param y           La coordonnée verticale du pion (0 <= y <= 9)
     * @param isDame      Indique si le pion est une dame (true) ou non (false).
     * @param proprietaire Le nom du propriétaire du pion, soit "blanc" soit "noir".
     * @throws IllegalArgumentException si :
     *         - le propriétaire n'est pas égal à "blanc" ou "noir"
     *         - le pion est placé sur une case blanche (détectable si x+y est impair)
     *         - x ou y est supérieur à 9 ou inférieur à 0
     */
    public Piece(int x, int y, boolean isDame, String proprietaire) {
        if (!proprietaire.equals("noir") && !proprietaire.equals("blanc")) {
            throw new IllegalArgumentException("Le propriétaire est invalide");
        }
        if ((x + y) % 2 == 0) {
            throw new IllegalArgumentException("Le pion est placé sur une case blanche !");
        } else if (x < CASE_MIN_PLATEAU || y < CASE_MIN_PLATEAU || x > CASE_MAX_PLATEAU || y > CASE_MAX_PLATEAU) {
            throw new IllegalArgumentException("Le pion n'est pas sur le damier !");
        }
        this.x = x;
        this.y = y;
        this.isDame = isDame;
        this.proprietaire = proprietaire;
    }

    /**
     * Obtient la coordonnée x du pion.
     *
     * @return La coordonnée x du pion.
     */
    public int getX() {
        return x;
    }

    /**
     * Définit la coordonnée x du pion.
     *
     * @param x La nouvelle coordonnée x du pion.
     * @throws IllegalArgumentException si :
     *         - le pion est placé sur une case blanche (détectable si x+y est impair)
     *         - x est supérieur à 9 ou inférieur à 0
     */
    public void setX(int x) {
        if ((x + y) % 2 == 0) {
            throw new IllegalArgumentException("Le pion est placé sur une case blanche !");
        } else if (x < CASE_MIN_PLATEAU || x > CASE_MAX_PLATEAU) {
            throw new IllegalArgumentException("Le pion n'est pas sur le damier !");
        }
        this.x = x;
    }

    /**
     * Obtient la coordonnée y du pion.
     *
     * @return La coordonnée y du pion.
     */
    public int getY() {
        return y;
    }

    /**
     * Définit la coordonnée y du pion.
     *
     * @param y La nouvelle coordonnée y du pion.
     * @throws IllegalArgumentException si :
     *         - le pion est placé sur une case blanche (détectable si x+y est impair)
     *         - y est supérieur à 9 ou inférieur à 0
     */
    public void setY(int y) {
        if ((x + y) % 2 == 0) {
            throw new IllegalArgumentException("Le pion est placé sur une case blanche !");
        } else if (y < CASE_MIN_PLATEAU || y > CASE_MAX_PLATEAU) {
            throw new IllegalArgumentException("Le pion n'est pas sur le damier !");
        }
        this.y = y;
    }

    /**
     * Indique si le pion est une dame.
     *
     * @return true si le pion est une dame, false sinon.
     */
    public boolean isDame() {
        return isDame;
    }

    /**
     * Définit si le pion est une dame.
     *
     * @param dame true pour définir le pion comme une dame, false sinon.
     */
    public void setDame(boolean dame) {
        isDame = dame;
    }

    /**
     * Obtient les mouvements possibles pour cette pièce.
     *
     * @return Une liste des mouvements possibles.
     */
    public List<Mouvement> getMouvementsPossibles() {
        List<Mouvement> mouvementsPossibles = new ArrayList<>();

        // Si la pièce est une dame, elle peut se déplacer dans n'importe quelle direction
        if (isDame) {
            mouvementsPossibles.add(new Mouvement(x + 1, y + 1)); // Déplacement diagonale bas droite
            mouvementsPossibles.add(new Mouvement(x + 1, y - 1)); // Déplacement diagonale bas gauche
            mouvementsPossibles.add(new Mouvement(x - 1, y + 1)); // Déplacement diagonale haut droite
            mouvementsPossibles.add(new Mouvement(x - 1, y - 1)); // Déplacement diagonale haut gauche
        } else {
            // Sinon, la pièce ne peut se déplacer que vers le haut ou vers le bas en diagonale, selon le propriétaire
            int direction = (proprietaire.equals("blanc")) ? 1 : -1;
            mouvementsPossibles.add(new Mouvement(x + direction, y + 1)); // Déplacement diagonale bas
            mouvementsPossibles.add(new Mouvement(x + direction, y - 1)); // Déplacement diagonale haut
        }

        return mouvementsPossibles;
    }
}
