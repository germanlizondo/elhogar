package sample.vista.controllersFx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {



    @FXML
    private Button home;

    @FXML
    private Button animals;

    @FXML
    private Button parceles;

    @FXML
    private Button arribades;



    @FXML
    public void canviarEscenaAction(ActionEvent actionEvent) {

        Stage stage = null;
        Parent root = null;
        try {

            if(actionEvent.getSource()==home){
                System.out.println("Home");


            }else if(actionEvent.getSource()==animals){
                System.out.println("animals");


                stage=(Stage) animals.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/animalsPage.fxml"));


            }else if(actionEvent.getSource()==parceles){
                System.out.println("parceles");

                stage=(Stage) parceles.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/parcelesPage.fxml"));

            }else if(actionEvent.getSource()==arribades){
                System.out.println("arribades");

                stage=(Stage) animals.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/arribadesPage.fxml"));

            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.getStackTrace();
        }



    }

}
