package vue;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import modele.Joueur;

/**
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class ControleurPartie {
        
        @FXML
        private Pane panneauJeu;

        @FXML
        private void initialize() {
            // Créer les joueurs
            Joueur joueur1 = new Joueur(Color.WHITE, "blanc");
            Joueur joueur2 = new Joueur(Color.BLACK, "noir");

            // Créer le plateau avec les joueurs
            Plateau plateau = new Plateau(10, joueur1, joueur2);

            // Ajouter le plateau au panneau de jeu
            panneauJeu.getChildren().add(plateau);
        }

        
        @SuppressWarnings({ "static-method" })
        @FXML
        void buttonMenu() {
                Main.activerMenuPartie();
        }
}
