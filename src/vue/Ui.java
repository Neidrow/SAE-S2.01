package vue;

import java.awt.Label;

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

public class Ui {
    private static final int SIZE = 10; // Taille de la grille
    private char[][] plateau;
    private Label labelMessage;
    private Label labelChronometre;
    private int minutes = 10;
    private int seconds = 0;
    private GridPane grid; // Ajout du champ GridPane

    public Ui() {
        this.plateau = new char[SIZE][SIZE]; // Initialise un plateau vide pour l'exemple
        initialiserPlateau();
        this.labelMessage = new Label(); // Initialisation du labelMessage
        this.labelChronometre = new Label(); // Initialisation du labelChronometre
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
                    afficherPion(row, col, "noir");
                } else if (plateau[row][col] == 'B') {
                    afficherPion(row, col, "blanc");
                }
            }
        }
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
        // Actualiser la représentation visuelle du plateau de jeu en fonction de l'état actuel du plateau

        // Parcourir le plateau et mettre à jour chaque case dans la représentation visuelle
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Node node = grid.getChildren().get(row * SIZE + col);
                if (node instanceof Rectangle) {
                    Rectangle cell = (Rectangle) node;
                    switch (plateau[row][col]) {
                        case 'N':
                            // Mettre à jour la case avec la couleur ou le symbole représentant un pion noir
                            // Par exemple, changer la couleur de la case en noir ou afficher un cercle noir
                            cell.setFill(Color.BLACK);
                            break;
                        case 'B':
                            // Mettre à jour la case avec la couleur ou le symbole représentant un pion blanc
                            // Par exemple, changer la couleur de la case en blanc ou afficher un cercle blanc
                            cell.setFill(Color.WHITE);
                            break;
                        default:
                            // Mettre à jour la case pour qu'elle soit vide
                            // Par exemple, changer la couleur de la case en gris
                            cell.setFill(Color.GRAY);
                            break;
                    }
                }
            }
        }
    }

    public void afficherPion(int x, int y, String couleur) {
        Circle pion = new Circle(20); // Créez un cercle pour représenter le pion
        pion.setFill(couleur.equals("noir") ? Color.BLUE : Color.BLACK); // Couleur du pion en fonction du propriétaire
        grid.add(pion, y, x); // Ajoutez le pion à la position spécifiée sur le plateau de jeu
    }



    private void message(String message) {
        // Affichage des messages sur l'écran si nécessaire
        labelMessage.setText(message);
    }
}