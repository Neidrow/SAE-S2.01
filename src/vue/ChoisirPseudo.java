package vue;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Gère l'interface utilisateur pour choisir le pseudonyme des joueurs.
 */
public class ChoisirPseudo {

    @FXML
    private TextField pseudoJoueur1;

    @FXML
    private TextField pseudoJoueur2;

    /**
     * Récupère le pseudonyme saisi pour le joueur 1.
     * @return Le pseudonyme du joueur 1.
     */
    public String getPseudoJoueur1() {
        return pseudoJoueur1.getText();
    }

    /**
     * Récupère le pseudonyme saisi pour le joueur 2.
     * @return Le pseudonyme du joueur 2.
     */
    public String getPseudoJoueur2() {
        return pseudoJoueur2.getText();
    }
    
}
