package sample.vista.TableModels;

import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class SortidesTableModel {
    private SimpleStringProperty animal;
    private SimpleStringProperty parcela;
    private SimpleStringProperty date;

    public SortidesTableModel(String animal, String parcela, Date date) {
        this.animal = new SimpleStringProperty(animal);
        this.parcela = new SimpleStringProperty(parcela);
        this.date = new SimpleStringProperty(date.toString());
    }

    public String getAnimal() {
        return animal.get();
    }

    public SimpleStringProperty animalProperty() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal.set(animal);
    }

    public String getParcela() {
        return parcela.get();
    }

    public SimpleStringProperty parcelaProperty() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela.set(parcela);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
