/*
 * GestionUtilisateur.java 					25/05/2024
 */

package modele;

import java.util.Scanner;

import controleur.Parametre;

/**
 * Gère tout ce qui est lié à l'utilisateur, la définition du pseudo,
 * la gestion du trait ainsi que la sélection de la couleur du joueur
 * @author groupe 41
 */

public class GestionUtilisateur {
	
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
    public GestionUtilisateur(String pseudo) {
    	if (pseudo.isBlank()) {
    		throw new IllegalArgumentException("Le pseudo est vide !");
    	}
        nomUtilisateur = pseudo;
        if (Math.random() >= 0.5) {
        	camp = "blanc";
        } else {
        	camp = "noir";
        }
        trait = camp == "blanc";
    }

    private Scanner scanner;

    public GestionUtilisateur() {
        scanner = new Scanner(System.in);
    }

    public String nomUtilisateur() {
        System.out.print("Entrez votre nom d'utilisateur : ");
        String nom = scanner.nextLine();
        return nom;
    }

    public void allerDansParametre() {
        Parametre parametre = new Parametre();
        parametre.gestionParametres();
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
