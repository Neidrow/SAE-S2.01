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

	/**
	 * Test method for {@link modele.GestionUtilisateur#getNomUtilisateur()}.
	 */
	@Test
	void testGetNomUtilisateur() {
		assertEquals("enjien",
				new GestionUtilisateur("enjien").getNomUtilisateur());
		assertEquals("onil",
				new GestionUtilisateur("onil").getNomUtilisateur());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#getCamp()}.
	 */
	@Test
	void testGetCamp() {
		assertEquals("noir",
				new GestionUtilisateur("enjien").getCamp());
		assertEquals("blanc",
				new GestionUtilisateur("onil").getCamp());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#gtTrait()}.
	 */
	@Test
	void testGetTrait() {
		assertEquals(true,
				new GestionUtilisateur("onil").getTrait());
		assertEquals(false,
				new GestionUtilisateur("enjien").getTrait());
	}

	/**
	 * Test method for {@link modele.GestionUtilisateur#setTrait(boolean)}.
	 */
	@Test
	void testSetTrait() {
		assertDoesNotThrow(()-> new GestionUtilisateur("onil")
				           .setTrait(false));
		assertDoesNotThrow(()-> new GestionUtilisateur("ENJIEN")
		           		   .setTrait(true));
	}

}
