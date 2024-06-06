package vue;

import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * TODO commenter la responsabilité de cette classe (SRP)
 */
public class ControleurPrincipal {

    @SuppressWarnings("static-method")
    @FXML
    void actionMenu() {
        Main.activerAffichagePrincipalMenu();
    }

    @SuppressWarnings("static-method")
    @FXML 
    void actionParametres() {
        Main.activerParametres();
    }

    @SuppressWarnings("static-method")
    @FXML
    void actionPlay() {
        Main.activerChoixPseudo();
    }

    @SuppressWarnings("static-method")
    @FXML 
    void buttonQuitter() {
        Platform.exit();
    }

}
