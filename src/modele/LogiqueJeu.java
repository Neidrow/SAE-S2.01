package modele;

import java.util.List;

import controleur.Mouvement;

public class LogiqueJeu {
	public static char[][] plateau;
	public static char joueurActif; // Variable pour suivre le joueur actif ('B' pour blanc, 'N' pour noir)

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
	public static char getJoueurActif() {
		return joueurActif;
	}
	public static char[][] getCopyOfPlateau() {
		char[][] copy = new char[10][10];
		for (int i = 0; i < 10; i++) {
			System.arraycopy(plateau[i], 0, copy[i], 0, 10);
		}
		return copy;
	}

	public static boolean mangerPion(char[][] plateau, int x1, int y1, int x2, int y2) {
		// Vérifier si le mouvement est valide pour une capture
		// Assurez-vous de remplacer les appels aux méthodes de vérification appropriées
		if (verificationJoueur(x1, y1) && verificationMouvement(x1, y1, x2, y2)) {
			char pion = plateau[x1][y1];

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

				// Calculer la direction de déplacement pour la capture
				int directionX = (x2 - x1) / Math.abs(x2 - x1);
				int directionY = (y2 - y1) / Math.abs(y2 - y1);
				int nextX = x2 + directionX;
				int nextY = y2 + directionY;

				// Vérifier si une prise supplémentaire est possible dans la même direction
				while (nextX >= 0 && nextX < 10 && nextY >= 0 && nextY < 10) {
					// Vérifier si la case suivante est vide
					if (plateau[nextX][nextY] == ' ') {
						break;
					}
					// Vérifier si la case suivante contient une pièce ennemie
					if (plateau[nextX][nextY] == ennemi) {
						// Effectuer la capture supplémentaire
						plateau[nextX][nextY] = pion;
						plateau[x2][y2] = ' ';
						plateau[midX][midY] = ' ';
						System.out.println("Pion " + ennemi + " capturé à (" + nextX + "," + nextY + ").");
						// Mettre à jour les coordonnées pour la prochaine vérification
						x1 = x2;
						y1 = y2;
						x2 = nextX;
						y2 = nextY;
						midX = (x1 + x2) / 2;
						midY = (y1 + y2) / 2;
						// Recalculer la direction pour la prochaine itération
						directionX = (x2 - x1) / Math.abs(x2 - x1);
						directionY = (y2 - y1) / Math.abs(y2 - y1);
						nextX = x2 + directionX;
						nextY = y2 + directionY;
					} else {
						// Si la case suivante contient une pièce du joueur actuel, terminer la boucle
						break;
					}
				}

				// Passer au joueur suivant une fois que toutes les prises ont été effectuées
				passerJoueurSuivant();

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

	public static boolean peutContinuerCapture(char[][] plateau, int x, int y) {
		char pion = plateau[x][y];
		int direction = (pion == 'N') ? 1 : -1;

		// Définir les directions possibles pour la capture
		int[][] directions = {{direction, -1}, {direction, 1}};

		// Parcourir toutes les directions pour vérifier s'il y a des prises possibles
		for (int[] dir : directions) {
			int midX = x + dir[0];
			int midY = y + dir[1];
			int endX = x + 2 * dir[0];
			int endY = y + 2 * dir[1];

			// Vérifier si la case intermédiaire contient un pion ennemi et si la case finale est vide
			if (estDansPlateau(endX, endY) && plateau[midX][midY] != ' ' && plateau[midX][midY] != pion &&
					plateau[endX][endY] == ' ') {
				return true; // Une prise est possible dans cette direction
			}
		}

		return false; // Aucune prise possible dans toutes les directions
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

	// TODO mettre dans Jeu
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


	// TODO mettre dans Jeu 
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

