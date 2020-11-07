/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author XperriX
 */
public class BalaGrafica extends Bala {
    Color color;
    
    public BalaGrafica(Coordenada cor, float radio, Color col){
        super(cor,radio);
        this.color = col;
    }
    
    public void dibujar(Graphics dv) {
        dv.setColor(color);
        dv.fillOval((int)(this.getX()-this.getRadio()),(int)(this.getY()-this.getRadio()), (int)(2*this.getRadio()), (int)(2*this.getRadio()));
    }
    
    public void pintar(Color col) {
        this.color = col;
    }
}
