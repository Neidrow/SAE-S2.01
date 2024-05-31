package vue;

public class Ui {
	public void rafraichirPlateau() {
		// Mettre à jour l'affichage du plateau de jeu
	}

	public void joueurs() {
		// Afficher les utilisateurs
	}

	public void message(String message) {
		// Guider/informer l’utilisateur
	}

	public void chronometre() {
		// Afficher le chronomètre et le temps restant
	}

	public void afficherOptions() {
		// Afficher les options disponibles dans l'interface
	}
	public static boolean transformation(String proprietaire, int x) {
    	
    	boolean transformationOk;
    	
        transformationOk = x == 9 && proprietaire == "noir";
        if (!transformationOk) {
        	transformationOk = x == 0 && proprietaire == "blanc";
        }
    	return transformationOk;
    }
}

