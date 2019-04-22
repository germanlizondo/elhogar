package sample.controlador.interfaces;

import sample.model.Parcela;

import java.util.ArrayList;

public interface IParcela {
    ArrayList<Parcela> obtenirParceles();
    Parcela obtenirParcela(int id);
    void insertParcela(Parcela parcela);
    void updateParcela(Parcela parcela);
    void deleteParcela(Parcela parcela);
}
