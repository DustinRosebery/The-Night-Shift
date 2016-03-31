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
 * Controls the interaction between the GUI and the Game
 * @author Connor Nelson
 */

public class MainController implements Initializable {

    private final static int FRAME_DURATION = 75;

    private Interpreter interpreter;
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

        commandField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String command = commandField.getText();
                commandField.clear();
                historyField.appendText("\n" + command);
                interpreter.interpret(command);
            }
        });

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

        Timeline timeline = new Timeline();
        for (int idx = 0; idx < 6; idx++) {
            final int finalIdx = idx;
            timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(FRAME_DURATION * idx), event -> {
                diceView.setImage(diceFaces.get(2 * finalIdx));
                diceView.setRotate(75 * finalIdx);
            }));
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        interpreter.offer(input -> {
            if (input.toString().equalsIgnoreCase("play"))
                timeline.play();
        }, input -> {
            if (input.toString().equalsIgnoreCase("pause"))
                timeline.pause();
        });

    }

    /**
     * Sets up the interpreter with a few basic commands
     * TODO: Implement real commands
     */
    public void initializeInterpreter() {
        interpreter = new Interpreter(input -> {
            String message = input.toString().replace("say ", "");
            if (input.toString().contains("say "))
                descriptionField.appendText("\n" + message);
        }, input -> {
            if (input.toString().equalsIgnoreCase("clear"))
                descriptionField.clear();
        }, input -> {
            if (input.toString().matches("(.*)exit(.*)")) {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close();
            }
        });
    }

}
