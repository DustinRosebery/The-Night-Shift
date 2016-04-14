import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controls the interaction between the load character menu and the game
 * @author Connor Nelson
 */
public class LoadController implements Initializable {

    @FXML private VBox loadPane;
    @FXML private ListView<String> listView;
    @FXML private Button loadButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> characters = FXCollections.observableArrayList(Saves.getCharacters());
        listView.setItems(characters);

        loadButton.setOnAction(event -> {
            String selected = listView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    Character character = Saves.readCharacter(selected);

                    Stage loadStage = (Stage) loadPane.getScene().getWindow();
                    loadStage.close();

                    Game.getController().updateCharacter(character);
                    Game.getController().write(selected + " has been successfully loaded.");

                    Game.loadGame(character);

                } catch (IOException | ClassNotFoundException e) {
                    Game.getController().write("Character could not be loaded.");
                }
            }
        });
    }
}
