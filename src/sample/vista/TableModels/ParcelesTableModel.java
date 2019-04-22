package sample.vista.TableModels;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ParcelesTableModel {

    private SimpleIntegerProperty id;
    private SimpleStringProperty nom;
    private SimpleFloatProperty hectareas;
    private SimpleIntegerProperty zonaOmbres;
    private SimpleBooleanProperty aiguaNatural;


    public ParcelesTableModel(int id, String nom, float hectareas, int zonaOmbres, boolean aiguaNatural) {
        this.id = new SimpleIntegerProperty(id);
        this.nom = new SimpleStringProperty(nom);
        this.hectareas = new SimpleFloatProperty(hectareas);
        this.zonaOmbres = new SimpleIntegerProperty(zonaOmbres);
        this.aiguaNatural = new SimpleBooleanProperty(aiguaNatural);
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

    public float getHectareas() {
        return hectareas.get();
    }

    public SimpleFloatProperty hectareasProperty() {
        return hectareas;
    }

    public void setHectareas(float hectareas) {
        this.hectareas.set(hectareas);
    }

    public int getZonaOmbres() {
        return zonaOmbres.get();
    }

    public SimpleIntegerProperty zonaOmbresProperty() {
        return zonaOmbres;
    }

    public void setZonaOmbres(int zonaOmbres) {
        this.zonaOmbres.set(zonaOmbres);
    }

    public boolean isAiguaNatural() {
        return aiguaNatural.get();
    }

    public SimpleBooleanProperty aiguaNaturalProperty() {
        return aiguaNatural;
    }

    public void setAiguaNatural(boolean aiguaNatural) {
        this.aiguaNatural.set(aiguaNatural);
    }
}
