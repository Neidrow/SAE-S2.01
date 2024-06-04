package test;

import modele.LogiqueJeu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogiqueJeuTest {
    private char[][] plateau;

    @BeforeEach
    void setUp() {
        LogiqueJeu.initialiserJeu();
        plateau = LogiqueJeu.getCopyOfPlateau();
    }


    @Test
    void testInitialiserJeu() {
        assertEquals('B', LogiqueJeu.getJoueurActif());
    }


}
