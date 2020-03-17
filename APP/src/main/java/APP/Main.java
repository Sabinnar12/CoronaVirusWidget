package APP;

import APP.JavaFX.FXML_Loader;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
    Stage load_screen=new Stage();



    @Override
    public void start(Stage primaryStage) throws Exception {
        FXML_Loader.OPEN_NEW_SCENE(load_screen,"./src/main/resources/corona.fxml",StageStyle.DECORATED);
        load_screen.getIcons().add(new Image("file:coronavirus.png"));
        load_screen.setTitle("CoronaVirus Widget ");
        load_screen.setResizable(false);
    }
    public static void main(String[] args) {
        FXML_Loader.launch(args);

    }
}
