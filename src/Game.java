
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
 * TODO: implement enterRoom and exitRoom
 * @author Connor Nelson, Dustin Rosebery
 */

public class Game extends Application {

    private static Character currentCharacter;
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

    // TODO: possibly make controller protected
    public static MainController getController() {
        return controller;
    }

    /**
     * where you land after creating your character.
     * @param character being played
     */
    public static void startGame(Character character) {
        Items.populate();
        currentCharacter = character;
        currentCharacter.initMap();
        controller.updateRoom(currentCharacter);
        controller.write ("Try going inside");
    }


    public static Character getCurrentCharacter() {
        return currentCharacter;
    }

    /**
     * Initiates items and updates description upon successful character load
     * @param character currently being played
     */
    public static void loadGame(Character character) {
        Items.populate();
        currentCharacter = character;
        character.currentRoom().entry(character);
        if (currentCharacter.index() == 0)
            controller.write ("try going inside");
    }

    /**
     * Used to hold room events. It runs every time a character enters the room.
     * @param character - uses input character room object for any room state change
     */
    public static void enterRoom(Character character) {
        controller.write (currentCharacter.currentRoom().exits());
        //TODO add room control
        controller.updateRoom(character);
    }


    /**
     * Similar to enterRoom this method holds room events, and runs every time the player leaves a room
     * @param character
     */
    public static void exitRoom(Character character) {}
    //TODO

    public static void main(String... args) {
        launch(args);
    }

}
