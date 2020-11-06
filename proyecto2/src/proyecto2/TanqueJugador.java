/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author vtrej
 */

    
public class TanqueJugador extends Coordenada {
    
    private ImageIcon ii;
    private Image imagen;
    
    public Coordenada cor1 = new Coordenada();

    
    public TanqueJugador(){
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        
    }
    public TanqueJugador(Coordenada a, String ruta){
    
        //super(a.getX(),a.getY());
        ii = new ImageIcon(this.getClass().getResource(ruta));
        this.cor1.setX(a.getX());
        this.cor1.setY(a.getY());
        imagen = ii.getImage();
        
    }   
    public TanqueJugador(TanqueJugador nav){
        String ruta;
        //super(nav.getX(),nav.getY());
        this.cor1.setX(nav.getX());
        this.cor1.setY(nav.getY());
        ruta = nav.imagen.getSource().toString();
    }
    public Image getImagen() {
        return imagen;
    }
    
}

