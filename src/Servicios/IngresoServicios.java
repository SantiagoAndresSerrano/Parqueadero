/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;

import Modelo.Ingreso;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author santi
 */
public class IngresoServicios {
  
    public void guardar(Connection conexion, Ingreso i)throws SQLException{
    
    try{
    PreparedStatement consulta;
    consulta=conexion.prepareStatement("INSERT INTO registro(placa,fecha,modelo,tipo,valor)"+"values(?,?,?,?,?)");
    consulta.setString(1, i.getPlaca());
    consulta.setDate(2, (Date) i.getFecha());
    consulta.setInt(3, i.getModelo());
    consulta.setInt(4, i.obtenerTipo());
    consulta.setInt(5,i.getValor());
    
    consulta.executeUpdate();
    }catch(SQLException e)
    {
        throw new SQLException(e);
    }
    }

}
