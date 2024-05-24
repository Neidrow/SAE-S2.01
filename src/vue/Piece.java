package vue;

/**
 * La classe Piece représente un pion dans le jeu.
 */
public class Piece {
    
	private int x;
    private int y;
    private boolean isDame;
    private String proprietaire;
    
    /** est égale à la coordonnée de la cas la plus élogoignée */
    public static final int CASE_MAX_PLATEAU = 9;
    
    /** est égale à la coordonnée de la cas la plus proche */
    public static final int CASE_MIN_PLATEAU = 0;

    /**
     * Constructeur pour initialiser un pion avec ses coordonnées, 
     * son type et son propriétaire.
     *
     * @param x           La coordonnée horizontale du pion (0<=x<=9)
     * @param y           La coordonnée verticale du pion (0<=y<=9)
     * @param isDame      Indique si le pion est une dame (true)
     *                                            ou non (false).
     * @param proprietaire Le nom du propriétaire du pion, soit blanc
     *                                                     soit noir.
     * @throws IllegalArgumentException si :
     * <ul>
     * 		   <li> le propriétaire s'il n'est pas égal à blanc ou 
     *              noir </li>
     *         <li> le pion est placé sur une case blanche 
     *              ( détectable si x+y est pair ) </li>
     *         <li> x ou y est supérieur à 9 ou inférieur à 0 </li>
     * </ul>
     */
    public Piece(int x, int y, boolean isDame, String proprietaire) {
    	if (proprietaire != "noir" && proprietaire != "blanc") {
    		throw new IllegalArgumentException("Le propriétaire est invalide");
    	}
    	if ((x+y)%2 != 1) {
    		throw new IllegalArgumentException("Le pion est placé sur une "
    				                        + "case blanche !");
    	} else if (x < CASE_MIN_PLATEAU || y < CASE_MIN_PLATEAU 
    	    || x > CASE_MAX_PLATEAU || y > CASE_MAX_PLATEAU) {
    		throw new IllegalArgumentException("Le pion n'est pas sur le"
    				                                 + " damier !");
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
     * <ul>
     *         <li> le pion est placé sur une case blanche 
     *              ( détectable si x+y est pair ) </li>
     *         <li> x est supérieur à 9 ou inférieur à 0 </li>
     * </ul>
     */
    public void setX(int x) {
    	if ((x+y)%2 != 1) {
    		throw new IllegalArgumentException("Le pion est placé sur une "
    				                        + "case blanche !");
    	} else if (x < CASE_MIN_PLATEAU || x > CASE_MAX_PLATEAU ) {
        		throw new IllegalArgumentException("Le pion n'est pas sur le"
        				                                 + " damier !");
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
