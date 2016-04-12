import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the start menu and the game
 * @author Connor Nelson
 */

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
            Stage createStage = new Stage();
            Parent newRoot = null;
            try {
                newRoot = FXMLLoader.load(getClass().getResource("/fxml/create.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage startStage = (Stage) startPane.getScene().getWindow();
            Stage primaryStage = (Stage) startStage.getProperties().get("primary");
            createStage.getProperties().put("start", startStage);
            createStage.getProperties().put("primary", primaryStage);

            createStage.setTitle("Create Character");
            createStage.setResizable(false);
            createStage.initModality(Modality.APPLICATION_MODAL);
            createStage.setScene(new Scene(newRoot, 300, 200));
            createStage.show();

            startStage.hide();

            createStage.setOnCloseRequest(closeEvent -> {
                startStage.show();
            });

        });

        quitButton.setOnAction(event -> {
            Stage stage = (Stage) startPane.getScene().getWindow();
            stage.close();
            stage = (Stage) stage.getProperties().get("primary");
            stage.close();
        });
    }

}
