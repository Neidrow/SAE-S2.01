package modele;

import java.io.*;

public class GestionJeu {
    private Jeu jeuEnCours;
    private boolean partieSauvegardee;

    public void commencerJeu() {
        jeuEnCours = new Jeu();
        partieSauvegardee = false;
    }

    public void chargerPartieSauvegardee(String nomFichier) {
        try {
            FileInputStream fileIn = new FileInputStream(nomFichier);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            jeuEnCours = (Jeu) in.readObject();
            in.close();
            fileIn.close();
            partieSauvegardee = true;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void quitterJeu() {
        if (partieSauvegardee) {
            supprimerPartieSauvegardee("partie.sav");
        }
        System.exit(0);
    }

    public void confirmer() {
        if (partieSauvegardee) {
            supprimerPartieSauvegardee("partie.sav");
        }
        sauvegarderPartie("partie.sav");
    }

    public void annuler() {
    if (jeuEnCours != null) {
        jeuEnCours.annulerDernierCoup();
    }
}

    public void supprimerPartieSauvegardee(String nomFichier) {
        File fichier = new File(nomFichier);
        if (fichier.exists()) {
            fichier.delete();
            partieSauvegardee = false;
        }
    }

    private void sauvegarderPartie(String nomFichier) {
        try {
            FileOutputStream fileOut = new FileOutputStream(nomFichier);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(jeuEnCours);
            out.close();
            fileOut.close();
            partieSauvegardee = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
