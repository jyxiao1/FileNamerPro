package Launcher;

import Singletons.Database;
import Singletons.FXMLManager;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Launcher extends Application {

    //define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Database.initDatabase();
        FXMLManager fxmlManager = FXMLManager.getInstance();

        fxmlManager.loadFXML("FXML/fullNamer.fxml");

        Parent root = fxmlManager.getFXMLNode("FXML/fullNamer.fxml");
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setScene(new Scene(root));
        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING, window -> {
            FadeTransition ft = new FadeTransition(Duration.millis(1000), root);
            ft.setFromValue(0.8);
            ft.setToValue(1.0);
            ft.play();
        });

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
