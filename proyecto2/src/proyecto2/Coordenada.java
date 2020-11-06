/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

/**
 *
 * @author vtrej
 */
public class Coordenada {
    private float x;
    private float y;
    
    public Coordenada(){
        this.x = 0;
        this.y = 0;
    }
    public Coordenada(float x, float y){
        this.x = x;
        this.y = y;        
    }
    public Coordenada(Coordenada nva){
        this.x = nva.x;
        this.y = nva.y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}