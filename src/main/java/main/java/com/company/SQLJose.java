package main.java.com.company;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Calendar;
import java.util.Properties;

public class SQLJose {

    private String sql;
    private Calendar calendario;
    private Date startdate;
    private Connection conexion;

    /**
     *
     * @param sql Se debe de escribir el nombre de la base de datos, recibe "String"
     */
    public SQLJose(String sql) {
        /**
         * Se introduce la base de datos
         */
        this.sql = sql;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            FileReader reader = new FileReader("src/main/java/main/java/com/company/archivo.properties");
            properties.load(reader);
            String url = properties.getProperty("url");
            String usr = properties.getProperty("usr");
            String psswd = properties.getProperty("psswd");
            this.conexion = DriverManager.getConnection(url, usr, psswd);

        } catch (SQLException | ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * @param name Nombre de persona
     * @param hobbie A que hobbie se dedica
     */
    public void insertarSQL(String name, int hobbie) {
        //queryInsert = "INSERT INTO pruebaSQLRemota (date,name,hobbie) VALUES (?,?,?)";

        try {

            this.calendario = Calendar.getInstance();
            this.startdate = new Date(calendario.getTime().getTime());
            String queryInsert = "INSERT INTO "+ this.sql +" (date, name, hobbie) VALUES (?,?,?) ";

            PreparedStatement stmInsert = conexion.prepareStatement(queryInsert);
            stmInsert.setDate(1, startdate);
            stmInsert.setString(2, name);
            stmInsert.setInt(3, hobbie);
            stmInsert.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void recibirSQL(){

        try {

            String query = "SELECT * FROM "+this.sql;
            PreparedStatement stmSelect = conexion.prepareStatement(query);
            ResultSet resultSet = stmSelect.executeQuery();

            while (resultSet.next()){
                System.out.println(resultSet.getObject(1) + " " + resultSet.getDate(2) + " "
                        + resultSet.getString(3) + " " + resultSet.getInt(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrar(){
        try {
            this.conexion.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void buscar(){
        
    }
}
