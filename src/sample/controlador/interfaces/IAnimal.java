package sample.controlador.interfaces;

import sample.model.Animal;

import java.util.ArrayList;

public interface IAnimal {
     ArrayList<Animal> obtenirAnimals();
     Animal obtenirAnimal(int id);
     void insertAnimal(Animal animal);
     void updateAnimal(Animal animal);
     void deleteAnimal(Animal animal);
}
