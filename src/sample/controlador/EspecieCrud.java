package sample.controlador;

import sample.controlador.interfaces.IEspecie;
import sample.model.Especie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EspecieCrud implements IEspecie {

    private Database db = new Database();
    private PreparedStatement preparedStatement;
    private ArrayList<Especie> especies = new ArrayList<>();
    @Override
    public ArrayList<Especie> obtienirEspecies() {
        String getEspecies = "SELECT * FROM especies";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getEspecies);

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                this.especies.add(new Especie(rs.getInt("id"),rs.getString("nom")));
            }
            this.db.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.db.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return this.especies;
    }

    @Override
    public Especie obtenirEspecie(int id) {
        String getEspecie = "SELECT * FROM especies a WHERE a.id = ?";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getEspecie);
            this.preparedStatement.setInt(1,id);
            ResultSet rs = this.preparedStatement.executeQuery();
            Especie especie = new Especie();
            while (rs.next()){
                especie.setId(rs.getInt("id"));
                especie.setNom(rs.getString("nom"));
            }
            this.db.getConnection().commit();

            return especie;
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
    public void insertEspecie(Especie especie) {
        String insertEspecies = "INSERT INTO especies (nom) VALUES (?)";

        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(insertEspecies);
            this.preparedStatement.setString(1,especie.getNom());
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
    public void updateEspecie(Especie especie) {

    }

    @Override
    public void deleteEspecie(Especie especie) {
        String deleteEspecie = "DELETE FROM especies WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(deleteEspecie);
            this.preparedStatement.setInt(1,especie.getId());
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
