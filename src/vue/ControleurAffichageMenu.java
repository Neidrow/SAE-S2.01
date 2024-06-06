package vue;

import javafx.fxml.FXML;

public class ControleurAffichageMenu {
        
        @SuppressWarnings("static-method")
        @FXML
        void buttonEngrenage() {
                Main.activerParametres();
        }
        
        @SuppressWarnings("static-method")
        @FXML 
        void actionRetour() {
                Main.activerPrincipal();
        }
        
        @SuppressWarnings("static-method")
        @FXML
        void actionRegles() {
                Main.activerRegles();
        }
        
}
