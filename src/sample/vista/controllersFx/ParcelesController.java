package sample.vista.controllersFx;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.controlador.ParcelaCrud;
import sample.controlador.AlimentCrud;
import sample.model.Aliment;
import sample.model.Parcela;
import sample.vista.TableModels.AlimentsTableModel;
import sample.vista.TableModels.ParcelesTableModel;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ParcelesController implements Initializable {


    private ParcelaCrud parcelaCrud;
   private ArrayList<Parcela> parcelas;

    /*PARCELES ELEMENTS*/
    @FXML
    private Button novaParcela;
    @FXML
    private TextField textNomParcela;
    @FXML
    private TextField textHectareas;
    @FXML
    private TextField textZonesOmbra;
    @FXML
    private CheckBox checkAiguaNatural;

    @FXML
    private TableView tableParceles;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nomColumn;
    @FXML
    private TableColumn hetareasColumn;
    @FXML
    private TableColumn zonesOmbresColumn;
    @FXML
    private TableColumn aiguaNaturalColumn;
    @FXML
    private TextField eliminaParcelaText;
    @FXML
    private Button eliminaParcelaButton;


    private ObservableList<ParcelesTableModel> parcelesTableModels = FXCollections.observableArrayList();

    /*UPDATE PARCELA*/
    @FXML
    private TextField buscarParcela;
    @FXML
    private TextField actualitzaParcelaNom;
    @FXML
    private TextField textHectareasUpadate;
    @FXML
    private TextField zonaOmbresUpdate;
    @FXML
    private CheckBox checkUpdateAigua;
    @FXML
    private Button updateParcela;


    /*ALIMENTS ELEMENTS*/
    private AlimentCrud alimentCrud;
    private ArrayList<Aliment> aliments;
    @FXML
    private Button home;
    @FXML
    private Button animals;
    @FXML
    private Button parceles;
    @FXML
    private Button arribades;
    @FXML
    private TextField nouAlimentText;
    @FXML
    private TextField eliminaText;
    @FXML
    private TableView tableAliments;
    @FXML
    private TableColumn idAlimentColumn;
    @FXML
    private TableColumn nomAlimentColumn;


    private ObservableList<AlimentsTableModel> alimentsTableModels = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.parcelaCrud = new ParcelaCrud();
        this.parcelas = this.parcelaCrud.obtenirParceles();

        this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        this.hetareasColumn.setCellValueFactory(new PropertyValueFactory<>("hectareas"));
        this.zonesOmbresColumn.setCellValueFactory(new PropertyValueFactory<>("zonaOmbres"));
        this.aiguaNaturalColumn.setCellValueFactory(new PropertyValueFactory<>("aiguaNatural"));

        this.tableParceles.setItems(this.parcelesTableModels);
        this.rellenarParcelasTabla();

       this.alimentCrud = new AlimentCrud();
        this.aliments = this.alimentCrud.obtenirAliments();

      this.idAlimentColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
      this.nomAlimentColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

     this.tableAliments.setItems(this.alimentsTableModels);
       this.rellenarAlimentosTabla();




        this.zonaOmbresUpdate.setDisable(true);
        this.textHectareasUpadate.setDisable(true);
        this.actualitzaParcelaNom.setDisable(true);
        this.checkUpdateAigua.setDisable(true);
        this.updateParcela.setDisable(true);



    }
    public void rellenarParcelasTabla(){

        for (Parcela a: this.parcelas) {
            this.parcelesTableModels.add(new ParcelesTableModel(a.getId(),a.getNom(),a.getHectareas(),a.getZonesOmbres(),a.isAiguaNatural()));
        }
    }

    public void rellenarAlimentosTabla(){

        for (Aliment a: this.aliments) {
            this.alimentsTableModels.add(new AlimentsTableModel(a.getId(),a.getNom()));
        }
    }


    public void nouAliment(ActionEvent actionEvent) {

        Aliment aliment = new Aliment();
        aliment.setNom(this.nouAlimentText.getText().toString());
        this.alimentCrud.insertAliment(aliment);
      this.nouAlimentText.setText("");

      this.aliments = this.alimentCrud.obtenirAliments();

        Aliment lastAliment = this.aliments.get(this.aliments.size() - 1);
        this.alimentsTableModels.add(new AlimentsTableModel(lastAliment.getId(),lastAliment.getNom()));

    }




    public void eliminaAliment(ActionEvent actionEvent) {

        int idAliment = Integer.parseInt(this.eliminaText.getText().toString());
        this.eliminaText.setText("");

       Aliment aliment = this.alimentCrud.obtenirAliment(idAliment);
        this.alimentCrud.deleteAliment(aliment);

        for (AlimentsTableModel a: this.alimentsTableModels) {
            if(a.getId() == idAliment){
                this.alimentsTableModels.remove(a);
            }
        }


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

                stage=(Stage) home.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("../fxml/animalsPage.fxml"));

            }else if(actionEvent.getSource()==parceles){
                System.out.println("parceles");


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


    public void novaParcela(ActionEvent actionEvent) {

        String nom = this.textNomParcela.getText().toString();
        float hectareas = Float.parseFloat(this.textHectareas.getText());
        int zonaombres = Integer.parseInt(this.textZonesOmbra.getText());
        boolean aiguaNatural;
        if(this.checkAiguaNatural.isSelected()) aiguaNatural = true;
        else aiguaNatural=false;

        Parcela parcela = new Parcela();
        parcela.setNom(nom);
        parcela.setHectareas(hectareas);
        parcela.setAiguaNatural(aiguaNatural);
        parcela.setZonesOmbres(zonaombres);
        this.parcelaCrud.insertParcela(parcela);

        this.textNomParcela.setText("");
        this.textHectareas.setText("");
       this.textZonesOmbra.setText("");
       this.checkAiguaNatural.setSelected(false);

       this.parcelas = this.parcelaCrud.obtenirParceles();
       parcela = this.parcelas.get(this.parcelas.size()-1);
       this.parcelesTableModels.add(new ParcelesTableModel(parcela.getId(),parcela.getNom(),parcela.getHectareas(),
               parcela.getZonesOmbres(),parcela.isAiguaNatural()));

    }

    public void eliminaParcela(ActionEvent actionEvent) {

        int idParcela = Integer.parseInt(this.eliminaParcelaText.getText().toString());
        this.eliminaParcelaText.setText("");

        Parcela parcela = this.parcelaCrud.obtenirParcela(idParcela);
        this.parcelaCrud.deleteParcela(parcela);

        for (ParcelesTableModel p:this.parcelesTableModels) {
            if(p.getId() ==idParcela){
                this.parcelesTableModels.remove(p);
            }
        }

    }


    public void buscarParcelaUpdate(KeyEvent keyEvent) {
try{

    int idParcela =Integer.parseInt(this.buscarParcela.getText());

    Parcela parcela = this.parcelaCrud.obtenirParcela(idParcela);
    this.zonaOmbresUpdate.setText(parcela.getZonesOmbres()+"");
    this.actualitzaParcelaNom.setText(parcela.getNom());
    this.textHectareasUpadate.setText(parcela.getHectareas()+"");
    if(parcela.isAiguaNatural()) this.checkUpdateAigua.setSelected(true);
    else this.checkUpdateAigua.setSelected(false);



    this.zonaOmbresUpdate.setDisable(false);
    this.textHectareasUpadate.setDisable(false);
    this.actualitzaParcelaNom.setDisable(false);
    this.checkUpdateAigua.setDisable(false);
    this.updateParcela.setDisable(false);


}catch (Exception e){
    System.out.println("ESCRIU UN INT");
    this.zonaOmbresUpdate.setText("");
    this.textHectareasUpadate.setText("");
    this.actualitzaParcelaNom.setText("");
    this.checkUpdateAigua.setSelected(false);

    this.zonaOmbresUpdate.setDisable(true);
    this.textHectareasUpadate.setDisable(true);
    this.actualitzaParcelaNom.setDisable(true);
    this.checkUpdateAigua.setDisable(true);
    this.updateParcela.setDisable(true);
}



    }

    public void updateParcela(ActionEvent actionEvent) {

        Parcela parcela = new Parcela();
        parcela.setId(Integer.parseInt(this.buscarParcela.getText()));
        parcela.setNom(this.actualitzaParcelaNom.getText());
        parcela.setZonesOmbres(Integer.parseInt(this.zonaOmbresUpdate.getText()));
        parcela.setHectareas(Float.parseFloat(this.textHectareasUpadate.getText()));
        if(this.checkUpdateAigua.isSelected()) parcela.setAiguaNatural(true);
        else parcela.setAiguaNatural(false);

        System.out.println(parcela);

        this.parcelaCrud.updateParcela(parcela);

        this.buscarParcela.setText("");
        this.actualitzaParcelaNom.setText("");
        this.zonaOmbresUpdate.setText("");
        this.textHectareasUpadate.setText("");
        this.checkUpdateAigua.setSelected(false);

        this.parcelesTableModels.set(parcela.getId()-1,new ParcelesTableModel(parcela.getId(),parcela.getNom(),
        parcela.getHectareas(),parcela.getZonesOmbres(),parcela.isAiguaNatural()));

        this.zonaOmbresUpdate.setDisable(true);
        this.textHectareasUpadate.setDisable(true);
        this.actualitzaParcelaNom.setDisable(true);
        this.checkUpdateAigua.setDisable(true);
        this.updateParcela.setDisable(true);
    }
}
