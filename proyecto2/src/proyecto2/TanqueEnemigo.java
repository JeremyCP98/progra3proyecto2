/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author vtrej
 */
public class TanqueEnemigo extends Coordenada implements Runnable, Dibujable{
    
    private ImageIcon ii;
    private Image imagen;
    private Thread hiloTanque;
    
    public Coordenada cor1 = new Coordenada();

    
     public TanqueEnemigo(){
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        
    }
    public TanqueEnemigo(Coordenada a, String ruta){
    
        //super(a.getX(),a.getY());
        ii = new ImageIcon(this.getClass().getResource(ruta));
        this.cor1.setX(a.getX());
        this.cor1.setY(a.getY());
        imagen = ii.getImage();
        hiloTanque = new Thread(this);
        hiloTanque.start();
        
    }   
    public TanqueEnemigo(TanqueEnemigo nav){
        String ruta;
        //super(nav.getX(),nav.getY());
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
}
