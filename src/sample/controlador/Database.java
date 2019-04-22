package sample.controlador;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {

    private Connection connection;
    private Statement statement;


    public Database() {

        try {
            this.connection = this.createConnection();
            this.statement = this.connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public void crearTablas(){

        try {
            this.connection.setAutoCommit(false);
            String crearTablaAnimals= "CREATE TABLE IF NOT EXISTS animals (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "nom VARCHAR (250) NOT NULL," +
                    "id_especie INT NOT NULL," +
                    "id_parcela INT NOT NULL" +
                    ")";

            String crearTablaParcela= "CREATE TABLE IF NOT EXISTS parceles (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "nom VARCHAR (250) NOT NULL," +
                    "hectareas FLOAT NOT NULL," +
                    "zonaOmbres INT NOT NULL DEFAULT 0," +
                    "aiguaNatural BIT " +
                    ")";

            String crearTablaRealacionAlimentsParceles=  "CREATE TABLE IF NOT EXISTS aliments_parceles (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "id_aliment INT NOT NULL," +
                    "id_parcela INT NOT NULL" +
                    ")";
            String crearTablaAliments=  "CREATE TABLE IF NOT EXISTS aliments (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "nom VARCHAR (250) NOT NULL" +
                    ")";
            String crearTablaEspecie=  "CREATE TABLE IF NOT EXISTS especies (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "nom VARCHAR (250) NOT NULL" +
                    ")";
            String crearTablaArribades=  "CREATE TABLE IF NOT EXISTS arribades (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "id_animal INT NOT NULL," +
                    "id_parcela INT NOT NULL," +
                    "dia_arribada DATE NOT NULL" +
                    ")";
            String crearTablaSortides=  "CREATE TABLE IF NOT EXISTS sortides (" +
                    "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ," +
                    "id_animal INT NOT NULL," +
                    "id_parcela INT NOT NULL," +
                    "dia_sortida DATE NOT NULL" +
                    ")";




            PreparedStatement statementAnimals = this.connection.prepareStatement(crearTablaAnimals);
            PreparedStatement statementParceles = this.connection.prepareStatement(crearTablaParcela);
            PreparedStatement statementAnimalsParceles = this.connection.prepareStatement(crearTablaRealacionAlimentsParceles);
            PreparedStatement statementAliments = this.connection.prepareStatement(crearTablaAliments);
            PreparedStatement statementEspecies = this.connection.prepareStatement(crearTablaEspecie);
            PreparedStatement statementArribades = this.connection.prepareStatement(crearTablaArribades);
            PreparedStatement statementSortides = this.connection.prepareStatement(crearTablaSortides);


            statementAnimals.execute();
            statementParceles.execute();
            statementAnimalsParceles.execute();
            statementAliments.execute();
            statementEspecies.execute();
            statementArribades.execute();
            statementSortides.execute();

            this.connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                this.connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }






    public static Connection createConnection() throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("/home/lordfisto/IdeaProjects/ElHogar/src/sample/database.properties");
        props.load(in);
        in.close();

        String drivers = props.getProperty("jdbc.drivers");
        if (drivers != null) {
            System.setProperty("jdbc.drivers", drivers);
        }
        String url = props.getProperty("jdbc.url");
        String usuari = props.getProperty("jdbc.username");
        String password = props.getProperty("jdbc.password");

        return DriverManager.getConnection(url, usuari, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }
}
