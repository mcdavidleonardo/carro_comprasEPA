package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    //Declaramos variables para manejar los datos de conexión de BDD
    private static String url = "jdbc:mysql://localhost:3306/carro_compras?useTimezone=true&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "";

    //Método para conectar a la BDD
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password);
    }
}
