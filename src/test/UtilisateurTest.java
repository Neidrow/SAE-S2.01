package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import modele.Utilisateur;

class UtilisateurTest {

	/**
	 * Test method for {@link modele.GestionUtilisateur#GestionUtilisateur(String, String)}.
	 */
	@Test
	void testGestionUtilisateur() {
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("pseudo", "zef"));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("pseudo", ""));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("pseudo", " "));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("", "noir"));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur(" ", "blanc"));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("\n", "blanc"));
		assertThrows(IllegalArgumentException.class, 
				()-> new Utilisateur("\t", "noir"));
		assertDoesNotThrow(()-> new Utilisateur("enjien", "noir"));
		assertDoesNotThrow(()-> new Utilisateur("onil", "blanc"));
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#getNomUtilisateur()}.
	 */
	@Test
	void testGetNomUtilisateur() {
		assertEquals("enjien",
				new Utilisateur("enjien", "noir").getNomUtilisateur());
		assertEquals("onil",
				new Utilisateur("onil", "blanc").getNomUtilisateur());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#getCamp()}.
	 */
	@Test
	void testGetCamp() {
		assertEquals("noir",
				new Utilisateur("enjien", "noir").getCamp());
		assertEquals("blanc",
				new Utilisateur("onil", "blanc").getCamp());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#gtTrait()}.
	 */
	@Test
	void testGetTrait() {
		assertEquals(true,
				new Utilisateur("onil", "blanc").getTrait());
		assertEquals(false,
				new Utilisateur("enjien", "noir").getTrait());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#setTrait(boolean)}.
	 */
	@Test
	void testSetTrait() {
		assertDoesNotThrow(()-> new Utilisateur("onil", "blanc")
				           .setTrait(false));
		assertDoesNotThrow(()-> new Utilisateur("ENJIEN", "noir")
		           		   .setTrait(true));
	}

}