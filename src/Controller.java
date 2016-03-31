
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the GUI and the Game
 * @author Connor Nelson
 */

public class Controller implements Initializable {

    private Interpreter interpreter;

    @FXML private TextField commandField;
    @FXML private TextArea historyField;
    @FXML private TextArea descriptionField;
    @FXML private TextArea statsField;
    @FXML private TextArea itemsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeInterpreter();

        commandField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String command = commandField.getText();
                    commandField.clear();
                    historyField.appendText("\n" + command);
                    interpreter.interpret(command);
                }
            }
        });
    }

    /**
     * Sets up the interpreter with a few basic commands
     * TODO: Implement real commands
     */
    public void initializeInterpreter() {
        interpreter = new Interpreter(
                new Command() {
                    String message; // Notice how these are anonymous classes and can have additional objects/methods

                    @Override
                    public boolean condition(String condition) {
                        message = condition.replace("say ", "");
                        return condition.contains("say ");
                    }

                    @Override
                    public void execute() {
                        descriptionField.appendText("\n" + message); // Outside objects like the Fields can be accessed
                    }
                },
                new Command() {
                    @Override
                    public boolean condition(String input) {
                        return input.equalsIgnoreCase("clear");
                    }

                    @Override
                    public void execute() {
                        descriptionField.clear(); // I recommend reading up on the JavaFX objects to see available methods
                    }
                },
                new Command() {
                    @Override
                    public boolean condition(String input) {
                        return input.matches("(.*)exit(.*)"); // Example of regex command
                    }

                    @Override
                    public void execute() {
                        Stage stage = (Stage) commandField.getScene().getWindow();
                        stage.close(); // Potentially the best/standard way to arbitrarily close?
                    }
                });
    }

}
