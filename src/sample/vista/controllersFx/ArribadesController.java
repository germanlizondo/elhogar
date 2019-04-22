package sample.vista.controllersFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.controlador.ArribadaCrud;
import sample.controlador.SortidaCrud;
import sample.model.Arribada;
import sample.model.Sortida;
import sample.vista.TableModels.ArribadesTableModel;
import sample.vista.TableModels.SortidesTableModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ArribadesController implements Initializable {

    private ArribadaCrud arribadaCrud;
    private SortidaCrud sortidaCrud;

    /*ARRIBADES FXML*/
    @FXML
    private TableView arribadesTable;
    @FXML
    private TableColumn animalArribadaColumn;
    @FXML
    private TableColumn parcelaArribadaColumn;
    @FXML
    private TableColumn dataArribadaColumn;

    private ObservableList<ArribadesTableModel> arribadesTableModels = FXCollections.observableArrayList();
    private ArrayList<Arribada> arribadesArray;


    /*SORTIDES FXML*/
    @FXML
    private TableView sortidesTable;
    @FXML
    private TableColumn animalSortidaColumn;
    @FXML
    private TableColumn parcelaSortidaColumn;
    @FXML
    private TableColumn dataSortidaColumn;
    private ObservableList<SortidesTableModel> sortidesTableModels = FXCollections.observableArrayList();
    private ArrayList<Sortida> sortidesArray;


    @FXML
    private Button home;

    @FXML
    private Button animals;

    @FXML
    private Button parceles;

    @FXML
    private Button arribades;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        this.arribadaCrud = new ArribadaCrud();
        this.arribadesArray = this.arribadaCrud.obtenirArrbades();

        this.animalArribadaColumn.setCellValueFactory(new PropertyValueFactory<>("animal"));
        this.parcelaArribadaColumn.setCellValueFactory(new PropertyValueFactory<>("parcela"));
        this.dataArribadaColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.arribadesTable.setItems(this.arribadesTableModels);
        this.rellenarTablaArribades();

        this.sortidaCrud = new SortidaCrud();
      this.sortidesArray = this.sortidaCrud.obtenirSortides();


        this.animalSortidaColumn.setCellValueFactory(new PropertyValueFactory<>("animal"));
        this.parcelaSortidaColumn.setCellValueFactory(new PropertyValueFactory<>("parcela"));
        this.dataSortidaColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        this.sortidesTable.setItems(this.sortidesTableModels);
        this.rellenarTablaSortides();

    }

    public void rellenarTablaArribades(){
        for (Arribada a: this.arribadesArray) {
            this.arribadesTableModels.add(new ArribadesTableModel(a.getAnimal().getNom(),
                    a.getParcela().getNom(),a.getDate()));
        }
    }

    public void rellenarTablaSortides(){
        for (Sortida a: this.sortidesArray) {
            this.sortidesTableModels.add(new SortidesTableModel(a.getAnimal().getNom(),
                    a.getParcela().getNom(),a.getDate()));
        }
    }




    @FXML
    public void canviarEscenaAction(ActionEvent actionEvent) {

        Stage stage = null;
        Parent root = null;
        try {

            if(actionEvent.getSource()==home){
                System.out.println("Home");
                stage=(Stage) animals.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));

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
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            e.getStackTrace();
        }



    }


}
