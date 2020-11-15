/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author XperriX
 */
public class Conexion {
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jugadores", "root", "admin");
            return con;
        } catch (Exception e) {
            System.out.println(e.fillInStackTrace());
            return null;
        }
    }
}
