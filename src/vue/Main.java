package vue;

import javafx.application.Application;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	
	private static Scene AffichagePrincipal;
	private static Scene AffichagePrincipalMenu;
	private static Scene Parametres;
	private static Stage fenetrePrincipale;
	
	public static void activerAffichagePrincipalMenu() {
        fenetrePrincipale.setScene(AffichagePrincipalMenu);
    }
	
	public static void activerParametres() {
        fenetrePrincipale.setScene(Parametres);
    }

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
             // on définit le titre, la hauteur et la largeur de la fenêtre principale
                primaryStage.setTitle("Jeu de dames");
                
                primaryStage.setScene(AffichagePrincipal);
                fenetrePrincipale = primaryStage;
                primaryStage.show();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
