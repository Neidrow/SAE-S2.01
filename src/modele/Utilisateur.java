/*
 * GestionUtilisateur.java 					25/05/2024
 * Iut de Rodez, pas de copyright
 */

package modele;

/**
 * Gère tout ce qui est lié à l'utilisateur, la définition du pseudo,
 * la gestion du trait ainsi que la sélection de la couleur du joueur
 * @author groupe 41
 */

public class Utilisateur {
	
	private String nomUtilisateur;
	
	private String camp;
	
	/** définit si c'est au tour du joueur, si oui true, sinon false */
	private boolean trait;
	
	/**
	 * 
	 * @param pseudo le nom d'utilisateur du joueur
	 * @param couleur la couleur des pions jouée par le joueur
	 * @throws IllegalArgumentException si couleur != noir ou blanc
	 *         ou si le pseudo est une chaine vide
	 */
    public Utilisateur(String pseudo, String couleur) {
    	if (pseudo.isBlank()) {
    		throw new IllegalArgumentException("Le pseudo est vide !");
    	}
    	if (couleur != "noir" && couleur != "blanc") {
    		throw new IllegalArgumentException("La couleur de l'équipe"
    				                           + "est invalide !");
    	}
        nomUtilisateur = pseudo;
        camp = couleur;
        trait = couleur == "blanc";
    }

    /**
     * Obtient le nom d'utilisateur du joueur
     * @return le nom d'utilisateur
     */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	
	/**
	 * Obtient le camp du joueur
	 * @return le camp du joueur
	 */
	public String getCamp() {
		return camp;
	}
	
	/**
	 * Demande si un joueur à le trait
	 * @return true si à le trait, sinon false
	 */
	public boolean getTrait() {
		return trait;
	}

	/*
	 * Permet de définir le tour du jour, true si c'est son tour
	 * false dans le cas contraire
	 * @param trait vrai si c'est le tour du joueur, sinon false
	 */
	public void setTrait(boolean trait) {
		this.trait = trait;
	}
}