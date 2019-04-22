package sample.controlador.interfaces;

import sample.model.Aliment;

import java.util.ArrayList;

public interface IAliments {
    ArrayList<Aliment> obtenirAliments();
    Aliment obtenirAliment(int id);
    void insertAliment(Aliment aliment);
    void updateAliment(Aliment aliment);
    void deleteAliment(Aliment aliment);
}
