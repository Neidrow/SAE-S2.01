package vue;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;

/**
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class ControleurParametresEnJeu {

        @FXML
        private ColorPicker colorPicker;
        

        @FXML 
        void buttonRetour() {
                Main.activerMenuPartie();
        }

}
