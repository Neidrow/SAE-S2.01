package vue;

import javafx.fxml.FXML;

public class ControleurAffichageMenu {
	
	@FXML
	void buttonEngrenage() {
		Main.activerParametres();
	}
	
	@FXML 
	void actionRetour() {
		Main.activerPrincipal();
	}
	
	@FXML
	void actionRegles() {
		Main.activerRegles();
	}
	
}
