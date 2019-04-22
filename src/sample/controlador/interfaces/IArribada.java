package sample.controlador.interfaces;

import sample.model.Arribada;

import java.util.ArrayList;

public interface IArribada {
    ArrayList<Arribada> obtenirArrbades();
    Arribada obtenirArraibada(int id);
    void updateArribades(Arribada arribada);
    void deleteArribades(Arribada arribada);
    void insertArribada(Arribada arribada);
}
