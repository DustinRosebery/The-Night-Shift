import javafx.animation.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the main GUI and the game
 * @author Connor Nelson
 */

public class MainController implements Initializable {

    private final static int FRAME_DURATION = 75;

    private Character character;
    //TODO: private ArrayList<Room> rooms;
    //TODO: private Room currentRoom;

    private Interpreter interpreter;
    private boolean commandsAllowed;

    private Timeline diceTimeline; // Animation for the Dice
    private ArrayList<Image> diceFaces;
    private boolean diceStopped; // Animation pauses are asynchronous--ensures dice doesn't roll while displaying a value


    @FXML private BorderPane mainPane;
    @FXML private TextField commandField;
    @FXML private TextArea historyField;
    @FXML private TextArea descriptionField;
    @FXML private TextArea statsField;
    @FXML private TextArea itemsField;
    @FXML private ImageView diceView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        character = null;

        initializeInterpreter();
        initializeDice();

        // Connect command field to interpreter
        commandField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && commandsAllowed) {
                String command = commandField.getText();
                commandField.clear();
                write(command);

                if (!interpreter.interpret(command))
                    //TODO: if(!Room.getInterpreter().interpret(command));
                    write("Unknown command: Type \"help\" for help regarding commands.");
            }
        });

        // Testing commands; TODO: Remove later, just to test roll function
        interpreter.offer(input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("roll")) {
                int roll = rollDice();
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
     *
     * @param message The text to be displayed to the description field
     */
    public void setDesc(String message) { descriptionField.setText(message); }

    /**
     * @param enabled Whether or not the commandline is enabled
     */
    public void setCommandlineEnabled(boolean enabled) {
        commandsAllowed = enabled;
        commandField.setEditable(enabled);
    }

    /**
     * Updates the stats for the character TODO: and items
     * @param character The player which the UI should reflect
     */
    public void updateCharacter(Character character) {
        this.character = character;

        statsField.setText("Stats for " + character.name() + "\n\n");
        statsField.appendText("Strength:\t\t\t\t" + character.strength() + "\n");
        statsField.appendText("Reflex:\t\t\t\t" + character.reflex() + "\n");
        statsField.appendText("Intelligence:\t\t\t" + character.intelligence() + "\n");
        statsField.appendText("Perception:\t\t\t" + character.perception() + "\n");
        statsField.appendText("Dexterity:\t\t\t" + character.dexterity() + "\n");
        statsField.appendText("Luck:\t\t\t\t" + character.luck() + "\n");
        statsField.appendText("Experience Points:\t\t" + character.exp() + "\n");
    }

    /**
     * @return The current character in the game
     */
    public Character getCharacter() {
        return character;
    }

    //TODO: public void updateRoom(Room room);

    /**
     * Animates a dice roll and prevents commands from being interpreted while doing so
     * @return A random number (3-18) from rolling the dice
     */
    public int rollDice() {
        setCommandlineEnabled(false);
        commandField.setText("You are currently rolling the dice...");

        diceTimeline.stop();
        Timeline rollTimeline = new Timeline();

        final int numDice = 3;
        int[] rolls = new int[numDice];
        int sum = 0;
        for (int roll = 0; roll < numDice; roll++) {
            sum += rolls[roll] = (int) (Math.random() * 6 + 1);

            final int finalRoll = roll;
            rollTimeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(50 * FRAME_DURATION * roll), event -> {
                diceStopped = false;
                diceTimeline.playFromStart();
            }), new KeyFrame(Duration.millis(50 * FRAME_DURATION * roll + 20 * FRAME_DURATION), event -> {
                diceStopped = true;
                diceTimeline.stop();
                diceView.setRotate(0);
                diceView.setImage(diceFaces.get(2 * rolls[finalRoll] - 1));
            }));
        }

        final int finalSum = sum;
        rollTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(50 * FRAME_DURATION * numDice), event -> {
            diceStopped = false;
            diceTimeline.playFromStart();

            write("You rolled a total of " + finalSum);

            commandField.setText("");
            setCommandlineEnabled(true);
        }));

        rollTimeline.play();
        return sum;
    }

    /**
     * Sets up the interpreter with basic commands
     */
    private void initializeInterpreter() {
        commandsAllowed = true;

        interpreter = new Interpreter( input -> {
            if (character != null)
                try {
                    Saves.writeCharacter(character);
                    System.out.println("[Autosave] The character was successfully saved.");
                } catch (IOException e) {
                    System.out.println("[Autosave] The character could not be saved.");
                }
            else
                System.out.println("[Autosave] The character could not be saved.");

            return false; // Autosave character, but do not prevent commands from happening

        }, input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("help"))
                write("clear\t\t\t\t\t\t\tRemoves past command history\n" +
                        "exit\t\t\t\t\t\t\tLeaves the game\n" +
                        "save\t\t\t\t\t\t\tSaves the character\n" +
                        "level <skill> <amount>\t\t\tLevels your character using available experience points\n" +
                        "You must discover the commands that will get you through the house.");
            return handled;

        }, input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("clear"))
                historyField.setText("Command History\n");
            return handled;

        }, input -> {
            boolean handled;
            if (handled = input.toString().equalsIgnoreCase("exit")) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close();
            }
            return handled;

        }, input -> {
                boolean handled;
                String[] command = input.toString().split(" ");
                int amount = 0;
                if (handled = command[0].equalsIgnoreCase("level")) {
                    if (command.length != 3 || !command[2].matches("\\d+"))
                        write("Invalid command usage: level <skill> <amount>");
                    else if ((amount = Integer.parseInt(command[2])) > character.exp())
                        write("You do not have enough experience points.");
                    else if (command[1].equalsIgnoreCase("strength"))
                        character.addStrength(amount);
                    else if (command[1].equalsIgnoreCase("reflex"))
                        character.addReflex(amount);
                    else if (command[1].equalsIgnoreCase("intelligence"))
                        character.addIntelligence(amount);
                    else if (command[1].equalsIgnoreCase("perception"))
                        character.addPerception(amount);
                    else if (command[1].equalsIgnoreCase("dexterity"))
                        character.addDexterity(amount);
                    else if (command[1].equalsIgnoreCase("luck"))
                        character.addLuck(amount);
                    else
                        write("Invalid command usage, unknown skill: level <skill> <amount>");
                }
                updateCharacter(character);
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

        diceStopped = false;

        diceTimeline = new Timeline();
        for (int idx = 0; idx < 6; idx++) {
            final int finalIdx = idx;
            diceTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(FRAME_DURATION * idx), event -> {
                if (!diceStopped) {
                    diceView.setImage(diceFaces.get(2 * finalIdx));
                    diceView.setRotate(75 * finalIdx);
                }
            }));
        }
        diceTimeline.setCycleCount(Timeline.INDEFINITE);
        diceTimeline.play();
    }

}
