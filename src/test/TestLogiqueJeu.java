package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.LogiqueJeu;

class TestLogiqueJeu {

	@Test
	void testLogiqueJeu() {
		assertDoesNotThrow(()-> new LogiqueJeu());
	}

	@Test
	void testInitialiserJeu() {
		assertDoesNotThrow(()-> new LogiqueJeu().initialiserJeu());
	}

	@Test
	void testMouvement() {
		new LogiqueJeu();
		assertDoesNotThrow(()-> LogiqueJeu.mouvement(2, 1, 1, 2));
		assertDoesNotThrow(()-> LogiqueJeu.mouvement(11, 12, 12, 11));
		assertDoesNotThrow(()-> LogiqueJeu.mouvement(-1, -2, -2, -1));
	}

	@Test
	void testVerificationVictoire() {
		fail("Not yet implemented");
	}

	@Test
	void testChargerEtat() {
		fail("Not yet implemented");
	}

	@Test
	void testObtenirEtat() {
		fail("Not yet implemented");
	}

}
