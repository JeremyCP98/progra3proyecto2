/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author vtrej
 */
public class TanqueJugador extends Coordenada implements Dibujable {

    private ImageIcon ii;
    private Image imagen;
    String ruta;
    public Coordenada cor1 = new Coordenada();
    String nombre;
    ArrayList balas = new ArrayList();

    public TanqueJugador() {
        super();
        this.cor1.setX(0);
        this.cor1.setY(0);
        nombre = "";
    }

    public TanqueJugador(Coordenada a, String ruta) {
        this.ruta = ruta;
        ii = new ImageIcon(this.getClass().getResource(this.ruta));
        this.cor1.setX(a.getX());
        this.cor1.setY(a.getY());
        imagen = ii.getImage();
    }

    public TanqueJugador(TanqueJugador nav) {
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

    public BalaGrafica Bala() {
        Coordenada salida = new Coordenada(this.getX() + 63, this.getY());
        BalaGrafica bala = new BalaGrafica(salida, 10, Color.red);

        return bala;
    }

    public void cicloBala() {
        for (int i = 0; i < this.balas.size(); i++) {
            BalaGrafica y = (BalaGrafica) this.balas.get(i);
            float x = y.getY();
            y.setY(x -= 20);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void mover(Coordenada nva) {
        setX(this.Suma(nva).getX());
        setY(this.Suma(nva).getY());
    }

    @Override
    public void dibujar(Graphics dv) {
        dv.drawImage(getImagen(), (int) getX(), (int) getY(), null);
    }
}
