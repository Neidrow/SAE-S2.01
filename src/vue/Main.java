package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Ui ui = new Ui();
            BorderPane root = ui.creerContenu(primaryStage);
            Scene scene = new Scene(root, 1200, 1000);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Menu Principal");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
