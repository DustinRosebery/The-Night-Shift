
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Sets up the JavaFX 'stage'
 * TODO: Merge this with current Main
 * @author Connor Nelson
 */

public class Game extends Application {

    private Character character;
    private static MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/fxml/main.fxml").openStream());
        controller = (MainController) loader.getController();

        primaryStage.setTitle("The NightShift");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();

        Stage startStage = new Stage();
        Parent startRoot = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        startStage.getProperties().put("primary", primaryStage);
        startStage.setTitle("Main Menu");
        startStage.setResizable(false);
        startStage.initModality(Modality.APPLICATION_MODAL);
        startStage.setScene(new Scene(startRoot, 250, 250));
        startStage.setOnCloseRequest(event -> {
            primaryStage.close();
        });
        startStage.show();
    }

    // TODO: This seems "bad" -- it technically gives god powers to EVERY class to make UI Changes, which is a poor design
    // TODO: Think of a better design pattern
    public static MainController getController() {
        return controller;
    }

    public static void main(String... args) {
        launch(args);
    }
}
