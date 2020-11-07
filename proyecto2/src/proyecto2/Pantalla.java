/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author vtrej
 */
public class Pantalla extends JPanel implements Runnable, KeyListener {
    ArrayList array = new ArrayList();
    private TanqueEnemigo tanq1 = null;
    private TanqueEnemigo tanq2 = null;
    private TanqueEnemigo tanq3 = null;
    private TanqueEnemigo tanq4 = null;
    private TanqueEnemigo tanq5 = null;
    private TanqueJugador tanqJug  = null;
    
    String ruta;
    String ruta2;
    
    private Thread hiloControl;
    Coordenada a = new Coordenada(0,0);
    Coordenada b = new Coordenada(0,0);
    
    Coordenada movIzq = new Coordenada(-25,0);
    Coordenada movDer = new Coordenada(25,0);
    Coordenada movArriba = new Coordenada(0,-25);
    Coordenada movAbajo = new Coordenada(0,25);
    Coordenada movNulo = new Coordenada(0,0);
    
    public Pantalla(){
    ArrayList objetos = new ArrayList();
    
    ruta = "tanqueEnemigo.png" ;
    ruta2 = "tanqueJugador.png";
    tanq1 = new TanqueEnemigo(a,ruta);
    tanq2 = new TanqueEnemigo(a,ruta);
    tanq3 = new TanqueEnemigo(a,ruta);
    tanq4 = new TanqueEnemigo(a,ruta);
    tanq5 = new TanqueEnemigo(a,ruta);
    tanqJug = new TanqueJugador(b,ruta2);
    hiloControl = new Thread(this);//Se crea el hilo
    hiloControl.start(); //se inicia
    objetos.add(tanq1);
    objetos.add(tanq2);
    objetos.add(tanq3);
    objetos.add(tanq4);
    objetos.add(tanq5);
    objetos.add(tanqJug);
    
    this.array = objetos;
    this.addKeyListener(this);
    setFocusable(true);
    
    tanq1.setY(0);
    tanq1.setX(550);
    tanq2.setY(0);
    tanq2.setX(200);
    tanq3.setY(0);
    tanq3.setX(900);
    tanq4.setY(150);
    tanq4.setX(350);
    tanq5.setY(150);
    tanq5.setX(750);
    
    tanqJug.setY(859);
    tanqJug.setX(550);
    
    }
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g); //Permite la funcionalidad básica para dibujar el panel
        int anchoPanel = this.getWidth() / 10;
        int altoPanel = this.getHeight() / 10;
        
//        int yNave1 = 0;
//        int yNave2 = yNave1 + altoPanel;
//        int yNave3 = yNave2 + altoPanel; 
       
        
        /*nave2.setY(yNave2); 590.0 - 659.0
        nave3.setY(yNave3);
        
        g.drawImage(tanq.getImagen(), tanq.getX(), tanq.getY(),anchoPanel, altoPanel,this);
        g.drawImage(nave2.getImagen(), nave2.getX(), nave2.getY(),anchoPanel, altoPanel,this);
        g.drawImage(nave3.getImagen(), nave3.getX(), nave3.getY(),anchoPanel, altoPanel,this);*/
        
        
        
         g.drawImage(tanqJug.getImagen(), (int)tanqJug.getX(), (int)tanqJug.getY(),anchoPanel, altoPanel,this);
         g.drawImage(tanq1.getImagen(), (int)tanq1.getX(), (int)tanq1.getY(),anchoPanel, altoPanel,this);
         g.drawImage(tanq2.getImagen(), (int)tanq2.getX(), (int)tanq2.getY(),anchoPanel, altoPanel,this);
         g.drawImage(tanq3.getImagen(), (int)tanq3.getX(), (int)tanq3.getY(),anchoPanel, altoPanel,this);
         g.drawImage(tanq4.getImagen(), (int)tanq4.getX(), (int)tanq4.getY(),anchoPanel, altoPanel,this);
         g.drawImage(tanq5.getImagen(), (int)tanq5.getX(), (int)tanq5.getY(),anchoPanel, altoPanel,this);
    } 
    
    
    @Override
    public void run() {
        while(true){
            try{
                if(!tanqJug.balas.isEmpty()){
                    tanqJug.cicloBala();
                }
                Thread.sleep(80);
            }catch(InterruptedException err){
                System.out.println(err);
            }
            
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            if(tanqJug.getX() > 0)
            {
                this.tanqJug.mover(movIzq);
            }
        }
        if(tecla == KeyEvent.VK_RIGHT){
            if(tanqJug.getX() < 1100)
            {
                this.tanqJug.mover(movDer);
            }
        }
        if(tecla == KeyEvent.VK_UP){
            if(tanqJug.getY() > 10)
            {
                this.tanqJug.mover(movArriba);
            }
        }
        if(tecla == KeyEvent.VK_DOWN){
            if(tanqJug.getY() < 850)
            {
                this.tanqJug.mover(movAbajo);
            }
        }
        if(tecla == KeyEvent.VK_Q){
            BalaGrafica bala = tanqJug.Bala();
            tanqJug.balas.add(bala);
            array.add(bala);
        }
        //repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_RIGHT){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_UP){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_DOWN){
            this.tanqJug.mover(movNulo);
        }
    }
    
}
