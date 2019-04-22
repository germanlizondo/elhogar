package sample.model;

public class Animal {

    private int id;
    private Especie especie;
    private Parcela parcela;
    private String nom;

    public Animal() {
    }

    public Animal(int id, String nom,Especie especie, Parcela parcela) {
        this.id = id;
        this.especie = especie;
        this.nom = nom;
        this.parcela = parcela;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", especie=" + especie +
                ", parcela=" + parcela +
                ", nom='" + nom + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
