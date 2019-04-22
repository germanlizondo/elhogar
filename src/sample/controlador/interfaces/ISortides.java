package sample.controlador.interfaces;

import sample.model.Sortida;

import java.util.ArrayList;

public interface ISortides {

    ArrayList<Sortida> obtenirSortides();
    Sortida obtenirSortida(int id);
    void updateSortida(Sortida sortida);
    void deleteSortida(Sortida sortida);
    void insertSortida(Sortida sortida);
}
