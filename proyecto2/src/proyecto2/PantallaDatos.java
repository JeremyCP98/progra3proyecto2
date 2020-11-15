/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author XperriX
 */
public class PantallaDatos implements Dibujable {

    String titulo;
    Color color;
    int anchoY;
    int altoY;
    int size;
    
    public PantallaDatos(){
        this.titulo = "";
        this.color = Color.white;
        this.size = 10;
        this.anchoY = 0;
        this.altoY = 0;
    }
    public PantallaDatos(String titulo, Color col, int a, int h){
        this.titulo = titulo;
        this.color = col;
        this.size = 10;
        this.anchoY = a;
        this.altoY = h;
    }
    public void borrarTexto(Graphics g, String txt){
        g.setColor(Color.white);
        g.setFont(new Font("Algerian", Font.PLAIN, this.getSize()));
        int ancho = (int)g.getFontMetrics().getStringBounds(txt, g).getWidth();
        int alto = (int)g.getFontMetrics().getAscent();
        int x = this.anchoY/2 - ancho/2;
        int y = this.altoY/2 + alto/4;
        g.drawString(txt, x, y);
    }
    
    public void pintarTexto(Graphics g, String txt){
        g.setColor(Color.red);
        g.setFont(new Font("Algerian", Font.PLAIN, this.getSize()));
        int ancho = (int)g.getFontMetrics().getStringBounds(txt, g).getWidth();
        int alto = (int)g.getFontMetrics().getAscent();
        int x = this.anchoY/2 - ancho/2;
        int y = this.altoY/2 + alto/4;
        g.drawString(txt, x, y);
    }
    
    @Override
    public void dibujar(Graphics g) {
        g.setColor(color);
        g.setFont(new Font("Monospaced", Font.PLAIN, this.getSize()));
        int ancho = (int)g.getFontMetrics().getStringBounds(titulo, g).getWidth();
        int alto = (int)g.getFontMetrics().getAscent();
        int x = this.anchoY/2 - ancho/2;
        int y = this.altoY/2 + alto/4;
        g.drawString(titulo, x, y);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void setColor(Color col) {
        this.color = col;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
}
