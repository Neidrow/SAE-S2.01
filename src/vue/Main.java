package vue;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Joueur;

public class Main extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        Joueur joueur2 = new Joueur(Color.BLACK, "Noir");
        Joueur joueur1 = new Joueur(Color.WHITE, "Blanc");

        Plateau plateau = new Plateau(10, joueur1, joueur2);

        Scene scene = new Scene(plateau, 1200, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Plateau de Dames");
        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}