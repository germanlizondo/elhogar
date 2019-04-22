package sample.vista.TableModels;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class AlimentsTableModel {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;


    public AlimentsTableModel(int id, String nom) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
    }

    public int getId() {
        return id.get();
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public String getNom() {
        return nom.get();
    }


    public void setNom(String nom) {
        this.nom.set(nom);
    }
}
