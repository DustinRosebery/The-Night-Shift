import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the create character menu and the game
 * @author Connor Nelson
 */
public class CreateController implements Initializable {

    private static int MAX_POINTS = 60;

    @FXML private VBox createPane;
    @FXML private TextField nameField;
    @FXML private TextField strengthField;
    @FXML private TextField reflexField;
    @FXML private TextField intelligenceField;
    @FXML private TextField perceptionField;
    @FXML private TextField dexterityField;
    @FXML private TextField luckField;
    @FXML private Button createButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChangeListener<String> numericListener = (observable, oldValue, newValue) -> {
            TextField field = (TextField) ((ReadOnlyProperty) observable).getBean();
            if (!newValue.matches("\\d{0,2}")) {
                field.setText(oldValue);
            }
        };

        ChangeListener<Boolean> focusListener = (observable, oldValue, newValue) -> {
            TextField field = (TextField) ((ReadOnlyProperty) observable).getBean();
            if (!newValue) {
                field.setText(field.getText().replaceFirst("^0+", ""));
                if (field.getText().isEmpty())
                    field.setText("0");
            }
        };

        strengthField.textProperty().addListener(numericListener);
        strengthField.focusedProperty().addListener(focusListener);

        reflexField.textProperty().addListener(numericListener);
        reflexField.focusedProperty().addListener(focusListener);

        intelligenceField.textProperty().addListener(numericListener);
        intelligenceField.focusedProperty().addListener(focusListener);

        perceptionField.textProperty().addListener(numericListener);
        perceptionField.focusedProperty().addListener(focusListener);

        dexterityField.textProperty().addListener(numericListener);
        dexterityField.focusedProperty().addListener(focusListener);

        luckField.textProperty().addListener(numericListener);
        luckField.focusedProperty().addListener(focusListener);

        createButton.setOnAction(event -> {
            String name = nameField.getText();
            int[] attributes = { Integer.parseInt(strengthField.getText()),
                    Integer.parseInt(reflexField.getText()),
                    Integer.parseInt(intelligenceField.getText()),
                    Integer.parseInt(perceptionField.getText()),
                    Integer.parseInt(dexterityField.getText()),
                    Integer.parseInt(luckField.getText()) };

            int totalPoints = 0;
            for (int attribute : attributes)
                totalPoints += attribute;

            if (!name.isEmpty() && totalPoints <= MAX_POINTS) {
                Character character = new Character(name, attributes[0], attributes[1], attributes[2], attributes[3],
                        attributes[4], attributes[5], MAX_POINTS - totalPoints);

                Stage createStage = (Stage) createPane.getScene().getWindow();
                createStage.close();

                Game.getController().updateCharacter(character);
                Game.getController().write(character.name() + " has been created.");

                Game.startGame(character);
            }
        });
    }

}
