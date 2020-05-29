/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Modelo.Ingreso;
import Modelo.Tipo;
import Modelo.Conexion;
import Servicios.IngresoServicios;
import Vista.Formulario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author santi
 */
public class Controlador implements ActionListener {

    Ingreso i;
    Tipo t;
    Formulario f;
    private SimpleDateFormat s = new SimpleDateFormat("YYYY/DD/MM");

    public Controlador(Ingreso i, Formulario f) {
        super();
        this.i = i;

        this.f = f;
        actionListener(this);

    }

    public final void actionListener(ActionListener e) {

        f.btnAgregar.addActionListener(e);
        f.btnLimpiar.addActionListener(e);
        f.btnTotalizar.addActionListener(e);
        f.btnReiniciar.addActionListener(e);
        f.jComboBox1.addActionListener(e);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == f.btnAgregar) {//placa, int modelo, Date fecha, Tipo tipo

            try {
                String placa = f.jtPlaca.getText();
                int modelo = Integer.parseInt(f.jtModelo.getText());
                Date fecha = s.parse(f.jtFecha.getText()); 
                String fechas = f.jtFecha.getText();
                String[] fs = fechas.split("/");
                int anio = Integer.parseInt(fs[0]);
                int mes = Integer.parseInt(fs[1]);
                int dia = Integer.parseInt(fs[2]);

                java.sql.Date fechasql = new java.sql.Date(anio - 1900, mes - 1, dia);
                Tipo tipo = null;
                if (f.jComboBox1.getSelectedItem().equals("Carro")) {
                    tipo = Tipo.CARRO;
                } else if (f.jComboBox1.getSelectedItem().equals("Moto")) {
                    tipo = Tipo.MOTO;
                }

                i = new Ingreso(placa, modelo, fechasql, tipo);
                i.precio();
                if (i.validarPlaca() && !i.existePlaca(placa)) {

                    try {
                        IngresoServicios us = new IngresoServicios();
                        JOptionPane.showMessageDialog(null, tipo + " agregado correctamente");
                        us.guardar(Conexion.obtener(), i);

                    } catch (ClassNotFoundException | SQLException e) {
                        System.out.println(e.getMessage());
                    }

                    try {
                        String[] cantidades = i.cantidadMotosCarros().split(",");
                        f.jtCantidadCarros.setText(cantidades[0]);
                        f.jtCantidadMotos.setText(cantidades[1]);
                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error placa repetida ó número de dígitos incorrectos");

                }

            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (ae.getSource() == f.btnReiniciar) {
            try {
                String[] cantidades = i.cantidadMotosCarros().split(",");

                JOptionPane.showMessageDialog(null, cantidades[0] + " Carros eliminados, " + cantidades[1] + " Motos eliminadas");
                i.eliminarRegistros();
                cantidades = i.cantidadMotosCarros().split(",");
                f.jtCantidadCarros.setText(cantidades[0]);
                f.jtCantidadMotos.setText(cantidades[1]);
                f.jTextArea2.setText("");

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (ae.getSource() == f.btnTotalizar) {
            
            try {
                String[] cantidades = i.cantidadMotosCarros().split(",");
                f.jtCantidadCarros.setText(cantidades[0]);
                f.jtCantidadMotos.setText(cantidades[1]);
                f.jTextArea2.setText(i.totalizar());
            } catch (ClassNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }

    }

}
