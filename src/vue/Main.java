/**
 * Main.java									25/05/2024
 * Iut de Rodez, pas de copyright
 */
package vue;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Joueur;

/**
 * <p>Classe principale de l'application.</p>
 * <p>Elle étend la classe javafx.application.Application et définit la méthode start() pour lancer l'application.</p>
 * <p>Cette classe crée une instance de Plateau et affiche une fenêtre graphique contenant ce plateau.</p>
 * <p>Elle crée également deux joueurs avec des couleurs différentes et les associe au plateau.</p>
 * <p>Le titre de la fenêtre est défini comme "Plateau de Dames".</p>
 * <p>La taille de la fenêtre est définie sur 1200x1000 pixels.</p>
 * <p>Pour lancer l'application, la méthode main appelle la méthode launch() de la classe Application avec les arguments fournis.</p>
 * 
 * @author Votre nom
 */
public class Main extends javafx.application.Application {

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param primaryStage Le stage principal de l'application.
     */
    @Override
    public void start(Stage primaryStage) {
        // Création des joueurs
        Joueur joueur2 = new Joueur(Color.BLACK, "Noir");
        Joueur joueur1 = new Joueur(Color.WHITE, "Blanc");

        // Création du plateau avec les joueurs
        Plateau plateau = new Plateau(10, joueur1, joueur2);
      
        // Création de la scène avec le plateau et définition de sa taille
        Scene scene = new Scene(plateau, 1200, 1000);

        // Définition du titre de la fenêtre
        primaryStage.setTitle("Plateau de Dames");
        
        // Définition de la scène dans le stage et affichage du stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Méthode principale pour lancer l'application.
     * 
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
