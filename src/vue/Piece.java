package vue;

/**
 * La classe Piece représente un pion dans le jeu.
 */
public class Piece {
    private int x;
    private int y;
    private boolean isDame;
    private String proprietaire;

    /**
     * Constructeur pour initialiser un pion avec ses coordonnées, son type et son propriétaire.
     *
     * @param x           La coordonnée x du pion.
     * @param y           La coordonnée y du pion.
     * @param isDame      Indique si le pion est une dame (true) ou non (false).
     * @param proprietaire Le nom du propriétaire du pion.
     */
    public Piece(int x, int y, boolean isDame, String proprietaire) {
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
     */
    public void setX(int x) {
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
     */
    public void setY(int y) {
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
     * Obtient le nom du propriétaire du pion.
     *
     * @return Le nom du propriétaire du pion.
     */
    public String getProprietaire() {
        return proprietaire;
    }

    /**
     * Définit le nom du propriétaire du pion.
     *
     * @param proprietaire Le nouveau nom du propriétaire du pion.
     */
    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
}
