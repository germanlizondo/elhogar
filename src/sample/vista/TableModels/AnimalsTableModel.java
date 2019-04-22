package sample.vista.TableModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AnimalsTableModel {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleStringProperty especieNom;
    private SimpleStringProperty parcelaNom;

    public AnimalsTableModel(int id,String nom,String espcieNom,String parcelaNom) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.especieNom = new SimpleStringProperty(espcieNom);
        this.parcelaNom = new SimpleStringProperty(parcelaNom);

    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }

    public SimpleStringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getEspecieNom() {
        return especieNom.get();
    }

    public SimpleStringProperty especieNomProperty() {
        return especieNom;
    }

    public void setEspecieNom(String especieNom) {
        this.especieNom.set(especieNom);
    }

    public String getParcelaNom() {
        return parcelaNom.get();
    }

    public SimpleStringProperty parcelaNomProperty() {
        return parcelaNom;
    }

    public void setParcelaNom(String parcelaNom) {
        this.parcelaNom.set(parcelaNom);
    }
}
