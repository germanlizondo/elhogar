package sample.controlador;

import sample.controlador.Database;
import sample.controlador.interfaces.IAliments;
import sample.model.Aliment;
import sample.model.Aliment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AlimentCrud implements IAliments {

    private ArrayList<Aliment> aliments = new ArrayList<Aliment>();
    private Database db = new Database();
    private PreparedStatement preparedStatement;

    @Override
    public ArrayList<Aliment> obtenirAliments() {
        String getAliments = "SELECT * FROM aliments";
        try{
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(getAliments);

            ResultSet rs = this.preparedStatement.executeQuery();
            while (rs.next()) {
                this.aliments.add(new Aliment(rs.getInt("id"),rs.getString("nom")));
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

        return this.aliments;
    }

    @Override
    public Aliment obtenirAliment(int id) {
        String getAliment = "SELECT * FROM aliments a WHERE a.id = ?";
        try{
            this.db.getConnection().setAutoCommit(false);
            this.preparedStatement = db.getConnection().prepareStatement(getAliment);
            this.preparedStatement.setInt(1,id);
            ResultSet rs = this.preparedStatement.executeQuery();
            Aliment aliment = new Aliment();
            while (rs.next()){
                aliment.setId(rs.getInt("id"));
                aliment.setNom(rs.getString("nom"));
            }
            this.db.getConnection().commit();

            return aliment;
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
    public void insertAliment(Aliment aliment) {
        String insertAliment = "INSERT INTO aliments (nom) VALUES (?)";
        try {
          this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(insertAliment);
            this.preparedStatement.setString(1,aliment.getNom());
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
    public void updateAliment(Aliment aliment) {
        String insertAliment = "UPDATE aliments SET nom=? WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(insertAliment);
            this.preparedStatement.setString(1,aliment.getNom());
            this.preparedStatement.setInt(2,aliment.getId());
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
    public void deleteAliment(Aliment aliment) {
        String deleteAliment = "DELETE FROM aliments WHERE id=?";
        try {
            this.db.getConnection().setAutoCommit(false);

            this.preparedStatement = db.getConnection().prepareStatement(deleteAliment);
            this.preparedStatement.setInt(1,aliment.getId());
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
