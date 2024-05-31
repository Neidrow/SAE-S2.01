package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import vue.Ui;

class TestUi {

	@Test
	void testRafraichirPlateau() {
		fail("Not yet implemented");
	}

	@Test
	void testJoueurs() {
		fail("Not yet implemented");
	}

	@Test
	void testMessage() {
		fail("Not yet implemented");
	}

	@Test
	void testChronometre() {
		fail("Not yet implemented");
	}

	@Test
	void testAfficherOptions() {
		fail("Not yet implemented");
	}

	@Test
	void testTransformation() {
		assertEquals(false, Ui.transformation("noir", 0));
		assertEquals(false, Ui.transformation("noir", 4));
		assertEquals(true, Ui.transformation("noir", 9));
		assertEquals(true, Ui.transformation("blanc", 0));
		assertEquals(false, Ui.transformation("blanc", 4));
		assertEquals(false, Ui.transformation("blanc", 9));
	}

}
