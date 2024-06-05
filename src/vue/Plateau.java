package vue;

import java.awt.Label;
import java.util.List;
import controleur.Mouvement;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.LogiqueJeu;

public class Plateau {
	private static final int SIZE = 10; // Taille de la grille
	private char[][] plateau;
	private Label labelMessage;
	private Label labelChronometre;
	private int minutes = 10;
	private int secondes = 0;
	private GridPane grid; // Ajout du champ GridPane
	private int selectedX = -1;
	private int selectedY = -1;
	private Circle selectedPion = null; // Ajout d'un champ pour le pion sélectionné
	private char joueurActuel = 'B'; // Blancs commencent généralement

	public Plateau() {
		this.plateau = new char[SIZE][SIZE]; // Initialise un plateau vide pour l'exemple
		initialiserPlateau();
		this.labelMessage = new Label(); // Initialisation du labelMessage
		this.labelChronometre = new Label(); // Initialisation du labelChronometre
		LogiqueJeu jeu = new LogiqueJeu(); // Créez une instance de LogiqueJeu
		LogiqueJeu.initialiserJeu(); // Initialisez le jeu
	}

	// TODO A changer par du FXML
	public void initialiserPlateau() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if ((i + j) % 2 == 0 && i < 4) {
					plateau[i][j] = 'N'; // Pions noirs
				} else if ((i + j) % 2 == 0 && i > 5) {
					plateau[i][j] = 'B'; // Pions blancs
				} else {
					plateau[i][j] = ' '; // Case vide
				}
			}
		}
	}

	// TODO fxml
	public BorderPane creerContenu(Stage primaryStage) {
		BorderPane root = new BorderPane();

		// Options
		VBox vboxOptions = new VBox(); // Utilisez VBox au lieu de HBox
		vboxOptions.setAlignment(Pos.CENTER); // Centrer verticalement
		vboxOptions.setSpacing(10); // Espacement entre les boutons

		Button btnNouvellePartie = new Button("Nouvelle Partie");
		btnNouvellePartie.setOnAction(e -> {
			message("Nouvelle partie commencée.");

			// Remplacer le contenu de la scène par l'écran de jeu
			BorderPane ecranJeu = creerEcranJeu();
			root.setCenter(ecranJeu); // Ajoutez l'écran de jeu à la racine BorderPane

			// Ajouter la création du plateau avec les pions
			creerPlateau();
		});

		Button btnQuitter = new Button("Quitter");
		btnQuitter.setOnAction(e -> primaryStage.close());

		vboxOptions.getChildren().addAll(btnNouvellePartie, btnQuitter);
		root.setCenter(vboxOptions);

		return root;
	}

	private BorderPane creerEcranJeu() {
		BorderPane ecranJeu = new BorderPane();

		// Initialisation de la grille
		if (grid == null) {
			grid = new GridPane();
			grid.setAlignment(Pos.CENTER); // Centrer le plateau horizontalement et verticalement
		}
		ecranJeu.setCenter(grid);

		// Création du plateau de jeu
		creerPlateau();

		// Autres éléments à ajouter à l'écran de jeu si nécessaire

		return ecranJeu;
	}

	private void creerPlateau() {
		grid.getChildren().clear();

		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = new Rectangle(50, 50);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.BLACK);
				} else {
					cell.setFill(Color.WHITE);
				}
				grid.add(cell, col, row);

				if (plateau[row][col] == 'N') {
					Circle pion = afficherPion(row, col, "noir", false);
					evenementPions(pion, row, col);
				} else if (plateau[row][col] == 'B') {
					Circle pion = afficherPion(row, col, "blanc", false);
					evenementPions(pion, row, col);
				}
			}
		}
	}

	public void deplacerPion(int newX, int newY) {
	    if (selectedX != -1 && selectedY != -1 && plateau[selectedX][selectedY] == joueurActuel) {
	        char pion = plateau[selectedX][selectedY];
	        boolean estDame = Character.isUpperCase(pion);

	        if (estDame) {
	            if (peutDeplacerDame(newX, newY)) {
	                effectuerDeplacementDame(newX, newY);
	            }
	        } else {
	            int direction = (pion == 'N') ? 1 : -1;

	            if ((newX - selectedX) == direction && Math.abs(newY - selectedY) == 1) {
	                plateau[newX][newY] = plateau[selectedX][selectedY];
	                plateau[selectedX][selectedY] = ' ';

	                // Si c'est un mouvement de capture
	                if (Math.abs(newX - selectedX) == 2 && Math.abs(newY - selectedY) == 2) {
	                    // Coordonnées de la case mangée
	                    int midX = (selectedX + newX) / 2;
	                    int midY = (selectedY + newY) / 2;
	                    // Vérifier si la case mangée contient un pion adverse
	                    char pionMange = plateau[midX][midY];
	                    if ((pion == 'N' && Character.toUpperCase(pionMange) == 'B') || (pion == 'B' && Character.toUpperCase(pionMange) == 'N')) {
	                        // Retirer le pion mangé
	                        plateau[midX][midY] = ' ';
	                        // Déplacer le pion mangeur à la nouvelle position
	                        plateau[newX][newY] = pion;
	                    } else {
	                        // Annuler le mouvement si la case mangée ne contient pas un pion adverse
	                        plateau[selectedX][selectedY] = pion;
	                        plateau[newX][newY] = ' ';
	                    }
	                }

	                // Si le joueur atteint le bord opposé, transformer le pion en dame
	                if ((joueurActuel == 'B' && newX == 0) || (joueurActuel == 'N' && newX == SIZE - 1)) {
	                    plateau[newX][newY] = Character.toUpperCase(plateau[newX][newY]);
	                    transformation(joueurActuel, newX); // Appel avec deux arguments
	                }

	                rafraichirPlateau();

	                if (LogiqueJeu.peutContinuerCapture(LogiqueJeu.plateau, newX, newY)) {
	                    selectedX = newX;
	                    selectedY = newY;
	                } else {
	                    selectedX = -1;
	                    selectedY = -1;
	                    if (selectedPion != null) {
	                        selectedPion.setStroke(Color.GRAY);
	                    }
	                    selectedPion = null;
	                    passerAuJoueurSuivant();
	                }
	            }
	        }
	    }
	}

	private boolean peutDeplacerDame(int newX, int newY) {
	    int deltaX = newX - selectedX;
	    int deltaY = newY - selectedY;

	    if (Math.abs(deltaX) != Math.abs(deltaY)) {
	        return false;
	    }

	    int stepX = Integer.signum(deltaX);
	    int stepY = Integer.signum(deltaY);

	    int x = selectedX + stepX;
	    int y = selectedY + stepY;

	    boolean foundOpponent = false;

	    while (x != newX && y != newY) {
	        char current = plateau[x][y];
	        if (current != ' ') {
	            if (foundOpponent || (joueurActuel == 'B' && Character.toUpperCase(current) == 'B') || (joueurActuel == 'N' && Character.toUpperCase(current) == 'N')) {
	                return false;
	            }
	            foundOpponent = true;
	        }
	        x += stepX;
	        y += stepY;
	    }

	    return true;
	}

	private void effectuerDeplacementDame(int newX, int newY) {
	    int deltaX = newX - selectedX;
	    int deltaY = newY - selectedY;

	    int stepX = Integer.signum(deltaX);
	    int stepY = Integer.signum(deltaY);

	    int x = selectedX + stepX;
	    int y = selectedY + stepY;

	    boolean foundOpponent = false;

	    while (x != newX && y != newY) {
	        char current = plateau[x][y];
	        if (current != ' ') {
	            plateau[x][y] = ' ';
	            foundOpponent = true;
	        }
	        x += stepX;
	        y += stepY;
	    }

	    plateau[newX][newY] = plateau[selectedX][selectedY];
	    plateau[selectedX][selectedY] = ' ';
	    rafraichirPlateau();

	    if (foundOpponent && LogiqueJeu.peutContinuerCapture(LogiqueJeu.plateau, newX, newY)) {
	        selectedX = newX;
	        selectedY = newY;
	    } else {
	        selectedX = -1;
	        selectedY = -1;
	        if (selectedPion != null) {
	            selectedPion.setStroke(Color.GRAY);
	        }
	        selectedPion = null;
	        passerAuJoueurSuivant();
	    }
	}


	public void passerAuJoueurSuivant() {
		joueurActuel = (joueurActuel == 'N') ? 'B' : 'N';
		System.out.println("C'est au tour des " + (joueurActuel == 'N' ? "noirs" : "blancs"));
	}


	public void evenementPions(Circle pion, int x, int y) {
	    pion.setOnMouseClicked(event -> {
	        if (selectedPion == null) {
	            // Si aucun pion n'est sélectionné, sélectionnez le pion actuel
	            selectedX = x;
	            selectedY = y;
	            selectedPion = pion;
	            pion.setStroke(Color.RED); // Indiquer la sélection en changeant la bordure

	            // Ajouter un événement de clic pour chaque case de la grille
	            for (Node node : grid.getChildren()) {
	                if (node instanceof Rectangle) {
	                    Rectangle cell = (Rectangle) node;
	                    cell.setOnMouseClicked(moveEvent -> {
	                        int newX = GridPane.getRowIndex(cell);
	                        int newY = GridPane.getColumnIndex(cell);
	                        deplacerPion(newX, newY);
	                    });
	                }
	            }
	        } else if (selectedX == x && selectedY == y) {
	            // Si le pion sélectionné est cliqué à nouveau, désélectionnez-le
	            selectedX = -1;
	            selectedY = -1;
	            selectedPion.setStroke(Color.GRAY); // Restaurer la bordure grise
	            selectedPion = null;
	        } else {
	            // Désélectionner l'ancien pion
	            selectedPion.setStroke(Color.GRAY); // Restaurer la bordure grise de l'ancien pion

	            // Mettre à jour la sélection au nouveau pion
	            selectedX = x;
	            selectedY = y;
	            selectedPion = pion;
	            pion.setStroke(Color.RED); // Indiquer la sélection en changeant la bordure

	            // Vérifier si le joueur peut capturer une pièce adverse
	            if (LogiqueJeu.peutContinuerCapture(LogiqueJeu.plateau, x, y)) {
	                // Forcer le joueur à capturer la pièce adverse
	                message("Vous devez capturer une pièce adverse !");
	            }
	        }
	    });
	}


	public void afficherMouvementsPossibles(List<Mouvement> mouvementsPossibles) {
		// Afficher visuellement les mouvements possibles pour le pion sélectionné
		if (mouvementsPossibles != null && selectedX != -1 && selectedY != -1) {
			for (Mouvement mouvement : mouvementsPossibles) {
				int x = mouvement.getX();
				int y = mouvement.getY();
				// Affichez visuellement le mouvement possible en changeant la couleur de la case
				Rectangle cell = (Rectangle) grid.getChildren().get(x * SIZE + y);
				cell.setFill(Color.LIGHTGREEN); // Changez la couleur de fond de la case en vert clair
				// Ajoutez un gestionnaire d'événements de clic à la case pour permettre le déplacement
				cell.setOnMouseClicked(event -> {
					// Déplacez le pion sélectionné vers la nouvelle position
					deplacerPion(x, y);
				});
			}
		}
	}

	public static void themeClassique(GridPane grid) {
		System.out.println("Thème classique sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				StackPane cell = (StackPane) grid.getChildren().get(row * SIZE + col);
				Rectangle rectangle = (Rectangle) cell.getChildren().get(0);
				if ((row + col) % 2 == 0) {
					rectangle.setFill(Color.WHITE);
				} else {
					rectangle.setFill(Color.BLACK);
				}
			}
		}
	}

	public static void themeSombre(GridPane grid) {
		System.out.println("Thème sombre sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				StackPane cell = (StackPane) grid.getChildren().get(row * SIZE + col);
				Rectangle rectangle = (Rectangle) cell.getChildren().get(0);
				if ((row + col) % 2 == 0) {
					rectangle.setFill(Color.BLACK);
				} else {
					rectangle.setFill(Color.DARKGRAY);
				}
			}
		}
	}

	public static void themeClair(GridPane grid) {
		System.out.println("Thème clair sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				StackPane cell = (StackPane) grid.getChildren().get(row * SIZE + col);
				Rectangle rectangle = (Rectangle) cell.getChildren().get(0);
				if ((row + col) % 2 == 0) {
					rectangle.setFill(Color.WHITE);
				} else {
					rectangle.setFill(Color.LIGHTGRAY);
				}
			}
		}
	}

	public void rafraichirPlateau() {
	    grid.getChildren().clear();
	    for (int row = 0; row < SIZE; row++) {
	        for (int col = 0; col < SIZE; col++) {
	            Rectangle cell = new Rectangle(50, 50);
	            if ((row + col) % 2 == 0) {
	                cell.setFill(Color.BLACK);
	            } else {
	                cell.setFill(Color.WHITE);
	            }
	            grid.add(cell, col, row);

	            char pion = plateau[row][col];
	            if (pion != ' ') {
	                boolean estDame = (pion == 'N' && row == SIZE - 1) || (pion == 'B' && row == 0);
	                Circle circlePion = afficherPion(row, col, (pion == 'N' || pion == 'n') ? "noir" : "blanc", estDame);
	                evenementPions(circlePion, row, col);
	                if (estDame) {
	                    circlePion.setStroke(Color.GOLD); // Appliquer la bordure dorée aux dames
	                    circlePion.setStrokeWidth(3);
	                } else {
	                    circlePion.setStroke(Color.GRAY);
	                }
	            }
	        }
	    }
	}


	public Circle afficherPion(int x, int y, String couleur, boolean estDame) {
	    Circle pion = new Circle(20); // Créez un cercle pour représenter le pion
		    pion.setFill(couleur.equals("noir") ? Color.BLACK : Color.WHITE); // Couleur du pion en fonction du propriétaire
		    pion.setStrokeWidth(3);
	    if (estDame) {
	        pion.setStroke(Color.GOLD); // Bordure dorée pour indiquer une dame
	    } else {
	        pion.setStroke(Color.GRAY); // Bordure grise pour un pion ordinaire
	    }
	    grid.add(pion, y, x); // Ajoutez le pion à la position spécifiée sur le plateau de jeu
	    GridPane.setHalignment(pion, HPos.CENTER); // Centrer horizontalement
	    GridPane.setValignment(pion, VPos.CENTER); // Centrer verticalement
	    return pion;
	}

	public static boolean transformation(char proprietaire, int x) {
	    boolean transformationOk = (x == 9 && proprietaire == 'N') || (x == 0 && proprietaire == 'B');
	    return transformationOk;
	}


	private void message(String message) {
		labelMessage.setText(message);
	}
}
