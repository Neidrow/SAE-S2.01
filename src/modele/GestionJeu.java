package modele;

import java.io.*;

public class GestionJeu {
    private LogiqueJeu logiqueJeu;
    private String etatPartie;

    public GestionJeu() {
        logiqueJeu = new LogiqueJeu();
    }

    public void commencerJeu() {
        // Démarrer une nouvelle partie
        System.out.println("Nouvelle partie commencée.");
        logiqueJeu.initialiserJeu();
    }

    public void chargerPartieSauvegardee(String nomFichier) {
        // Charger une partie à partir d'un fichier spécifié
        try (BufferedReader br = new BufferedReader(new FileReader(nomFichier))) {
            etatPartie = br.readLine();
            System.out.println("Partie chargée depuis " + nomFichier);
            logiqueJeu.chargerEtat(etatPartie);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la partie: " + e.getMessage());
        }
    }

    public void quitterJeu() {
        // Quitter le jeu
        System.out.println("Le jeu est quitté.");
        System.exit(0);
    }

    public void confirmer() {
        // Confirmer une action
        System.out.println("Action confirmée.");
    }

    public void annuler() {
        // Annuler une action
        System.out.println("Action annulée.");
    }

    public void supprimerPartieSauvegardee(String nomFichier) {
        // Supprimer une partie sauvegardée spécifique
        File fichier = new File(nomFichier);
        if (fichier.delete()) {
            System.out.println("La partie " + nomFichier + " a été supprimée.");
        } else {
            System.out.println("Erreur lors de la suppression de la partie.");
        }
    }

    public void sauvegarderPartie(String nomFichier) {
        // Sauvegarder l'état actuel du jeu dans un fichier
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFichier))) {
            bw.write(logiqueJeu.obtenirEtat());
            System.out.println("Partie sauvegardée dans " + nomFichier);
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de la partie: " + e.getMessage());
        }
    }
}
