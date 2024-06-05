/**
 * 
 */
package modele;

/**
 * 
 */
public class Utilisateur {
	
	private Joueur tourJoueur;
	private Joueur joueur1;
	private Joueur joueur2;
	
	public Utilisateur(Joueur joueur1, Joueur joueur2) {
		this.tourJoueur = joueur1;
		this.joueur1 = joueur1;
		this.joueur2 = joueur2;
	}
	
	public void changeTour() {
        if (tourJoueur == joueur1) {
            tourJoueur = joueur2;
        } else {
            tourJoueur = joueur1;
        }
        System.out.println("Tour du joueur : " + (tourJoueur == joueur1 ? joueur1.getName() : joueur2.getName()));
    }

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public Joueur getTourJoueur() {
		return tourJoueur;
	}

	/**
	 * @param 
	 */
	public void setTourJoueur(Joueur Joueur) {
		tourJoueur = joueur2;
		
	}

}