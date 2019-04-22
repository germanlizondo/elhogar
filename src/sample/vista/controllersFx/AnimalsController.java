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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.controlador.*;
import sample.model.*;
import sample.vista.TableModels.AnimalsTableModel;
import sample.vista.TableModels.EspeciesTableModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnimalsController implements Initializable {

    private EspecieCrud especieCrud;
    private ParcelaCrud parcelaCrud;
    private AnimalCrud animalCrud;
    private ArribadaCrud arribadaCrud;
    private SortidaCrud sortidaCrud;




    /*ESPECIE FXML*/
    @FXML
    private TextField novaEspecieText;
    @FXML
    private TextField eliminaEspecieText;
    @FXML
    private TableView tableEspecie;
    @FXML
    private TableColumn idEspcieColumn;
    @FXML
    private TableColumn nomEspcieColumn;

    private ArrayList<Especie> especies;

    private ObservableList<EspeciesTableModel> especiesTableModels = FXCollections.observableArrayList();


    /*ANIMALS FXML*/
    @FXML
    private TextField textNomAnimal;
    @FXML
    private TextField textIdEspecie;
    @FXML
    private TextField textIdParcela;
    @FXML
    private TextField eliminaAnimalText;
    @FXML
    private TableView animalTable;
    @FXML
    private TableColumn idAnimalColumn;
    @FXML
    private TableColumn nomAnimalColumn;
    @FXML
    private TableColumn espcieAnimalColumn;
    @FXML
    private TableColumn parcelaAnimalColumn;
    @FXML
    private TextField idAnimalFind;
    @FXML
    private TextField UpdateNomAnimal;
    @FXML
    private TextField UpdateIdEspecie;
    @FXML
    private TextField UpdateIdParcela;
    @FXML
    private Button buttonUpdate;

    private ArrayList<Animal> animalsArray;
    private ObservableList<AnimalsTableModel> animalsTableModels = FXCollections.observableArrayList();



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

        this.UpdateNomAnimal.setDisable(true);
        this.UpdateIdEspecie.setDisable(true);
        this.UpdateIdParcela.setDisable(true);
        this.buttonUpdate.setDisable(true);

        this.especieCrud = new EspecieCrud();
        this.animalCrud = new AnimalCrud();
        this.parcelaCrud = new ParcelaCrud();
        this.arribadaCrud = new ArribadaCrud();
        this.sortidaCrud = new SortidaCrud();

        this.idEspcieColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomEspcieColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

        this.tableEspecie.setItems(this.especiesTableModels);

        this.idAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.espcieAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("especieNom"));
        this.parcelaAnimalColumn.setCellValueFactory(new PropertyValueFactory<>("parcelaNom"));

        this.animalTable.setItems(animalsTableModels);


        this.animalsArray = animalCrud.obtenirAnimals();

        this.especies = especieCrud.obtienirEspecies();
        this.rellenarTablaEspcies();
        this.rellenarTablaAnimals();

    }



    public void rellenarTablaEspcies(){

        for (Especie e: this.especies) {
            this.especiesTableModels.add(new EspeciesTableModel(e.getId(),e.getNom()));
        }
    }


    public void rellenarTablaAnimals(){

        for (Animal a: this.animalsArray) {
            this.animalsTableModels.add(new AnimalsTableModel(a.getId(),a.getNom(),a.getEspecie().getNom(),a.getParcela().getNom()));
        }
    }


    public void nouAlimal(ActionEvent actionEvent) {

        int idParcela = Integer.parseInt(this.textIdParcela.getText());
        int idEspecie = Integer.parseInt(this.textIdEspecie.getText());
        String nomAnimal = this.textNomAnimal.getText();

        Parcela parcela = parcelaCrud.obtenirParcela(idParcela);
        Especie especie = especieCrud.obtenirEspecie(idEspecie);

        Animal animal = new Animal();
        animal.setNom(nomAnimal);
        animal.setEspecie(especie);
        animal.setParcela(parcela);

        this.animalCrud.insertAnimal(animal);

        this.animalsArray.clear();
        this.animalsArray = this.animalCrud.obtenirAnimals();

        animal = new Animal();
        animal = this.animalCrud.obtenirAnimal(this.animalsArray.size());
        System.out.println(animal);
        System.out.println(this.animalsArray.size());
        this.animalsTableModels.add(new AnimalsTableModel(animal.getId(),animal.getNom(),
                animal.getEspecie().getNom(),
                animal.getParcela().getNom()));

        this.textIdParcela.setText("");
        this.textIdEspecie.setText("");
        this.textNomAnimal.setText("");

        Arribada arribada = new Arribada();
        arribada.setAnimal(animal);
        arribada.setParcela(animal.getParcela());

        this.arribadaCrud.insertArribada(arribada);

    }


    public void eliminaAnimal(ActionEvent actionEvent) {

        int idAnimal = Integer.parseInt(this.eliminaAnimalText.getText());

        Animal animal = new Animal();
        animal.setId(idAnimal);

        this.animalCrud.deleteAnimal(animal);

        this.eliminaAnimalText.setText("");



        for (AnimalsTableModel a:this.animalsTableModels) {
            if(a.getId()==idAnimal){
                this.animalsTableModels.remove(a);
            }
        }


    }

    public void eliminaEspecie(ActionEvent actionEvent) {

        int idEspecie = Integer.parseInt(eliminaEspecieText.getText());

       Especie especie = this.especieCrud.obtenirEspecie(idEspecie);
       this.especieCrud.deleteEspecie(especie);
        eliminaEspecieText.setText("");

        for (EspeciesTableModel a: this.especiesTableModels) {
            if(a.getId() == idEspecie){
                this.especiesTableModels.remove(a);
            }
        }
    }

    public void novaEspecie(ActionEvent actionEvent) {

        Especie especie = new Especie();
        especie.setNom(this.novaEspecieText.getText());

        especieCrud.insertEspecie(especie);
        this.novaEspecieText.setText("");

        this.especies = especieCrud.obtienirEspecies();

        especie = this.especieCrud.obtenirEspecie(this.especies.size()-1);

        System.out.println(this.especies.size());



        this.especiesTableModels.add(new EspeciesTableModel(especie.getId(),especie.getNom()));

    }



    public void buscarAnimal(KeyEvent keyEvent) {

        try {
            int idAnimal = Integer.parseInt(this.idAnimalFind.getText());

            Animal animal = this.animalCrud.obtenirAnimal(idAnimal);

            if(animal.getNom() == null){
            this.resetUpdateText();
            }
            else {
                this.UpdateNomAnimal.setDisable(false);
                this.UpdateIdEspecie.setDisable(false);
                this.UpdateIdParcela.setDisable(false);
                this.buttonUpdate.setDisable(false);


                this.UpdateNomAnimal.setText(animal.getNom());
                this.UpdateIdEspecie.setText(animal.getEspecie().getId()+"");
                this.UpdateIdParcela.setText(animal.getParcela().getId()+"");

            }
        }catch (Exception e){
        this.resetUpdateText();
        }




    }


    public void resetUpdateText(){
        this.UpdateNomAnimal.setDisable(true);
        this.UpdateIdEspecie.setDisable(true);
        this.UpdateIdParcela.setDisable(true);
        this.buttonUpdate.setDisable(true);
        this.UpdateNomAnimal.setText("");
        this.UpdateIdEspecie.setText("");
        this.UpdateIdParcela.setText("");
        this.idAnimalFind.setText("");
    }


    public void actualitzaAnimal(ActionEvent actionEvent) {

        String nom = this.UpdateNomAnimal.getText();
        int idEspecie = Integer.parseInt(this.UpdateIdEspecie.getText());
        int idParcela = Integer.parseInt(this.UpdateIdParcela.getText());
        int idAnimal = Integer.parseInt(this.idAnimalFind.getText());
        Animal animal = this.animalCrud.obtenirAnimal(idAnimal);

        if(idParcela!=animal.getParcela().getId()){


            Sortida sortida = new Sortida();
            sortida.setAnimal(animal);
            sortida.setParcela(animal.getParcela());
            this.sortidaCrud.insertSortida(sortida);


            Arribada arribada = new Arribada();
            arribada.setAnimal(animal);
            animal.setParcela(this.parcelaCrud.obtenirParcela(idParcela));
            arribada.setParcela(animal.getParcela());
            this.arribadaCrud.insertArribada(arribada);
        }else animal.setParcela(this.parcelaCrud.obtenirParcela(idParcela));


        animal.setEspecie(this.especieCrud.obtenirEspecie(idEspecie));
        animal.setNom(nom);

        this.animalCrud.updateAnimal(animal);

        this.resetUpdateText();

        this.animalsTableModels.set(animal.getId()-1,new AnimalsTableModel(
                animal.getId(),animal.getNom(),animal.getEspecie().getNom(),animal.getParcela().getNom()));

    }














    @FXML
    public void canviarEscenaAction(ActionEvent actionEvent) {

        Stage stage = null;
        Parent root = null;
        try {

            if(actionEvent.getSource()==home){
                System.out.println("Home");

                stage=(Stage) home.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/sample.fxml"));
            }else if(actionEvent.getSource()==animals){
                System.out.println("animals");

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
