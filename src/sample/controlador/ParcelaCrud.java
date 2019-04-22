package sample.controlador;

import sample.controlador.interfaces.IParcela;
import sample.model.Aliment;
import sample.model.Animal;
import sample.model.Parcela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParcelaCrud implements IParcela {

    private ArrayList<Parcela> parceles = new ArrayList<Parcela>();
    private Database db = new Database();
    private PreparedStatement preparedStatement;

    public ParcelaCrud() {
    }

    @Override
    public ArrayList<Parcela> obtenirParceles() {
        String getParceles = "SELECT * FROM parceles";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getParceles);

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                this.parceles.add(new Parcela(rs.getInt("id"),
                        rs.getString("nom"),rs.getFloat("hectareas"),
                        rs.getInt("zonaOmbres"),rs.getBoolean("aiguaNatural")));
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

        return this.parceles;
    }

    @Override
    public Parcela obtenirParcela(int id) {
        String getParcela = "SELECT * FROM parceles a WHERE a.id = ?";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getParcela);
            this.preparedStatement.setInt(1,id);
            ResultSet rs = this.preparedStatement.executeQuery();
            Parcela parcela = new Parcela();
            while (rs.next()){
                parcela.setId(rs.getInt("id"));
                parcela.setNom(rs.getString("nom"));
                parcela.setHectareas(rs.getFloat("hectareas"));
                parcela.setZonesOmbres(rs.getInt("zonaOmbres"));
                parcela.setAiguaNatural(rs.getBoolean("aiguaNatural"));
            }
            this.db.getConnection().commit();

            return parcela;
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
    public void insertParcela(Parcela parcela) {

        String insertParcela = "INSERT INTO parceles (nom,hectareas,zonaOmbres,aiguaNatural) VALUES (?,?,?,?)";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(insertParcela);
            this.preparedStatement.setString(1,parcela.getNom());
            this.preparedStatement.setFloat(2,parcela.getHectareas());
            this.preparedStatement.setInt(3,parcela.getZonesOmbres());
            this.preparedStatement.setBoolean(4,parcela.isAiguaNatural());

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
    public void updateParcela(Parcela parcela) {

        String updateParcela = "UPDATE parceles SET nom=?,hectareas=?,zonaOmbres=?,aiguaNatural=? WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(updateParcela);
            this.preparedStatement.setString(1,parcela.getNom());
            this.preparedStatement.setFloat(2,parcela.getHectareas());
            this.preparedStatement.setInt(3,parcela.getZonesOmbres());
            this.preparedStatement.setBoolean(4,parcela.isAiguaNatural());
            this.preparedStatement.setInt(5,parcela.getId());
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

    @Override
    public void deleteParcela(Parcela parcela) {

        String deleteParcela = "DELETE FROM parceles WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(deleteParcela);
            this.preparedStatement.setInt(1,parcela.getId());
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
