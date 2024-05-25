package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.GestionUtilisateur;

class TestGestionUtilisateur {

	/**
	 * Test method for {@link modele.GestionUtilisateur#GestionUtilisateur(String, String)}.
	 */
	@Test
	void testGestionUtilisateur() {
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo", "zef"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo", ""));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("pseudo", " "));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("", "noir"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur(" ", "blanc"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("\n", "blanc"));
		assertThrows(IllegalArgumentException.class, 
				()-> new GestionUtilisateur("\t", "noir"));
		assertDoesNotThrow(()-> new GestionUtilisateur("enjien", "noir"));
		assertDoesNotThrow(()-> new GestionUtilisateur("onil", "blanc"));
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#getNomUtilisateur()}.
	 */
	@Test
	void testGetNomUtilisateur() {
		assertEquals("enjien",
				new GestionUtilisateur("enjien", "noir").getNomUtilisateur());
		assertEquals("onil",
				new GestionUtilisateur("onil", "blanc").getNomUtilisateur());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#getCamp()}.
	 */
	@Test
	void testGetCamp() {
		assertEquals("noir",
				new GestionUtilisateur("enjien", "noir").getCamp());
		assertEquals("blanc",
				new GestionUtilisateur("onil", "blanc").getCamp());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#gtTrait()}.
	 */
	@Test
	void testGetTrait() {
		assertEquals(true,
				new GestionUtilisateur("onil", "blanc").getTrait());
		assertEquals(false,
				new GestionUtilisateur("enjien", "noir").getTrait());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#setTrait(boolean)}.
	 */
	@Test
	void testSetTrait() {
		assertDoesNotThrow(()-> new GestionUtilisateur("onil", "blanc")
				           .setTrait(false));
		assertDoesNotThrow(()-> new GestionUtilisateur("ENJIEN", "noir")
		           		   .setTrait(true));
	}

}
