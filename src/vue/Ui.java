package vue;

import java.awt.Label;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Ui {
    private static final int SIZE = 10; // Taille de la grille
    private char[][] plateau;
    private Label labelMessage;
    private Label labelChronometre;
    private int minutes = 10;
    private int seconds = 0;

    public Ui() {
        this.plateau = new char[SIZE][SIZE]; // Initialise un plateau vide pour l'exemple
        initialiserPlateau();
    }

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

    public BorderPane creerContenu(Stage primaryStage) {
        BorderPane root = new BorderPane();

        // Options
        VBox vboxOptions = new VBox(); // Utilisez VBox au lieu de HBox
        vboxOptions.setAlignment(Pos.CENTER); // Centrer verticalement
        vboxOptions.setSpacing(10); // Espacement entre les boutons

        Button btnNouvellePartie = new Button("Nouvelle Partie");
        btnNouvellePartie.setOnAction(e -> {
            message("Nouvelle partie commencée.");
            initialiserPlateau();
            rafraichirPlateau();

            // Remplacer le contenu de la scène par l'écran de jeu
            BorderPane ecranJeu = creerEcranJeu();
            root.setCenter(ecranJeu);
        });

        Button btnQuitter = new Button("Quitter");
        btnQuitter.setOnAction(e -> primaryStage.close());

        vboxOptions.getChildren().addAll(btnNouvellePartie, btnQuitter);
        root.setCenter(vboxOptions);

        return root;
    }

    private BorderPane creerEcranJeu() {
        BorderPane ecranJeu = new BorderPane();

        // Création du plateau de jeu
        GridPane plateauJeu = creerPlateau();
        plateauJeu.setAlignment(Pos.CENTER); // Centrer le plateau horizontalement et verticalement
        ecranJeu.setCenter(plateauJeu);

        // Autres éléments à ajouter à l'écran de jeu si nécessaire

        return ecranJeu;
    }

    private GridPane creerPlateau() {
        GridPane grid = new GridPane(); // Ici, j'ai déclaré la variable grid
        grid.setHgap(1);
        grid.setVgap(1);

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
        return grid;
    }

    public static void thèmeClassique(GridPane grid) {
        System.out.println("Thème classique sélectionné.");

        // Parcours de toutes les cases du plateau
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

    // Méthode pour changer le thème vers le thème sombre (noir et gris)
    public  void thèmeSombre(GridPane grid) {
        System.out.println("Thème sombre sélectionné.");

        // Parcours de toutes les cases du plateau
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle cell = (Rectangle) grid.getChildren().get(row * SIZE + col);
                if ((row + col) % 2 == 0) {
                    cell.setFill(Color.BLACK);
                } else {
                    cell.setFill(Color.DARKGRAY); // Vous pouvez ajuster la teinte de gris selon vos préférences
                }
            }
        }
    }

    // Méthode pour changer le thème vers le thème clair (blanc et gris)
    public void thèmeClair(GridPane grid) {
        System.out.println("Thème clair sélectionné.");
        // Implémentez ici le changement de thème pour le thème clair (blanc et gris)
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Rectangle cell = (Rectangle) grid.getChildren().get(row * SIZE + col);
                if ((row + col) % 2 == 0) {
                    cell.setFill(Color.WHITE);
                } else {
                    cell.setFill(Color.LIGHTGRAY); // Vous pouvez ajuster la teinte de gris selon vos préférences
                }
            }
        }
    }
    private void rafraichirPlateau() {
        // Actualisation du plateau de jeu si nécessaire
    }

    private void message(String message) {
        // Affichage des messages sur l'écran si nécessaire
    }
}