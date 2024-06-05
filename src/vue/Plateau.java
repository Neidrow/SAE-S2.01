/**
 * Plateau.java									25/05/2024
 * Iut de Rodez, pas de copyright
 */
package vue;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Joueur;
import modele.Piece;
import modele.Utilisateur;

/**
 * <p>La classe Plateau représente visuellement le plateau de jeu dans l'interface utilisateur.
 * Elle étend la classe GridPane de JavaFX pour organiser les éléments graphiques de manière
 * tabulaire. Le plateau est composé de cases rectangulaires de couleur alternée 
 * noir et blanc
 * formant un damier. Chaque case peut contenir une pièce ou rester vide.</p>
 * 
 * <p>Lorsqu'une pièce est sélectionnée par un joueur, les mouvements possibles pour cette pièce
 * sont mis en surbrillance sur le plateau.</p>
 * 
 * <p>Le plateau gère également les interactions utilisateur telles que le clic sur une case pour
 * sélectionner une pièce ou effectuer un mouvement.</p>
 */

public class Plateau extends GridPane{

	private int taille;

	private double cellTaille;
	private Utilisateur utilisateur;
	private Piece selectionnePiece;

	/**
	 * Constructeur de la classe : Génération du plateau de jeu
	 * @param taille La taille du plateau (nombre de lignes et de colonnes)
	 * @param joueur1 Le premier joueur
	 * @param joueur2 Le deuxième joueur
	 */
	public Plateau(int taille, Joueur joueur1, Joueur joueur2) {

		this.taille = taille;
		this.utilisateur = new Utilisateur(joueur1, joueur2);

		cellTaille = 450 / taille;

		// Création du damier avec des cases alternées noires et blanches
		for (int row = 0; row < taille; row++) {
			for (int col = 0; col < taille; col++) {
				Rectangle rectangle = new Rectangle(cellTaille, cellTaille, (row + col) % 2 == 0 ? Color.BLACK : Color.WHITE);
				final int currentRow = row; // Variable locale finale pour la ligne
				final int currentCol = col; // Variable locale finale pour la colonne
				rectangle.setOnMouseClicked(e -> handleMouseClick(currentRow, currentCol));
				this.add(rectangle, col, row); // Ajout de la case au plateau

				// Ajout des pièces noires et blanches sur les cases appropriées
				if ((row + col) % 2 == 1 && row < 4) {
					Piece whitePiece = new Piece(row, col, cellTaille, this, joueur2);
					joueur2.getPieces().add(whitePiece);
					this.add(whitePiece, col, row); // Ajout de la pièce au plateau
				} else if ((row + col) % 2 == 1 && row >= taille - 4) {
					Piece blackPiece = new Piece(row, col, cellTaille, this, joueur1);
					joueur1.getPieces().add(blackPiece);
					this.add(blackPiece, col, row); // Ajout de la pièce au plateau
				}
				// Configuration du plateau
				setHgap(2); // Espace horizontal entre les cellules
				setVgap(2); // Espace vertical entre les cellules
				setMinSize(taille * cellTaille, taille * cellTaille); // Définir la taille minimale du plateau
				setMaxSize(taille * cellTaille, taille * cellTaille); // Définir la taille maximale du plateau
				setAlignment(Pos.CENTER); // Centre le plateau dans son parent
			}
		}
	}
	public int getTaille() {
		return taille;
	}
	// Ajoutez cette méthode pour centrer le plateau
	public void centrerPlateau(int sceneWidth, int sceneHeight) {
		setMinSize(sceneWidth, sceneHeight); // Définit la taille minimale du GridPane
		setVgap(10); // Espacement vertical
		setHgap(10); // Espacement horizontal
		setAlignment(Pos.CENTER); // Centre les éléments dans le GridPane
	}
	/**
	 * Méthode appelée lors du clic sur une case
	 * @param row Ligne de la case cliquée
	 * @param col Colonne de la case cliquée
	 */
	private void handleMouseClick(int row, int col) {
		System.out.println("Case cliquée : Ligne " + row + ", Colonne " + col);

		if (selectionnePiece != null) {
			if (mouvementValide(selectionnePiece, row, col)) {
				Piece mangerPion = peutEtreMange(selectionnePiece, row, col);
				if (mangerPion != null) {
					deplacerPiece(selectionnePiece, row, col);
					eneleverPiece(GridPane.getRowIndex(mangerPion), GridPane.getColumnIndex(mangerPion));
					System.out.println("Pion mangé");
				} else {
					deplacerPiece(selectionnePiece, row, col);
					System.out.println("Déplacement du pion de (" + selectionnePiece.getLigne() + ", " + selectionnePiece.getCol() + ") à (" + row + ", " + col + ")");
				}

				// Vérifier si la pièce doit être promue en dame
				if ((selectionnePiece.getProprietaire() == utilisateur.getJoueur1() && row == 0) ||
						(selectionnePiece.getProprietaire() == utilisateur.getJoueur2() && row == taille - 1)) {
					selectionnePiece.transformationDame();
				}

				deselectionnePiece(); // Désélectionner la pièce une fois le mouvement terminé
				utilisateur.changeTour();
				effacerSurbrillance();
				verificationFinPartie();
			} else {
				System.out.println("Mouvement non autorisé.");
			}
		} else {
			System.out.println("Veuillez d'abord sélectionner un pion");
		}
	}

	/**
	 * Sélectionne une pièce sur le plateau
	 * @param piece La pièce à sélectionner
	 */
	public void selectionnePiece(Piece piece) {
		if (selectionnePiece != null) {
			deselectionnePiece();
		}
		selectionnePiece = piece;
		if (selectionnePiece.estDame()) {
			selectionnePiece.setStroke(Color.PURPLE); // Si la pièce est une dame, la bordure est dorée
		} else {
			selectionnePiece.setStroke(Color.RED);
		}
		afficherMouvementPossible(getMouvementPossible(piece));
	}
	/**
	 * Désélectionne la pièce actuellement sélectionnée
	 */
	public void deselectionnePiece() {
		if (selectionnePiece != null) {
			if (selectionnePiece.estDame()) {
				selectionnePiece.setStroke(Color.PURPLE); // Si la pièce est une dame et qu'elle est désélectionnée, la bordure est dorée
			} else {
				selectionnePiece.setStroke(Color.GRAY);
			}
			selectionnePiece = null;
			effacerSurbrillance();
		}
	}

	/**
	 * Méthode pour vérifier si une case est occupée par une pièce
	 * @param row Ligne de la case
	 * @param col Colonne de la case
	 * @return true si la case est occupée, false sinon
	 */
	private boolean estOccupe(int row, int col) {
		for (Node node : this.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
				if (node instanceof Piece) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * <p>Vérifie si une pièce peut être capturée lors d'un mouvement.</p>
	 * 
	 * <p>Cette méthode prend en paramètres une pièce, ainsi que les coordonnées de la nouvelle position 
	 * (ligne et colonne) où le joueur souhaite déplacer cette pièce. Elle renvoie la pièce qui peut être 
	 * capturée, le cas échéant, ou null si aucune pièce n'est capturée.</p>
	 * 
	 * @param piece La pièce à déplacer.
	 * @param newRow La nouvelle ligne où la pièce est déplacée.
	 * @param newCol La nouvelle colonne où la pièce est déplacée.
	 * @return La pièce qui peut être capturée lors du déplacement, ou null s'il n'y a aucune capture possible.
	 */

	public Piece peutEtreMange(Piece piece, int newRow, int newCol) {
		int row = piece.getLigne();
		int col = piece.getCol();

		//Vérifier si la piece est une dame:
		if(piece.estDame()) {
			int deltaRow = Integer.compare(newRow, row); // Direction du déplacement en ligne
			int deltaCol = Integer.compare(newCol, col); // Direction du déplacement en colonne
			int currentRow = row + deltaRow;
			int currentCol = col + deltaCol;
			int pieceConnu = 0;

			while (currentRow != newRow && currentCol != newCol) {
				System.out.println("Vérification case : (" + currentRow + "," + currentCol + ")");
				if (estOccupe(currentRow, currentCol)) {
					System.out.println("Piece rencontrée");
					pieceConnu++;
					if (pieceConnu > 1) {
						System.out.println("Non");
						return null; // Si plus d'une pièce est rencontrée, le mouvement n'est pas valide
					}
				}
				currentRow += deltaRow;
				currentCol += deltaCol;
			}
			if(pieceConnu == 1 ) {
				currentRow = row + deltaRow;
				currentCol = col + deltaCol;
				while (currentRow != newRow && currentCol != newCol) {
					for (Node node : this.getChildren()) {
						if (GridPane.getRowIndex(node) == currentRow && GridPane.getColumnIndex(node) == currentCol) {
							if (node instanceof Piece) {
								Piece middlePiece = (Piece) node;
								if (middlePiece.getProprietaire() != piece.getProprietaire()) {
									return middlePiece;
								}
							}
						}
					}
					currentRow += deltaRow;
					currentCol += deltaCol;
				}
			}
		} else {

			int deltaRow = Integer.compare(newRow, row); // Direction du déplacement en ligne
			int deltaCol = Integer.compare(newCol, col); // Direction du déplacement en colonne
			int currentRow = row + deltaRow;
			int currentCol = col + deltaCol;
			int pieceConnu = 0;

			while (currentRow != newRow && currentCol != newCol) {
				System.out.println("Vérification case : (" + currentRow + "," + currentCol + ")");
				if (estOccupe(currentRow, currentCol)) {
					System.out.println("Piece rencontrée");
					pieceConnu++;
					if (pieceConnu > 1) {
						System.out.println("Non");
						return null; // Si plus d'une pièce est rencontrée, le mouvement n'est pas valide
					}
				}
				currentRow += deltaRow;
				currentCol += deltaCol;
			}

			int middleRow = (row + newRow) / 2;
			int middleCol = (col + newCol) / 2;

			for (Node node : this.getChildren()) {
				if (GridPane.getRowIndex(node) == middleRow && GridPane.getColumnIndex(node) == middleCol) {
					if (node instanceof Piece) {
						Piece middlePiece = (Piece) node;
						if (middlePiece.getProprietaire().getColor() != piece.getProprietaire().getColor()) {
							return middlePiece;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Vérifie si un mouvement est valide pour une pièce donnée
	 * @param piece La pièce pour laquelle vérifier le mouvement
	 * @param newRow Ligne de la nouvelle position
	 * @param newCol Colonne de la nouvelle position
	 * @return true si le mouvement est valide, false sinon
	 */
	public boolean mouvementValide(Piece piece, int newRow, int newCol) {
		int row = piece.getLigne();
		int col = piece.getCol();

		//Vérifier si la pièce est une dame
		if(piece.estDame()) {
			//Mouvement de Dame

			//Vérification si la case est occupé
			if (estOccupe(newRow, newCol)) {
				return false;
			}
			//Vérification mouvement en diagonal :
			if (Math.abs(newRow - row) == Math.abs(newCol - col)) {
				//Vérification si le mouvement mange un autre pion :
				if(peutEtreMange(piece, newRow, newCol) != null) {
					return true;
				}
			} else {
				return false;
			}
		} else {
			//Mouvement classique
			System.out.println("Mouvement classique");

			//Vérification si la case selectionner est occupé
			if (estOccupe(newRow, newCol)) {
				return false;
			}

			//Vérification si le mouvement mange un autre pion
			if(peutEtreMange(piece, newRow, newCol) != null) {
				return true;
			}

			//Vérifier la direction du déplacement
			if (piece.getProprietaire() == utilisateur.getJoueur2() && newRow <= row) {
				return false;
			} else if (piece.getProprietaire() == utilisateur.getJoueur1() && newRow >= row) {
				return false;
			}

			//Vérification si le mouvement est en diagonal
			if (Math.abs(newRow - row) != 1 || Math.abs(newCol - col) != 1) {
				return false;
			}


		}
		return true;
	}

	/**
	 * Méthode pour supprimer une pièce du plateau
	 * @param rowDiff La différence de ligne entre la position actuelle et la nouvelle position de la pièce
	 * @param colDiff La différence de colonne entre la position actuelle et la nouvelle position de la pièce
	 */
	public void eneleverPiece(int row, int col) {
		for (Node node : this.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
				if (node instanceof Piece) {
					Piece piece = (Piece) node;
					piece.getProprietaire().getPieces().remove(piece); // Supprimer de la liste des pièces du joueur
					this.getChildren().remove(node);
					break;
				}
			}
		}
	}
	/**
	 * Déplace une pièce vers une nouvelle position sur le plateau
	 * @param selectionnePiece La pièce à déplacer
	 * @param row La nouvelle ligne
	 * @param col La nouvelle colonne
	 */
	public void deplacerPiece(Piece selectionnePiece, int row, int col) {
		this.getChildren().remove(selectionnePiece);

		// Déplacer la pièce à la nouvelle position
		setRowIndex(selectionnePiece, row);
		setColumnIndex(selectionnePiece, col);
		selectionnePiece.setLigne(row); // Mettre à jour les coordonnées de la pièce
		selectionnePiece.setCol(col); // Mettre à jour les coordonnées de la pièce

		// Ajouter la pièce à la nouvelle position
		this.add(selectionnePiece, col, row);

	}

	/**
	 * <p>Définit la pièce sélectionnée pour un mouvement.</p>
	 * 
	 * @param selectionnePiece La pièce sélectionnée pour le mouvement.
	 */
	public void setselectionnePiece(Piece selectionnePiece) {
		this.selectionnePiece = selectionnePiece;
	}

	/**
	 * <p>Renvoie l'utilisateur associé à ce plateau de jeu.</p>
	 * 
	 * @return L'utilisateur associé à ce plateau de jeu.
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * <p>Vérifie si la partie est terminée en vérifiant si l'un des joueurs a perdu toutes ses pièces.</p>
	 * 
	 * <p>Si le joueur 1 n'a plus de pièces, le joueur 2 est déclaré vainqueur. Si le joueur 2 n'a plus de pièces,
	 * le joueur 1 est déclaré vainqueur.</p>
	 */
	public void verificationFinPartie() {
		if (utilisateur.getJoueur1().getPieces().isEmpty()) {
			System.out.println("Le joueur 2 a gagné !");
			// Terminer le jeu ou afficher un message de fin
		} else if (utilisateur.getJoueur2().getPieces().isEmpty()) {
			System.out.println("Le joueur 1 a gagné !");
			// Terminer le jeu ou afficher un message de fin
		}
	}

	/**
	 * <p>Renvoie une liste de coordonnées représentant les mouvements possibles pour une pièce donnée.</p>
	 * 
	 * @param piece La pièce pour laquelle vérifier les mouvements possibles.
	 * @return Une liste de coordonnées représentant les mouvements possibles pour la pièce.
	 */
	public List<int[]> getMouvementPossible(Piece piece) {
		List<int[]> mouvementPossible = new ArrayList<>();
		int row = piece.getLigne();
		int col = piece.getCol();
		Joueur opponent = piece.getProprietaire() == utilisateur.getJoueur1() ? utilisateur.getJoueur2() : utilisateur.getJoueur1();

		if (piece.estDame()) {
			// Mouvement pour une dame
			int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
			for (int[] dir : directions) {
				int dRow = dir[0];
				int dCol = dir[1];
				int currentRow = row + dRow;
				int currentCol = col + dCol;
				boolean canJump = false;
				while (coordonneValide(currentRow, currentCol)) {
					if (getPiece(currentRow, currentCol) == null) {
						mouvementPossible.add(new int[]{currentRow, currentCol});
					} else if (getPiece(currentRow, currentCol).getProprietaire() == opponent) {
						int sautLigne = currentRow + dRow;
						int sautCol = currentCol + dCol;
						if (coordonneValide(sautLigne, sautCol) && getPiece(sautLigne, sautCol) == null) {
							mouvementPossible.add(new int[]{sautLigne, sautCol});
							canJump = true;
						} else {
							break;
						}
					} else {
						break;
					}
					if (!canJump) {
						currentRow += dRow;
						currentCol += dCol;
					} else {
						break;
					}
				}
			}
		} else {
			// Mouvement classique
			int direction = piece.getProprietaire() == utilisateur.getJoueur1() ? -1 : 1;
			int[][] directions = {{direction, 1}, {direction, -1}};
			for (int[] dir : directions) {
				int dRow = dir[0];
				int dCol = dir[1];
				int newRow = row + dRow;
				int newCol = col + dCol;
				if (coordonneValide(newRow, newCol)) {
					if (getPiece(newRow, newCol) == null) {
						mouvementPossible.add(new int[]{newRow, newCol});
					} else if (getPiece(newRow, newCol).getProprietaire() == opponent) {
						int sautLigne = newRow + dRow;
						int sautCol = newCol + dCol;
						if (coordonneValide(sautLigne, sautCol) && getPiece(sautLigne, sautCol) == null) {
							mouvementPossible.add(new int[]{sautLigne, sautCol});
						}
					}
				}
			}
		}

		return mouvementPossible;
	}

	/**
	 * <p>Vérifie si les coordonnées spécifiées sont valides dans le contexte du plateau de jeu.</p>
	 * 
	 * @param row La ligne à vérifier.
	 * @param col La colonne à vérifier.
	 * @return true si les coordonnées sont valides, sinon false.
	 */

	private boolean coordonneValide(int row, int col) {
		return row >= 0 && row <taille&& col >= 0 && col < taille;
	}

	/**
	 * <p>Renvoie la pièce située aux coordonnées spécifiées.</p>
	 * 
	 * @param row La ligne où se trouve la pièce.
	 * @param col La colonne où se trouve la pièce.
	 * @return La pièce située aux coordonnées spécifiées, ou null si aucune pièce n'est présente.
	 */
	private Piece getPiece(int row, int col) {
		for (Node node : this.getChildren()) {
			if (node instanceof Piece && getRowIndex(node) == row && getColumnIndex(node) == col) {
				return (Piece) node;
			}
		}
		return null;
	}

	/**
	 * <p>Affiche visuellement les mouvements possibles pour une pièce sur le plateau de jeu.</p>
	 * 
	 * @param mouvementPossible La liste des mouvements possibles à afficher.
	 */
	public void afficherMouvementPossible(List<int[]> mouvementPossible) {
		for (int[] move : mouvementPossible) {
			int row = move[0];
			int col = move[1];
			Rectangle surbrillance = new Rectangle(cellTaille, cellTaille, Color.ORANGE);
			surbrillance.setOpacity(0.5);
			surbrillance.setMouseTransparent(true);
			setRowIndex(surbrillance, row);
			setColumnIndex(surbrillance, col);
			this.getChildren().add(surbrillance);
		}
	}

	/**
	 * <p>Efface toutes les surbrillances des mouvements possibles sur le plateau de jeu.</p>
	 */
	public void effacerSurbrillance() {
		List<Node> surbrillance = new ArrayList<>();
		for (Node node : this.getChildren()) {
			if (node instanceof Rectangle && ((Rectangle) node).getFill().equals(Color.ORANGE)) {
				surbrillance.add(node);
			}
		}
		this.getChildren().removeAll(surbrillance);
	}

	/**
	 * <p>Renvoie la pièce actuellement sélectionnée pour un mouvement.</p>
	 * 
	 * @return La pièce actuellement sélectionnée pour un mouvement.
	 */
	public Piece getSelectionnePiece() {

		return selectionnePiece;
	}


}