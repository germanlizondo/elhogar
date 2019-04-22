package sample.controlador;

import sample.controlador.interfaces.ISortides;
import sample.model.Animal;
import sample.model.Parcela;
import sample.model.Sortida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SortidaCrud implements ISortides {

    private ArrayList<Sortida> sortidas = new ArrayList<>();
    Database db = new Database();
    private PreparedStatement preparedStatement;
    private AnimalCrud animalCrud = new AnimalCrud();
    private ParcelaCrud parcelaCrud = new ParcelaCrud();
    @Override
    public ArrayList<Sortida> obtenirSortides() {
        try{
            this.db.getConnection().setAutoCommit(false);

            String getSortides = "SELECT * FROM sortides";

            this.preparedStatement = db.getConnection().prepareStatement(getSortides);

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                Animal animal = animalCrud.obtenirAnimal(rs.getInt("id_animal"));
                Parcela parcela = parcelaCrud.obtenirParcela(rs.getInt("id_parcela"));
                Sortida sortida = new Sortida(animal,parcela);
                sortida.setDate(rs.getDate("dia_sortida"));

                this.sortidas.add(sortida);
            }
            this.db.getConnection().commit();

            return this.sortidas;
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
    public Sortida obtenirSortida(int id) {
        return null;
    }

    @Override
    public void updateSortida(Sortida sortida) {

    }

    @Override
    public void deleteSortida(Sortida sortida) {

    }

    @Override
    public void insertSortida(Sortida sortida) {

        String insertSortida = "INSERT INTO sortides (id_animal,id_parcela,dia_sortida) VALUES (?,?,?)";
        try {
            this.db.getConnection().setAutoCommit(false);

            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
            this.preparedStatement = db.getConnection().prepareStatement(insertSortida);
            this.preparedStatement.setInt(1,sortida.getAnimal().getId());
            this.preparedStatement.setInt(2,sortida.getParcela().getId());
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
