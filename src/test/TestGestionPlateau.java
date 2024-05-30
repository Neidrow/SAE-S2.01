package test;

import static org.junit.jupiter.api.Assertions.*;

import vue.GestionPlateau;
import org.junit.jupiter.api.Test;

class TestGestionPlateau {

	@Test
	void testTransformation() {
		assertEquals(false, GestionPlateau.transformation("noir", 0));
		assertEquals(false, GestionPlateau.transformation("noir", 4));
		assertEquals(true, GestionPlateau.transformation("noir", 9));
		assertEquals(true, GestionPlateau.transformation("blanc", 0));
		assertEquals(false, GestionPlateau.transformation("blanc", 4));
		assertEquals(false, GestionPlateau.transformation("blanc", 9));
	}

}
