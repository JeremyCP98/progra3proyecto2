/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author vtrej
 */
public class TanqueEnemigo extends Coordenada implements Runnable, Dibujable{
    
    private ImageIcon ii;
    private Image imagen;
    private Thread hiloTanque;
    
    public Coordenada cor1 = new Coordenada();
    ArrayList balas = new ArrayList();


    
     public TanqueEnemigo(){
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        
    }
    public TanqueEnemigo(Coordenada a, String ruta){
    
        ii = new ImageIcon(this.getClass().getResource(ruta));
        this.cor1.setX(a.getX());
        this.cor1.setY(a.getY());
        imagen = ii.getImage();
        hiloTanque = new Thread(this);
        hiloTanque.start();
        
    }   
    public TanqueEnemigo(TanqueEnemigo nav){
        String ruta;
        this.cor1.setX(nav.getX());
        this.cor1.setY(nav.getY());
        ruta = nav.imagen.getSource().toString();
    }
    public Image getImagen() {
        return imagen;
    }
    
    @Override
    public void run() {
        while(hiloTanque == Thread.currentThread() && this.cor1.getX() < 800) {
           try {
               this.cor1.setX(cor1.getX()+50);
               Thread.sleep(700); //El sleep es para ver el cambio y poder visualizarlo
           } catch(InterruptedException ie) {
           }
       }
    }

    @Override
    public void dibujar(Graphics dv) {
        dv.drawImage(getImagen(), (int)getX(), (int)getY(), null);
    }
    
    public void ciclo(){
        float x = this.getX();
        if(x < 1100)this.setX(x += 5);
        else this.setX(0);
    }
    
    public BalaGrafica Bala(){ 
        Coordenada salida = new Coordenada(this.getX() + 63,this.getY()+100);
        BalaGrafica bala = new BalaGrafica(salida, 10, Color.black);
        
        return bala;
    }
    
    public void cicloBala(){
        for(int i = 0; i < this.balas.size(); i++){
            BalaGrafica y = (BalaGrafica)this.balas.get(i);
            float x = y.getY();
            y.setY(x += 9);
       }       
    }
    
    public void borrarBalas(){
        
        balas.removeAll(balas);
    }
}
