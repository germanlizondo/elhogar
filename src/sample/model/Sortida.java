package sample.model;

import java.util.Date;

public class Sortida {

    private Animal animal;
    private Parcela parcela;
    private Date date = new Date();

    public Sortida() {
    }

    public Sortida(Animal animal, Parcela parcela) {
        this.animal = animal;
        this.parcela = parcela;
    }



    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
