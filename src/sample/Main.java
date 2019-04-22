package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controlador.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("vista/fxml/sample.fxml"));
        primaryStage.setTitle("El Hogar");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
      //  primaryStage.setMaximized(true);
        Database db = new Database();
        db.crearTablas();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
