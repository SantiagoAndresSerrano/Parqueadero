/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parqueadero;

import Control.Controlador;
import Modelo.Ingreso;

import Vista.Formulario;

/**
 *
 * @author santi
 */
public class Parqueadero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ingreso i = new Ingreso();
        Formulario f = new Formulario();
        f.setVisible(true);
        Controlador c = new Controlador(i, f);
    }

}
