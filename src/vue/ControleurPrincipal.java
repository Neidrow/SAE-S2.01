package vue;

import javafx.fxml.FXML;

public class ControleurPrincipal {

	@FXML
    void actionMenu() {
        Main.activerAffichagePrincipalMenu();
    }
	
	@FXML 
	void actionParametres() {
		Main.activerParametres();
	}

}
