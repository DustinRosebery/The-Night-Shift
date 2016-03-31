
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Sets up the JavaFX 'stage'
 * TODO: Merge this with current Main
 * @author Connor Nelson
 */

public class MainGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("The NightShift");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();

        Stage startStage = new Stage();
        Parent startRoot = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        startStage.getProperties().put("primary", primaryStage);
        startStage.setTitle("Main Menu");
        startStage.setResizable(false);
        startStage.initModality(Modality.APPLICATION_MODAL);
        startStage.setScene(new Scene(startRoot, 250, 250));
        startStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                primaryStage.close();
            }
        });
        startStage.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
