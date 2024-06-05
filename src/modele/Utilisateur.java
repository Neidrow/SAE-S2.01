/**
 * Utilisateur.java					28/05/2024
 * Iut de Rodez, pas de copyright
 */
package modele;

/**
 * <p>Représente un utilisateur du jeu.</p>
 * <p>Un utilisateur est associé à deux joueurs et gère 
 * le tour de jeu.</p>
 * @author Amjed SEHIL et Rodrigo TABORDA
 */

public class Utilisateur {
	
	private Joueur tourJoueur;
	private Joueur joueur1;
	private Joueur joueur2;
	
	/**
	 * <p>Initialise un utilisateur avec 
	 * les deux joueurs spécifiés.</p>
	 * 
	 * @param joueur1 Le premier joueur.
	 * @param joueur2 Le deuxième joueur.
	 */
	public Utilisateur(Joueur joueur1, Joueur joueur2) {
		this.tourJoueur = joueur1;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}
	
	/**
	 * <p>Change le tour de jeu en passant au joueur suivant.</p>
	 * <p>Si le tour est actuellement attribué à joueur1, 
	 * il sera attribué à joueur2 et vice versa.</p>
	 */
	public void changeTour() {
        if (tourJoueur == joueur1) {
            tourJoueur = joueur2;
        } else {
            tourJoueur = joueur1;
        }
        System.out.println("Tour du joueur : " + 
        (tourJoueur == joueur1 ? joueur1.getName() : joueur2.getName()));
    }

	/**
	 * <p>Renvoie le premier joueur associé à l'utilisateur.</p>
	 * 
	 * @return Le premier joueur.
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}

	/**
	 * <p>Renvoie le deuxième joueur associé à l'utilisateur.</p>
	 * 
	 * @return Le deuxième joueur.
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}

	/**
	 * <p>Renvoie le joueur dont c'est actuellement le tour.</p>
	 * 
	 * @return Le joueur dont c'est actuellement le tour.
	 */
	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	/**
	 * <p>Définit le joueur dont c'est actuellement le tour.</p>
	 * 
	 * @param joueur Le joueur dont c'est actuellement le tour.
	 */
	public void setTourJoueur(Joueur joueur) {
		tourJoueur = joueur2;
		
	}

}