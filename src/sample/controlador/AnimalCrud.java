package sample.controlador;

import sample.controlador.interfaces.IAnimal;
import sample.model.Animal;
import sample.model.Arribada;
import sample.model.Especie;
import sample.model.Parcela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalCrud implements IAnimal {
    private ArrayList<Animal> animals = new ArrayList<Animal>();
    private Database db = new Database();
    private PreparedStatement preparedStatement;
    private EspecieCrud especieCrud = new EspecieCrud();
    private ParcelaCrud parcelaCrud = new ParcelaCrud();
    private ArribadaCrud arribadaCrud = new ArribadaCrud();

    public AnimalCrud() {
    }

    @Override
    public ArrayList<Animal> obtenirAnimals() {
        String getAnimals = "SELECT * FROM animals";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getAnimals);
            Especie especie;
            Parcela parcela;

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                especie  = this.especieCrud.obtenirEspecie(rs.getInt("id_especie"));
                parcela = this.parcelaCrud.obtenirParcela(rs.getInt("id_parcela"));
                this.animals.add(new Animal(rs.getInt("id"),rs.getString("nom"),especie,parcela));
            }
            this.db.getConnection().commit();

            return this.animals;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.db.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public Animal obtenirAnimal(int id) {
        String getAnimal = "SELECT * FROM animals a WHERE a.id = ?";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getAnimal);
            this.preparedStatement.setInt(1,id);
            ResultSet rs = this.preparedStatement.executeQuery();
            Animal animal= new Animal();
            Especie especie;
            Parcela parcela;

            while (rs.next()){

                animal.setId(rs.getInt("id"));
                animal.setNom(rs.getString("nom"));
                especie  = this.especieCrud.obtenirEspecie(rs.getInt("id_especie"));
                parcela = this.parcelaCrud.obtenirParcela(rs.getInt("id_parcela"));
                animal.setParcela(parcela);
                animal.setEspecie(especie);

            }
            this.db.getConnection().commit();

            return animal;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.db.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return null;
        }    }

    @Override
    public void insertAnimal(Animal animal) {
        String insertAnimal = "INSERT INTO animals (nom,id_especie,id_parcela) VALUES (?,?,?)";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(insertAnimal);
            this.preparedStatement.setString(1,animal.getNom());
            this.preparedStatement.setInt(2,animal.getEspecie().getId());
            this.preparedStatement.setInt(3,animal.getParcela().getId());

            this.preparedStatement.execute();
            this.db.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.db.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    @Override
    public void updateAnimal(Animal animal) {

        String updateParcela = "UPDATE animals SET nom=?,id_especie=?,id_parcela=? WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(updateParcela);
            this.preparedStatement.setString(1,animal.getNom());
            this.preparedStatement.setInt(2,animal.getEspecie().getId());
            this.preparedStatement.setInt(3,animal.getParcela().getId());
            this.preparedStatement.setInt(4,animal.getId());
            this.preparedStatement.executeUpdate();
            this.db.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                this.preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    this.db.getConnection().rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteAnimal(Animal animal) {

        String deleteAnimal = "DELETE FROM animals WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(deleteAnimal);
            this.preparedStatement.setInt(1,animal.getId());
            this.preparedStatement.executeUpdate();
            this.db.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.db.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
