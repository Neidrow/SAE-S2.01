package modele;

public class LogiqueJeu {
    private static char[][] plateau;

    public LogiqueJeu() {
        initialiserJeu();
    }

    public void initialiserJeu() {
        // Initialiser le plateau de jeu avec l'état initial
        plateau = new char[10][10];
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
        System.out.println("Jeu initialisé.");
    }

    public static void mouvement(int x1, int y1, int x2, int y2) {
        // Permettre le mouvement d’un pion par le joueur
        if (verificationMouvement(x1, y1, x2, y2)) {
            plateau[x2][y2] = plateau[x1][y1];
            plateau[x1][y1] = ' ';
            System.out.println("Mouvement effectué de (" + x1 + "," + y1 + ") à (" + x2 + "," + y2 + ").");
        } else {
            System.out.println("Mouvement invalide.");
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
                if (plateau[midX][midY] == ennemi) {
                    plateau[midX][midY] = ' ';
                    return true;
                }
            }
        }
        return false;
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

    public void chargerEtat(String etat) {
        // Charger l'état du jeu à partir d'une chaîne
        String[] lignes = etat.split("\n");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                plateau[i][j] = lignes[i].charAt(j);
            }
        }
        System.out.println("État du jeu chargé.");
    }

    public String obtenirEtat() {
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
}
