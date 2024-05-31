package vue;

import java.awt.Label;
import java.util.List;
import controleur.Mouvement;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import modele.LogiqueJeu;

public class Ui {
	private static final int SIZE = 10; // Taille de la grille
	private char[][] plateau;
	private Label labelMessage;
	private Label labelChronometre;
	private int minutes = 10;
	private int seconds = 0;
	private GridPane grid; // Ajout du champ GridPane
	private int selectedX = -1;
	private int selectedY = -1;
	private Circle selectedPion = null; // Ajout d'un champ pour le pion sélectionné

	public Ui() {
		this.plateau = new char[SIZE][SIZE]; // Initialise un plateau vide pour l'exemple
		initialiserPlateau();
		this.labelMessage = new Label(); // Initialisation du labelMessage
		this.labelChronometre = new Label(); // Initialisation du labelChronometre
		LogiqueJeu jeu = new LogiqueJeu(); // Créez une instance de LogiqueJeu
		jeu.initialiserJeu(); // Initialisez le jeu
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
		// Création de la grille
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = new Rectangle(50, 50);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.WHITE);
				} else {
					cell.setFill(Color.BLACK);
				}
				grid.add(cell, col, row);
			}
		}

		// Ajout des pions après les rectangles
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (plateau[row][col] == 'N') {
					Circle pion = afficherPion(row, col, "noir");
					evenementPions(pion, row, col);
				} else if (plateau[row][col] == 'B') {
					Circle pion = afficherPion(row, col, "blanc");
					evenementPions(pion, row, col);
				}
			}
		}
	}
	public void deplacerPion(int newX, int newY) {
	    // Vérifiez si un pion est sélectionné
	    if (selectedX != -1 && selectedY != -1) {
	        char pion = plateau[selectedX][selectedY];
	        int direction = (pion == 'N') ? 1 : -1; // Direction de déplacement selon le propriétaire du pion

	        // Vérifiez si le déplacement est valide
	        if ((newX - selectedX) == direction && Math.abs(newY - selectedY) == 1) {
	            // Déplacez le pion sélectionné vers la nouvelle position (newX, newY)
	            plateau[newX][newY] = plateau[selectedX][selectedY];
	            plateau[selectedX][selectedY] = ' '; // Videz l'ancienne position du pion
	            
	            // Vérifiez s'il y a un pion à capturer
	            if (!LogiqueJeu.mangerPion(selectedX, selectedY, newX, newY)) {
	                // Mettez à jour l'affichage du plateau si aucune capture n'a eu lieu
	                rafraichirPlateau();
	            } else {
	                // Mettez à jour l'affichage du plateau après la capture
	                rafraichirPlateau();
	            }
	        }

	        // Désélectionnez le pion après le déplacement
	        selectedX = -1;
	        selectedY = -1;
	        selectedPion.setStroke(Color.GRAY); // Restaurez la bordure grise
	        selectedPion = null;
	    }
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
	            // Sinon, vérifiez si la case cliquée est adjacente au pion mangeable
	            int midX = (selectedX + x) / 2;
	            int midY = (selectedY + y) / 2;
	            if (LogiqueJeu.mangerPion(selectedX, selectedY, midX, midY)) {
	                // Si oui, déplacez automatiquement le pion mangeable et sélectionnez le nouveau
	                deplacerPion(midX, midY);
	                selectedX = x;
	                selectedY = y;
	                selectedPion = pion;
	                pion.setStroke(Color.RED); // Indiquer la sélection en changeant la bordure
	            } else {
	                // Sinon, désélectionnez le pion précédemment sélectionné et sélectionnez le nouveau
	                selectedPion.setStroke(Color.GRAY); // Restaurer la bordure grise de l'ancien pion
	                selectedX = x;
	                selectedY = y;
	                selectedPion = pion;
	                pion.setStroke(Color.RED); // Indiquer la sélection en changeant la bordure
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

	public static void thèmeClassique(GridPane grid) {
		System.out.println("Thème classique sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = (Rectangle) grid.getChildren().get(row * SIZE + col);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.WHITE);
				} else {
					cell.setFill(Color.BLACK);
				}
			}
		}
	}

	public void thèmeSombre(GridPane grid) {
		System.out.println("Thème sombre sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = (Rectangle) grid.getChildren().get(row * SIZE + col);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.BLACK);
				} else {
					cell.setFill(Color.DARKGRAY);
				}
			}
		}
	}

	public void thèmeClair(GridPane grid) {
		System.out.println("Thème clair sélectionné.");
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = (Rectangle) grid.getChildren().get(row * SIZE + col);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.WHITE);
				} else {
					cell.setFill(Color.LIGHTGRAY);
				}
			}
		}
	}

	private void rafraichirPlateau() {
		grid.getChildren().clear(); // Vider la grille avant de recréer les éléments
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				Rectangle cell = new Rectangle(50, 50);
				if ((row + col) % 2 == 0) {
					cell.setFill(Color.WHITE);
				} else {
					cell.setFill(Color.BLACK);
				}
				grid.add(cell, col, row);
				if (plateau[row][col] == 'N') {
					Circle pion = afficherPion(row, col, "noir");
					evenementPions(pion, row, col);
				} else if (plateau[row][col] == 'B') {
					Circle pion = afficherPion(row, col, "blanc");
					evenementPions(pion, row, col);
				}
			}
		}
	}

	public Circle afficherPion(int x, int y, String couleur) {
		Circle pion = new Circle(20); // Créez un cercle pour représenter le pion
		pion.setFill(couleur.equals("noir") ? Color.BLACK : Color.WHITE); // Couleur du pion en fonction du propriétaire
		pion.setStroke(Color.GRAY); // Bordure grise
		pion.setStrokeWidth(3);
		grid.add(pion, y, x); // Ajoutez le pion à la position spécifiée sur le plateau de jeu
		return pion;
	}

	private void message(String message) {
		labelMessage.setText(message);
	}
}
