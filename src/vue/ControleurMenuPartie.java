package vue;

import javafx.fxml.FXML;

public class ControleurMenuPartie {


    @SuppressWarnings("static-method")
    @FXML
    void buttonParametre() {
        Main.activerParametresEnJeu();
    }

    @SuppressWarnings("static-method")
    @FXML 
    void buttonRetour() {
        Main.activerPartie();;
    }

    @SuppressWarnings("static-method")
    @FXML
    void buttonQuitter() {
        Main.activerPrincipal();;
    }
}
