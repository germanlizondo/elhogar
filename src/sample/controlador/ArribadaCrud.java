package sample.controlador;

import sample.controlador.interfaces.IArribada;
import sample.model.Animal;
import sample.model.Arribada;
import sample.model.Parcela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ArribadaCrud implements IArribada {
    private ArrayList<Arribada> arribades = new ArrayList<Arribada>();
    Database db = new Database();
    private PreparedStatement preparedStatement;


    @Override
    public ArrayList<Arribada> obtenirArrbades() {

        try{
            this.db.getConnection().setAutoCommit(false);

            String getArribades = "SELECT * FROM arribades";
            AnimalCrud animalCrud = new AnimalCrud();
            ParcelaCrud parcelaCrud = new ParcelaCrud();
            this.preparedStatement = db.getConnection().prepareStatement(getArribades);

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                Animal animal = animalCrud.obtenirAnimal(rs.getInt("id_animal"));
                Parcela parcela = parcelaCrud.obtenirParcela(rs.getInt("id_parcela"));
                Arribada arribada = new Arribada(animal,parcela);
                arribada.setDate(rs.getDate("dia_arribada"));

                this.arribades.add(arribada);
            }
            this.db.getConnection().commit();

            return this.arribades;
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
    public Arribada obtenirArraibada(int id) {
        return null;
    }

    @Override
    public void updateArribades(Arribada arribada) {

    }

    @Override
    public void deleteArribades(Arribada arribada) {

    }

    @Override
    public void insertArribada(Arribada arribada) {
        String insertAnimal = "INSERT INTO arribades (id_animal,id_parcela,dia_arribada) VALUES (?,?,?)";
        try {
            this.db.getConnection().setAutoCommit(false);

            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            this.preparedStatement = db.getConnection().prepareStatement(insertAnimal);
            this.preparedStatement.setInt(1,arribada.getAnimal().getId());
            this.preparedStatement.setInt(2,arribada.getParcela().getId());
            this.preparedStatement.setDate(3,sqlDate);

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
}
