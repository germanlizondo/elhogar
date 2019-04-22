package sample.controlador.interfaces;

import sample.model.Especie;

import java.util.ArrayList;

public interface IEspecie {
    ArrayList<Especie> obtienirEspecies();
    Especie obtenirEspecie(int id);
    void insertEspecie(Especie especie);
    void updateEspecie(Especie especie);
    void deleteEspecie(Especie especie);
}
