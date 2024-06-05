package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.GestionUtilisateur;

class TestGestionUtilisateur {

	@Test
	void testGestionUtilisateurString() {
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur(""));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur(" "));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("\n"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("\t"));
		assertDoesNotThrow(()-> new GestionUtilisateur("enjien"));
		assertDoesNotThrow(()-> new GestionUtilisateur("onil"));
	}

	@Test
	void testGestionUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	void testNomUtilisateur() {
		fail("Not yet implemented");
	}

	@Test
	void testAllerDansParametre() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNomUtilisateur() {
		assertEquals("enjien",
				new GestionUtilisateur("enjien").getNomUtilisateur());
		assertEquals("onil",
				new GestionUtilisateur("onil").getNomUtilisateur());
	}

	@Test
	void testGetCamp() {
		assertEquals("noir",
				new GestionUtilisateur("enjien").getCamp());
		assertEquals("blanc",
				new GestionUtilisateur("onil").getCamp());
	}

	@Test
	void testGetTrait() {
		assertEquals(true,
				new GestionUtilisateur("onil").getTrait());
		assertEquals(false,
				new GestionUtilisateur("enjien").getTrait());
	}

	@Test
	void testSetTrait() {
		assertDoesNotThrow(()-> new GestionUtilisateur("onil")
		           .setTrait(false));
		assertDoesNotThrow(()-> new GestionUtilisateur("ENJIEN")
        		   .setTrait(true));
	}

}
