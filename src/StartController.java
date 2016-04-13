import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartController implements Initializable {

    @FXML private VBox startPane;
    @FXML private Button createButton;
    @FXML private Button loadButton;
    @FXML private Button leaderboardButon;
    @FXML private Button helpButton;
    @FXML private Button quitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createButton.setOnAction(event -> {
            Stage stage = (Stage) startPane.getScene().getWindow();
            stage.close();
        });

        quitButton.setOnAction(event -> {
            Stage stage = (Stage) startPane.getScene().getWindow();
            stage.close();
            stage = (Stage) stage.getProperties().get("primary");
            stage.close();
        });
    }



}
