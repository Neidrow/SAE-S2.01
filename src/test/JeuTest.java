/*package test;

import modele.Jeu;
import modele.LogiqueJeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

class JeuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Jeu jeu = new Jeu();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testCommencerJeu() {
        jeu.commencerJeu();
        assertEquals("Nouvelle partie commencée.\n", outContent.toString());
    }

    @Test
    void testChargerPartieSauvegardee() {
        jeu.chargerPartieSauvegardee("exemple.txt");
        // Assurez-vous ici que l'état du jeu est correctement chargé
    }

    @Test
    void testQuitterJeu() {
        jeu.quitterJeu();
        assertEquals("Le jeu est quitté.\n", outContent.toString());
    }

    @Test
    void testConfirmer() {
        jeu.confirmer();
        assertEquals("Action confirmée.\n", outContent.toString());
    }

    @Test
    void testAnnuler() {
        jeu.annuler();
        assertEquals("Action annulée.\n", outContent.toString());
    }

    @Test
    void testSupprimerPartieSauvegardee() {
        jeu.supprimerPartieSauvegardee("exemple.txt");
        // Assurez-vous ici que la partie est correctement supprimée
    }

    @Test
    void testSauvegarderPartie() {
        // Testez ici la sauvegarde d'une partie
        // Vous pouvez utiliser des mocks pour simuler le comportement de LogiqueJeu
    }
}*/
