/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.BorderLayout;
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
                //Creación de la ventana e implementación del panel par
        JFrame ventana = new JFrame("Guerra de Tanques");
        ventana.setSize(1224, 1000);
        ventana.setLocationRelativeTo(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.getContentPane().add(new Pantalla(), BorderLayout.CENTER);
        ventana.setVisible(true);
    }
    
}
