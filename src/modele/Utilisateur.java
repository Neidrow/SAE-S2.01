package modele;

import java.util.Scanner;

import controleur.Parametre;

public class Utilisateur {

    private Scanner scanner;

    public Utilisateur() {
        scanner = new Scanner(System.in);
    }

    public String nomUtilisateur() {
        System.out.print("Entrez votre nom d'utilisateur : ");
        String nom = scanner.nextLine();
        return nom;
    }

    public void allerDansParametre() {
        Parametre parametre = new Parametre();
        parametre.gestionParametres();
    }
}
