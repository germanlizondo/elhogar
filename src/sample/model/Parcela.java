package sample.model;

import java.util.ArrayList;

public class Parcela {

    private int id;
    private String nom;
    private float hectareas;
    private ArrayList<Animal> animales = new ArrayList<Animal>();
    private int zonesOmbres = 0;
    private boolean aiguaNatural;
    private ArrayList<Aliment> aliments = new ArrayList<Aliment>();

    public Parcela() {
    }

    public Parcela(int id, String nom, float heateras, int zonesOmbres, boolean aiguaNatural) {
        this.id = id;
        this.nom = nom;
        this.hectareas = heateras;
        this.zonesOmbres = zonesOmbres;
        this.aiguaNatural = aiguaNatural;

    }

    @Override
    public String toString() {
        return "Parcela{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", hectareas=" + hectareas +
                ", animales=" + animales +
                ", zonesOmbres=" + zonesOmbres +
                ", aiguaNatural=" + aiguaNatural +
                ", aliments=" + aliments +
                '}';
    }

    public void addAnimal(Animal animal){
        this.animales.add(animal);
    }

    public void removeAnimal(Animal animal){
        this.animales.remove(animal);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getHectareas() {
        return hectareas;
    }

    public void setHectareas(float hectareas) {
        this.hectareas = hectareas;
    }

    public ArrayList<Animal> getAnimales() {
        return animales;
    }

    public void setAnimales(ArrayList<Animal> animales) {
        this.animales = animales;
    }

    public int getZonesOmbres() {
        return zonesOmbres;
    }

    public void setZonesOmbres(int zonesOmbres) {
        this.zonesOmbres = zonesOmbres;
    }

    public boolean isAiguaNatural() {
        return aiguaNatural;
    }

    public void setAiguaNatural(boolean aiguaNatural) {
        this.aiguaNatural = aiguaNatural;
    }

    public ArrayList<Aliment> getAliments() {
        return aliments;
    }

    public void setAliments(Aliment aliment) {
        this.aliments.add(aliment);
    }
}
