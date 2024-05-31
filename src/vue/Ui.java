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

    // Méthode pour afficher visuellement les mouvements possibles pour l'utilisateur
    public void afficherMouvementsPossibles(List<Mouvement> mouvementsPossibles) {
        if (mouvementsPossibles != null) { // Vérifiez si la liste est null
            for (Mouvement mouvement : mouvementsPossibles) {
                int x = mouvement.getX();
                int y = mouvement.getY();
                // Mettez en surbrillance visuellement la case pour indiquer le mouvement possible
                Rectangle cell = (Rectangle) grid.getChildren().get(x * SIZE + y);
                cell.setFill(Color.LIGHTGREEN); // Changez la couleur de fond de la case en vert clair
                // Ajoutez un gestionnaire d'événements de clic à la case pour permettre le déplacement
                cell.setOnMouseClicked(event -> {
                    int newX = GridPane.getRowIndex(cell);
                    int newY = GridPane.getColumnIndex(cell);
                    LogiqueJeu.mouvement(selectedX, selectedY, newX, newY);
                    rafraichirPlateau();
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

    public void evenementPions(Circle pion, int x, int y) {
        pion.setOnMouseClicked(event -> {
            if (selectedX == x && selectedY == y) {
                // Si le pion sélectionné est cliqué à nouveau, désélectionner
                selectedX = -1;
                selectedY = -1;
                selectedPion.setStroke(Color.GRAY); // Restaurer la bordure grise
                selectedPion = null;
            } else {
                // Si un autre pion est déjà sélectionné, désélectionner l'ancien
                if (selectedPion != null) {
                    selectedPion.setStroke(Color.GRAY); // Restaurer la bordure grise de l'ancien pion
                }
                // Sélectionner le nouveau pion
                selectedX = x;
                selectedY = y;
                selectedPion = pion;
                pion.setStroke(Color.RED); // Indiquer la sélection en changeant la bordure
            }
        });
    }

    private void message(String message) {
        labelMessage.setText(message);
    }
}
