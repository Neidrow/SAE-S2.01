package modele;

import java.util.List;

import controleur.Mouvement;

public class LogiqueJeu {
    private static char[][] plateau;
    private static char joueurActif; // Variable pour suivre le joueur actif ('B' pour blanc, 'N' pour noir)

    public static void initialiserJeu() {
        // Initialiser le plateau de jeu avec l'état initial
        plateau = new char[10][10];
        joueurActif = 'B'; // Les blancs commencent
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 == 1 && i < 4) {
                    plateau[i][j] = 'N'; // Pions noirs
                } else if ((i + j) % 2 == 1 && i > 5) {
                    plateau[i][j] = 'B'; // Pions blancs
                } else {
                    plateau[i][j] = ' '; // Case vide
                }
            }
        }
        System.out.println("Jeu initialisé. Les blancs commencent.");
    }

    public static void mouvement(int x1, int y1, int x2, int y2) {
        // Permettre le mouvement d’un pion par le joueur actif
        if (verificationJoueur(x1, y1) && verificationMouvement(x1, y1, x2, y2)) {
            char pion = plateau[x1][y1];
            char pionCible = plateau[x2][y2];

            // Vérifier si le pion atteint la dernière rangée
            boolean estDerniereRangee = (pion == 'B' && x2 == 0) || (pion == 'N' && x2 == 9);

            // Effectuer le mouvement
            plateau[x2][y2] = plateau[x1][y1];
            plateau[x1][y1] = ' ';
            System.out.println("Mouvement effectué de (" + x1 + "," + y1 + ") à (" + x2 + "," + y2 + ").");

            // Si le pion atteint la dernière rangée, le transformer en dame
            if (estDerniereRangee) {
                plateau[x2][y2] = Character.toUpperCase(pion);
                System.out.println("Le pion à la position (" + x2 + "," + y2 + ") a été promu en dame.");
            }

            // Vérifier s'il y a une prise possible après le mouvement
            if (!mangerPion(x2, y2, x2, y2)) {
                passerJoueurSuivant();
            }
        } else {
            System.out.println("Mouvement invalide.");
        }
    }


    public static boolean mangerPion(int x1, int y1, int x2, int y2) {
        // Vérifier si le mouvement est valide pour une capture
        if (verificationJoueur(x1, y1) && verificationMouvement(x1, y1, x2, y2)) {
            char pion = plateau[x1][y1];
            char pionCible = plateau[x2][y2];

            // Vérifier si le pion cliqué est vide
            if (pion == ' ') {
                System.out.println("Mouvement invalide : pion cliqué est vide.");
                return false;
            }

            int midX = (x1 + x2) / 2;
            int midY = (y1 + y2) / 2;
            char ennemi = (pion == 'B') ? 'N' : 'B';

            // Vérifier si la pièce à capturer est un pion ennemi
            if (plateau[midX][midY] == ennemi) {
                // Effectuer la capture
                plateau[x2][y2] = pion;
                plateau[x1][y1] = ' ';
                plateau[midX][midY] = ' ';
                System.out.println("Pion " + ennemi + " capturé.");

                // Vérifier si le pion peut continuer à capturer dans toutes les directions
                boolean peutContinuerCapture = false;
                int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
                for (int[] direction : directions) {
                    int newX = x2 + 2 * direction[0];
                    int newY = y2 + 2 * direction[1];
                    if (verificationMouvement(x2, y2, newX, newY) && plateau[newX][newY] == ' ') {
                        peutContinuerCapture = true;
                        break;
                    }
                }

                if (peutContinuerCapture) {
                    // Permettre au joueur de continuer à capturer
                    // ...
                } else {
                    passerJoueurSuivant();
                }

                return true;
            }
        }
        return false;
    }


    public static boolean verificationJoueur(int x, int y) {
        // Vérifier que le joueur actif contrôle le pion sélectionné
        if (plateau[x][y] == joueurActif || plateau[x][y] == Character.toUpperCase(joueurActif)) {
            return true;
        } else {
            System.out.println("C'est au tour du joueur " + joueurActif + " de jouer.");
            return false;
        }
    }

    public static boolean verificationMouvement(int x1, int y1, int x2, int y2) {
        // Vérifier qu’un utilisateur peut faire un coup
        if (plateau == null) {
            throw new IllegalStateException("Le plateau n'est pas initialisé.");
        }
        if (x2 < 0 || x2 > 9 || y2 < 0 || y2 > 9 || plateau[x2][y2] != ' ') {
            return false;
        }

        char pion = plateau[x1][y1];
        char pionCible = plateau[x2][y2];
        
        // Vérifier si la case cible est vide
        if (pionCible != ' ') {
            return false;
        }

        if (pion == 'B' && x2 == x1 - 1 && Math.abs(y2 - y1) == 1) {
            return true; // Mouvement simple d'un pion blanc
        } else if (pion == 'N' && x2 == x1 + 1 && Math.abs(y2 - y1) == 1) {
            return true; // Mouvement simple d'un pion noir
        } else if (pion == 'B' || pion == 'N') {
            // Vérification des mouvements de capture
            if (Math.abs(x2 - x1) == 2 && Math.abs(y2 - y1) == 2) {
                int midX = (x1 + x2) / 2;
                int midY = (y1 + y2) / 2;
                char ennemi = (pion == 'B') ? 'N' : 'B';
                // Vérifier si la case intermédiaire contient un pion ennemi
                if (plateau[midX][midY] == ennemi) {
                    return true;
                } else {
                    // Sinon, le mouvement n'est pas valide
                    return false;
                }
            } else {
                // Pour les mouvements simples, vérifier que la case cible est vide
                return plateau[x2][y2] == ' ';
            }
        }
        return false;
    }

    public static boolean peutContinuerCapture(char pion, int x, int y) {
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] direction : directions) {
            int midX = x + direction[0];
            int midY = y + direction[1];
            int endX = x + 2 * direction[0];
            int endY = y + 2 * direction[1];
            if (estDansPlateau(endX, endY) && plateau[midX][midY] != ' ' && plateau[midX][midY] != pion &&
                    plateau[endX][endY] == ' ') {
                return true;
            }
        }
        return false;
    }

    private static boolean estDansPlateau(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    private static void passerJoueurSuivant() {
        joueurActif = (joueurActif == 'B') ? 'N' : 'B'; // Changer le joueur actif
        System.out.println("C'est au tour du joueur " + joueurActif + " de jouer.");
    }

    public static boolean verificationVictoire() {
        // Vérifier les conditions de victoire
        boolean blancPresent = false;
        boolean noirPresent = false;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (plateau[i][j] == 'B') {
                    blancPresent = true;
                } else if (plateau[i][j] == 'N') {
                    noirPresent = true;
                }
            }
        }
        if (!blancPresent) {
            System.out.println("Les noirs gagnent !");
            return true;
        } else if (!noirPresent) {
            System.out.println("Les blancs gagnent !");
            return true;
        }
        return false;
    }

    public static void chargerEtat(String etat) {
        // Charger l'état du jeu à partir d'une chaîne
        String[] lignes = etat.split("\n");
        for (int i = 0; i < lignes.length; i++) {
            String ligne = lignes[i];
            for (int j = 0; j < ligne.length(); j++) {
                char pion = ligne.charAt(j);
                plateau[i][j] = pion;
            }
        }
        System.out.println("État du jeu chargé.");
    }



    public static String obtenirEtat() {
        // Retourner l'état actuel du jeu sous forme de chaîne
        StringBuilder etat = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                etat.append(plateau[i][j]);
            }
            etat.append("\n");
        }
        return etat.toString();
    }

	public static List<Mouvement> getMouvementsPossibles(char[][] plateau2, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}

