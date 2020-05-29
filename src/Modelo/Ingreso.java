/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Ingreso {

    private String placa;
    private int modelo;
    private Date fecha;
    private Tipo tipo;
    private int valor;

    public Ingreso() {

    }

    public Ingreso(String placa, int modelo, Date fecha, Tipo tipo) {
        this.placa = placa;
        this.modelo = modelo;
        this.fecha = fecha;
        this.tipo = tipo;

    }

    public int obtenerTipo() {

        if (this.tipo == Tipo.CARRO) {
            return 1;
        }
        if (this.tipo == Tipo.MOTO) {
            return 2;
        }

        return -1;
    }

    public Boolean validarPlaca() {

        if (this.tipo == Tipo.CARRO) {
            if (placa.length() == 6) {

                return true;
            } else {
                return false;
            }
        }

        if (this.tipo == Tipo.MOTO) {
            if (placa.length() == 5) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void precio() {

        if (this.tipo == Tipo.CARRO) {
            if (this.modelo < 2012) {
                this.valor = 2000;
            } else {
                if (modelo == 2020) {
                    this.valor = (int) (2500 * 1.1);
                } else {
                    valor = 2500;
                }
            }
        }

        if (this.tipo == Tipo.MOTO) {

            if (this.modelo < 2012) {
                this.valor = 1000;
            } else {
                if (modelo == 2020) {
                    this.valor = (int) (1200 * 1.1);
                } else {
                    valor = 1200;
                }

            }

        }

    }

    public void eliminarRegistros() throws ClassNotFoundException {

        try {
            Connection cnx = Conexion.obtener();
            PreparedStatement consulta;

            consulta = cnx.prepareStatement("DELETE FROM registro");
            consulta.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
    public Boolean existePlaca(String placa) throws ClassNotFoundException {
        try {
            Connection conexion = Conexion.obtener();
            PreparedStatement consulta;
            consulta = conexion.prepareStatement("SELECT * FROM registro WHERE placa = ?");
            consulta.setString(1, placa);

            ResultSet rs = consulta.executeQuery();

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
        }

        return false;
    }
    
    public String cantidadMotosCarros() throws ClassNotFoundException{
    
    try{
    Connection cnx=Conexion.obtener();
    PreparedStatement consulta;
    consulta = cnx.prepareStatement("select "+
            "count(placa) as cantidada, tipo "+
            "from registro group by tipo");
    ResultSet rs=consulta.executeQuery();
    String totalc="";
    String totalm="";
    if(rs.next()){
    
    totalc= totalc+rs.getInt("cantidada")+" ";
    
    }
    if(rs.next()){
    
    totalm= totalm+rs.getInt("cantidada")+" ";
    
    }
        if(totalm.equals("")){totalm="0";}
        if(totalc.equals("")){totalc="0";}
    return totalc+","+totalm;
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return "";
    
    }
    
    
    
    public String totalizar() throws ClassNotFoundException{
    
    try{
    Connection cnx=Conexion.obtener();
    PreparedStatement consulta;
    consulta = cnx.prepareStatement("select "+
            "sum(valor) as total, count(placa) as cantidad, tipo "+
            "from registro group by tipo");
    ResultSet rs=consulta.executeQuery();
    String total="";
    int totalvehiculos=0;
    int totalDinero=0;
    if(rs.next()){
    
    total= total+"Total de carros "+rs.getInt("cantidad")+" con un valor de "+rs.getInt("total")+"\n";
    totalvehiculos+=rs.getInt("cantidad");
    totalDinero+=rs.getInt("total");
    }
    
    if(rs.next()){
    
    total= total+"Total de motos "+rs.getInt("cantidad")+" con un valor de "+rs.getInt("total")+"";
    totalvehiculos+=rs.getInt("cantidad");
    totalDinero+=rs.getInt("total");
    }
    
    total+="\n El total de vehiculos fueron "+totalvehiculos+" y el total recaudado fue "+totalDinero;
    return total;
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return "";
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getPlaca() {
        return placa;
    }

    public int getModelo() {
        return modelo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

}
