import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Connor Nelson
 */
public class LeaderController implements Initializable {

    @FXML private VBox leaderPane;
    @FXML private ListView<String> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> characterNames = Saves.getCharacters();
        ArrayList<Character> characters = new ArrayList<Character>();
        for (String name : characterNames)
            try {
                characters.add(Saves.readCharacter(name));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        ArrayList<String> stats = new ArrayList<String>();
        for (Character character : characters)
            stats.add(character.name() + " - " + character.inventory().getValue() +
                    (character.isCaught() ? " - CAUGHT" : ""));

        ObservableList<String> observableStats = FXCollections.observableArrayList(stats);
        listView.setItems(observableStats);
    }

}
