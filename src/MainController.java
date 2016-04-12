import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the Main GUI and the Game
 * @author Connor Nelson
 */

public class MainController implements Initializable {

    private final static int FRAME_DURATION = 75;

    private Character character;
    //TODO: private ArrayList<Room> rooms;
    //TODO: private Room currentRoom;

    private Interpreter interpreter;

    private Timeline diceTimeline; // Animation for the Dice
    private ArrayList<Image> diceFaces;

    @FXML private BorderPane mainPane;
    @FXML private TextField commandField;
    @FXML private TextArea historyField;
    @FXML private TextArea descriptionField;
    @FXML private TextArea statsField;
    @FXML private TextArea itemsField;
    @FXML private ImageView diceView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeInterpreter();
        initializeDice();

        // Connect command field to interpreter
        commandField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String command = commandField.getText();
                commandField.clear();
                write(command);

                boolean generalHandled = interpreter.interpret(command);
                if (!interpreter.interpret(command))
                    //TODO: if(!Room.getInterpreter().interpret(command));
                    write("Unknown command: Type \"help\" for help regarding commands.");
            }
        });


        // Testing commands; TODO: Remove later
        interpreter.offer(input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("play")) {
                diceTimeline.play();
            }
            return handled;
        }, input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("pause")) {
                diceTimeline.pause();
            }
            return handled;
        });

    }

    /**
     * @param message The message to be appended to the history field
     */
    public void write(String message) {
        historyField.appendText("\n\n" + message);
    }

    /**
     * Updates the stats for the character TODO: and items
     * @param character The player which the UI should reflect
     */
    public void updateCharacter(Character character) {
        this.character = character;

        statsField.setText("Stats for " + character.getName() + "\n\n");
        statsField.appendText("Strength:\t\t\t\t" + character.getStr() + "\n");
        statsField.appendText("Reflex:\t\t\t\t" + character.getRef() + "\n");
        statsField.appendText("Intelligence:\t\t\t" + character.getInt() + "\n");
        statsField.appendText("Perception:\t\t\t" + character.getPerc() + "\n");
        statsField.appendText("Dexterity:\t\t\t\t" + character.getDex() + "\n");
        statsField.appendText("Luck:\t\t\t\t" + character.getLuck() + "\n");
        statsField.appendText("Experience Points:\t\t" + character.getExp() + "\n");
    }

    //TODO: public void updateRoom(Room room);

    /**
     * Sets up the interpreter with a few basic commands
     * TODO: Implement real commands
     */
    private void initializeInterpreter() {
        interpreter = new Interpreter(input -> {
            boolean handled;
            String message = input.toString().replace("say ", "");
            if (handled = input.toString().contains("say "))
                descriptionField.appendText("\n" + message);
            return handled;
        }, input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("clear"))
                descriptionField.clear();
            return handled;
        }, input -> {
            boolean handled;
            if (handled = input.toString().matches("(.*)exit(.*)")) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close();
            }
            return handled;
        });
    }

    /**
     * Initializes the dice by loading the necessary images and animations
     */
    private void initializeDice() {
        diceFaces = new ArrayList<Image>();
        diceFaces.add(new Image("/img/numbers/one_off.png"));
        diceFaces.add(new Image("/img/numbers/one_on.png"));
        diceFaces.add(new Image("/img/numbers/two_off.png"));
        diceFaces.add(new Image("/img/numbers/two_on.png"));
        diceFaces.add(new Image("/img/numbers/three_off.png"));
        diceFaces.add(new Image("/img/numbers/three_on.png"));
        diceFaces.add(new Image("/img/numbers/four_off.png"));
        diceFaces.add(new Image("/img/numbers/four_on.png"));
        diceFaces.add(new Image("/img/numbers/five_off.png"));
        diceFaces.add(new Image("/img/numbers/five_on.png"));
        diceFaces.add(new Image("/img/numbers/six_off.png"));
        diceFaces.add(new Image("/img/numbers/six_on.png"));

        diceTimeline = new Timeline();
        for (int idx = 0; idx < 6; idx++) {
            final int finalIdx = idx;
            diceTimeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(FRAME_DURATION * idx), event -> {
                diceView.setImage(diceFaces.get(2 * finalIdx));
                diceView.setRotate(75 * finalIdx);
            }));
        }
        diceTimeline.setCycleCount(Timeline.INDEFINITE);
        diceTimeline.play();
    }

}
