/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author vtrej
 */
public class Pantalla extends JPanel implements Runnable {
    private TanqueEnemigo tanq = null;
    private TanqueJugador tanqJug  = null;
    
    String ruta;
    String ruta2;
    
    private Thread hiloControl;
    Coordenada a = new Coordenada(0,0);
    Coordenada b = new Coordenada(0,0);
    
    public Pantalla(){
    ruta = "tanqueEnemigo.png" ;
    ruta2 = "tanqueJugador.png";
    tanq = new TanqueEnemigo(a,ruta);
    tanqJug = new TanqueJugador(b,ruta2);
    hiloControl = new Thread(this);//Se crea el hilo
    hiloControl.start(); //se inicia
    
    
    
    
    }
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g); //Permite la funcionalidad básica para dibujar el panel
        int anchoPanel = this.getWidth() / 10;
        int altoPanel = this.getHeight() / 10;
        
        int yNave1 = 0;
        int yNave2 = yNave1 + altoPanel;
        int yNave3 = yNave2 + altoPanel; 
       
        tanq.setY(yNave1);
        /*nave2.setY(yNave2);
        nave3.setY(yNave3);
        
        g.drawImage(tanq.getImagen(), tanq.getX(), tanq.getY(),anchoPanel, altoPanel,this);
        g.drawImage(nave2.getImagen(), nave2.getX(), nave2.getY(),anchoPanel, altoPanel,this);
        g.drawImage(nave3.getImagen(), nave3.getX(), nave3.getY(),anchoPanel, altoPanel,this);*/
        
        tanqJug.setY(859);
        tanqJug.setX(550);
        
         g.drawImage(tanqJug.getImagen(), (int)tanqJug.getX(), (int)tanqJug.getY(),anchoPanel, altoPanel,this);
    } 
    
    
    @Override
    public void run() {
        
    }
    
}
