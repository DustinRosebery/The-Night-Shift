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
    @FXML private Button quitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createButton.setOnAction(event -> {
            Stage createStage = new Stage();
            Parent createRoot = null;
            try {
                createRoot = FXMLLoader.load(getClass().getResource("/fxml/create.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage startStage = (Stage) startPane.getScene().getWindow();
            createStage.setTitle("Create Character");
            createStage.setResizable(false);
            createStage.initModality(Modality.APPLICATION_MODAL);
            createStage.setScene(new Scene(createRoot, 300, 200));
            createStage.show();

            startStage.hide();

            createStage.setOnCloseRequest(closeEvent -> {
                startStage.show();
            });
        });

        loadButton.setOnAction(event -> {
            Stage loadStage = new Stage();
            Parent loadRoot = null;
            try {
                loadRoot = FXMLLoader.load(getClass().getResource("/fxml/load.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage startStage = (Stage) startPane.getScene().getWindow();
            loadStage.setTitle("Load Character");
            loadStage.setResizable(false);
            loadStage.initModality(Modality.APPLICATION_MODAL);
            loadStage.setScene(new Scene(loadRoot, 250, 250));
            loadStage.show();

            startStage.hide();

            loadStage.setOnCloseRequest(closeEvent -> {
                startStage.show();
            });
        });

        leaderboardButon.setOnAction(event -> {
            // TODO: Implement leaderboard
        });

        quitButton.setOnAction(event -> {
            Stage stage = (Stage) startPane.getScene().getWindow();
            stage.close();
            stage = (Stage) stage.getProperties().get("primary");
            stage.close();
        });
    }

}
