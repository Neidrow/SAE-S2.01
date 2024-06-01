	package controleur;
	
	import java.util.Scanner;
	import vue.Plateau;
	
	public class Parametre {
	
	    private Scanner scanner;
	
	    public Parametre() {
	        scanner = new Scanner(System.in);
	    }
	
	    public void regles() {
	        // Afficher les règles
	        System.out.println("Voici les règles du jeu.");
	        //TODO mettre un lien vers les regles
	    }
	
	    public void modifierTheme() {
	        System.out.println("Choisissez un nouveau thème de couleur :");
	        System.out.println("1. Thème classique (noir et blanc)");
	        System.out.println("2. Thème clair (blanc et gris)");
	        System.out.println("3. Thème sombre (noir et gris)");
	        System.out.print("Votre choix : ");
	
	        int choixTheme = scanner.nextInt();
	
	        switch (choixTheme) {
	            case 1:
	                thèmeClassique();
	                break;
	            case 2:
	                thèmeClair();
	                break;
	            case 3:
	                thèmeSombre();
	                break;
	            default:
	                System.out.println("Choix invalide. Veuillez réessayer.");
	                break;
	        }
	    }
	
	    private void thèmeClassique() {
	        System.out.println("Thème classique sélectionné.");
	        Plateau.themeClassique(null);
	    }
	
	    private void thèmeClair() {
	        System.out.println("Thème clair sélectionné.");
	        Plateau.themeClair(null);
	    }
	
	    private void thèmeSombre() {
	        System.out.println("Thème sombre sélectionné.");
	        Plateau.themeSombre(null);
	    }
	
	    public void preferenceGraphique() {
	        // Modifier l’image de fond, du plateau, les couleurs de pions
	        System.out.println("Modifiez vos préférences graphiques.");
	        // TODO Code pour la modification des préférences graphiques
	    }
	
	    public void emplacementSauvegarde() {
	        // Choisir l’emplacement des sauvegardes
	        System.out.println("Choisissez un nouvel emplacement pour les sauvegardes.");
	        // Code pour la modification de l'emplacement des sauvegardes
	    }
	
	    public void chronometrePartie() {
	        // Activer ou désactiver le chronomètre de partie
	        System.out.println("Activez ou désactivez le chronomètre de partie.");
	        // Code pour activer ou désactiver le chronomètre de partie
	    }
	
	    public void chronometreUtilisateur() {
	        // Activer ou désactiver le chronomètre utilisateur
	        System.out.println("Activez ou désactivez votre chronomètre personnel.");
	        // Code pour activer ou désactiver le chronomètre utilisateur
	    }
	
	    public void afficherMenu() {
	        System.out.println("Menu des paramètres :");
	        System.out.println("1. Afficher les règles");
	        System.out.println("2. Modifier le thème de couleur");
	        System.out.println("3. Modifier les préférences graphiques");
	        System.out.println("4. Choisir l’emplacement des sauvegardes");
	        System.out.println("5. Activer ou désactiver le chronomètre de partie");
	        System.out.println("6. Activer ou désactiver votre chronomètre personnel");
	        System.out.println("0. Quitter les paramètres");
	    }
	
	    public void gestionParametres() {
	        int choix;
	        do {
	            afficherMenu();
	            System.out.print("Entrez votre choix : ");
	            choix = scanner.nextInt();
	            if (choix == 1) {
	                regles();
	            } else if (choix == 2) {
	                modifierTheme();
	            } else if (choix == 3) {
	                preferenceGraphique();
	            } else if (choix == 4) {
	                emplacementSauvegarde();
	            } else if (choix == 5) {
	                chronometrePartie();
	            } else if (choix == 6) {
	                chronometreUtilisateur();
	            } else if (choix == 0) {
	                System.out.println("Retour au jeu.");
	            } else {
	                System.out.println("Choix invalide. Veuillez réessayer.");
	            }
	        } while (choix != 0);
	    }
	}
