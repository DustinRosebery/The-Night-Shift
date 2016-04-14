
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import java.util.ArrayList;

import java.io.IOException;

/**
 * Sets up the JavaFX 'stage'
 * TODO: Merge this with current Main
 * @author Connor Nelson
 */

public class Game extends Application {

    private static MainController controller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource("/fxml/main.fxml").openStream());
        controller = loader.getController();

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

    /**
     * where you land after creating your character.
     * @param character being played
     */
    public static void startGame(Character character) {
        //Items.populate();
        character.initMap();
        controller.updateRoom(character);
        controller.write ("Try going inside");
    }

    /**
     * Initiates items and updates description upon successful character load
     * @param character currently being played
     */
    public static void loadGame(Character character) {
        //Items.populate();
        controller.updateRoom(character);
        if (character.index() == 0)
            controller.write ("try going inside");
    }

    public static void main(String... args) {
        launch(args);
    }
}
