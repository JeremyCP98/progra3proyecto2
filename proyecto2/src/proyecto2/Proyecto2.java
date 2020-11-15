/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Jeremy
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Instrucciones i = new Instrucciones();
        VistaLogin vl = new VistaLogin();
        VistaMenu vm = new VistaMenu();
        Pantalla pantalla = new Pantalla(i,vl,vm);
        vm.iniciar();
        
    }
    
}
