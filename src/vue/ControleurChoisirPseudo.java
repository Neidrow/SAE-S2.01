package vue;

import javafx.fxml.FXML;

public class ControleurChoisirPseudo {

    @SuppressWarnings("static-method")
    @FXML
    void buttonRetour() {
        Main.activerPrincipal();
    }
    @SuppressWarnings("static-method")
    @FXML
    void buttonValider() {
        Main.activerPartie();
    }
}
