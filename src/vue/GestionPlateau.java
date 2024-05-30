/*
 * GestionJeu.java               30 mai 2024
 * IUT de Rodez, pas copyright
 */
package vue;

/**
 * Gérer tout évenements liés a plateau, par exemple la transformation
 * d'un pion en dame
 * @author Groupe 41, Adrien Vigué
 */
public class GestionPlateau {
	
	/**
	 * Vérifie si un pion rempli les conditions pour se transformer en
	 * dames
	 * @param proprietaire le proprietaire du pion appelant
	 * @param x la ligne à laquelle se trouve le pion
	 * @return true si le pion se tranforme, sinon false
	 */
    public static boolean transformation(String proprietaire, int x) {
    	
    	boolean transformationOk;
    	
        transformationOk = x == 9 && proprietaire == "noir";
        if (!transformationOk) {
        	transformationOk = x == 0 && proprietaire == "blanc";
        }
    	return transformationOk;
    }
}