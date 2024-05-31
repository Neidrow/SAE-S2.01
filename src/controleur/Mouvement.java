package controleur;

/**
 * La classe Mouvement représente un mouvement possible d'une pièce dans le jeu.
 */
public class Mouvement {
    private int x;
    private int y;

    /**
     * Constructeur pour initialiser un mouvement avec ses coordonnées.
     *
     * @param x La coordonnée x du mouvement.
     * @param y La coordonnée y du mouvement.
     */
    public Mouvement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Obtient la coordonnée x du mouvement.
     *
     * @return La coordonnée x du mouvement.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtient la coordonnée y du mouvement.
     *
     * @return La coordonnée y du mouvement.
     */
    public int getY() {
        return y;
    }
}
