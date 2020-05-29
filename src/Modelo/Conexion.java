/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class Conexion {

    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://localhost/parqueadero", "root", "");

            } catch (SQLException e) {
                throw new SQLException(e);

            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

        }
        return cnx;

    }

    public static void cerrar() throws SQLException {

        if (cnx != null) {

            cnx.close();
        }

    }
}
