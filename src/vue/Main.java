/**
 * Main.java                                                   25/05/2024
 * Iut de Rodez, pas de copyright
 */
package vue;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <p>Classe principale de l'application.</p>
 * <p>Elle étend la classe javafx.application.Application et
 *  définit la méthode start() pour lancer l'application.</p>
 * <p>Cette classe crée une instance de Plateau et affiche une 
 * fenêtre graphique contenant ce plateau.</p>
 * <p>Elle crée également deux joueurs avec des couleurs
 *  différentes et les associe au plateau.</p>
 * <p>Le titre de la fenêtre est défini comme "Plateau de Dames".</p>
 * <p>La taille de la fenêtre est définie sur 1200x1000 pixels.</p>
 * <p>Pour lancer l'application, la méthode main appelle
 *  la méthode launch() de la classe Application avec les arguments fournis.</p>
 * 
 * @author Amjed SEHIL et Rodrigo TABORDA
 */
public class Main extends Application {

    private static Scene AffichagePrincipal;
    private static Scene AffichagePrincipalMenu;
    private static Scene Parametres;
    private static Scene Regles;
    private static Scene ChoisirPseudo;
    private static Scene Partie;
    private static Scene MenuPartie;
    private static Scene ParametresEnJeu;
    private static Stage fenetrePrincipale;

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerPrincipal() {
        fenetrePrincipale.setScene(AffichagePrincipal);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerAffichagePrincipalMenu() {
        fenetrePrincipale.setScene(AffichagePrincipalMenu);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerParametres() {
        fenetrePrincipale.setScene(Parametres);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerRegles() {
        fenetrePrincipale.setScene(Regles);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerChoixPseudo() {
        fenetrePrincipale.setScene(ChoisirPseudo);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerPartie() {
        fenetrePrincipale.setScene(Partie);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerMenuPartie() {
        fenetrePrincipale.setScene(MenuPartie);
    }

    /**
     * TODO commenter le rôle de cette méthode
     */
    public static void activerParametresEnJeu() {
        fenetrePrincipale.setScene(ParametresEnJeu);
    }

    /**
     * Méthode principale pour liée les interfaces.
     * 
     * @param primaryStage Le stage principal de l'application.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader chargeurFXMLAffichagePrincipal = new FXMLLoader();
            chargeurFXMLAffichagePrincipal.setLocation(getClass().getResource("AffichagePrincipal.fxml"));
            Parent conteneur = chargeurFXMLAffichagePrincipal.load();
            AffichagePrincipal = new Scene(conteneur);

            FXMLLoader chargeurFXMLAffichagePrincipalMenu = new FXMLLoader();
            chargeurFXMLAffichagePrincipalMenu.setLocation(getClass().getResource("AffichagePrincipalMenu.fxml"));
            conteneur = chargeurFXMLAffichagePrincipalMenu.load();
            AffichagePrincipalMenu = new Scene(conteneur);

            FXMLLoader chargeurFXMLParametres = new FXMLLoader();
            chargeurFXMLParametres.setLocation(getClass().getResource("Parametres.fxml"));
            conteneur = chargeurFXMLParametres.load();
            Parametres = new Scene(conteneur);

            FXMLLoader chargeurFXMLRegles = new FXMLLoader();
            chargeurFXMLRegles.setLocation(getClass().getResource("Regles.fxml"));
            conteneur = chargeurFXMLRegles.load();
            Regles = new Scene(conteneur);

            FXMLLoader chargeurFXMLChoisirPseudo = new FXMLLoader();
            chargeurFXMLChoisirPseudo.setLocation(getClass().getResource("ChoisirPseudo.fxml"));
            conteneur = chargeurFXMLChoisirPseudo.load();
            ChoisirPseudo = new Scene(conteneur);

            FXMLLoader chargeurFXMLPartie = new FXMLLoader();
            chargeurFXMLPartie.setLocation(getClass().getResource("Partie.fxml"));
            conteneur = chargeurFXMLPartie.load();
            Partie = new Scene(conteneur);

            FXMLLoader chargeurFXMLMenuPartie = new FXMLLoader();
            chargeurFXMLMenuPartie.setLocation(getClass().getResource("MenuPartie.fxml"));
            conteneur = chargeurFXMLMenuPartie.load();
            MenuPartie = new Scene(conteneur);

            FXMLLoader chargeurFXMLParametresEnJeu = new FXMLLoader();
            chargeurFXMLParametresEnJeu.setLocation(getClass().getResource("ParametresEnJeu.fxml"));
            conteneur = chargeurFXMLParametresEnJeu.load();
            ParametresEnJeu = new Scene(conteneur);

            // Définir le titre, la hauteur et la largeur de la fenêtre principale
            primaryStage.setTitle("Jeu de dames");

            // Définir explicitement la taille de la fenêtre principale
            primaryStage.setWidth(745);
            primaryStage.setHeight(572);

            primaryStage.setScene(AffichagePrincipal);
            fenetrePrincipale = primaryStage;
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
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
