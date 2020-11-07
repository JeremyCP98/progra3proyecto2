/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author vtrej
 */

    
public class TanqueJugador extends Coordenada {
    
    private ImageIcon ii;
    private Image imagen;
    String ruta;
    public Coordenada cor1 = new Coordenada();
    
    ArrayList balas = new ArrayList();
    
    public TanqueJugador(){
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        
    }
    public TanqueJugador(Coordenada a, String ruta){
        this.ruta = ruta;
        //super(a.getX(),a.getY());
        ii = new ImageIcon(this.getClass().getResource(this.ruta));
        this.cor1.setX(a.getX());
        this.cor1.setY(a.getY());
        imagen = ii.getImage();
        
    }   
    public TanqueJugador(TanqueJugador nav){
        
        //super(nav.getX(),nav.getY());
        this.cor1.setX(nav.getX());
        this.cor1.setY(nav.getY());
        ruta = nav.imagen.getSource().toString();
    }
    public Image getImagen() {
        return imagen;
    }

    public void setCor1(Coordenada cor1) {
        this.cor1 = cor1;
    }

    public String getRuta() {
        return ruta;
    }
    
    public BalaGrafica Bala(){ // 590.0 - 659.0
        Coordenada salida = new Coordenada(this.getX() + 40,this.getY() - 200);
        System.out.println(salida.getX() + " - " + salida.getY());
        BalaGrafica bala = new BalaGrafica(salida, 10, Color.red);
        
        return bala;
    }
    
    public void cicloBala(){
        for(int i = 0; i < this.balas.size(); i++){
            BalaGrafica y = (BalaGrafica)this.balas.get(i);
            float x = y.getY();
            y.setY(x -= 20);
        }
    }
    
    public void mover(Coordenada nva){
        setX(this.Suma(nva).getX());
        setY(this.Suma(nva).getY());
    }
}

