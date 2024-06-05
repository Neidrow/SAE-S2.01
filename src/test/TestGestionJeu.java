package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import modele.GestionJeu;

class TestGestionJeu {

	@Test
	void testGestionJeu() {
		assertDoesNotThrow(()-> new GestionJeu());
	}

	@Test
	void testCommencerJeu() {
		assertDoesNotThrow(()-> new GestionJeu().commencerJeu());
	}

	@Test
	void testChargerPartieSauvegardee() {
		new GestionJeu().sauvegarderPartie("sauvegarde.dame");
		assertThrows(IllegalArgumentException.class,
			()-> new GestionJeu().chargerPartieSauvegardee("test.txt"));
		assertThrows(IOException.class,
			()-> new GestionJeu().chargerPartieSauvegardee("test.dame"));
		assertDoesNotThrow(
			()-> new GestionJeu().chargerPartieSauvegardee("sauvegarde.dame"));
	}

	@Test
	void testConfirmer() {
		assertDoesNotThrow(()-> new GestionJeu().confirmer());
	}

	@Test
	void testAnnuler() {
		assertDoesNotThrow(()-> new GestionJeu().annuler());
	}

	@Test
	void testSupprimerPartieSauvegardee() {
		new GestionJeu().sauvegarderPartie("sauvegarde.dame");
		assertThrows(IllegalArgumentException.class, ()-> new GestionJeu()
				     .supprimerPartieSauvegardee("sauvegarde.txt"));
		assertDoesNotThrow(()-> new GestionJeu()
		                   .supprimerPartieSauvegardee("sauvegarde2.dame"));
		assertDoesNotThrow(()-> new GestionJeu()
                           .supprimerPartieSauvegardee("sauvegarde.dame"));
	}

	@Test
	void testSauvegarderPartie() {
		assertDoesNotThrow(()-> new GestionJeu()
				           .sauvegarderPartie("sauvegarde.dame"));
		assertDoesNotThrow(()-> new GestionJeu()
		                  .sauvegarderPartie("sauvegarde"));
		assertDoesNotThrow(()-> new GestionJeu()
                .sauvegarderPartie("/sauvegarde/dame"));
	}

}
