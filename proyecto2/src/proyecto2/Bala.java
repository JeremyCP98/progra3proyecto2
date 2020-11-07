/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author XperriX
 */
public class Bala extends Coordenada {
    private float radio;
    
    public Bala(){
        super();
        this.radio = 0;
    }
    public Bala(Coordenada cor, float r){
        super(cor);
        this.radio = r;
    }
    public Bala(Bala bala){
        super(bala);
        this.radio = bala.radio;
    }

    public float getRadio() {
        return radio;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }
    
    public Coordenada getCentro(){
        Coordenada cor = new Coordenada(this.getX(),this.getY());
        return cor;
    }
}
