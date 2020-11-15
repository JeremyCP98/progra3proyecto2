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
public class Jugador {
    private String nombre;
    private int puntaje;
    
     public Jugador(){
        this.nombre = "";
        this.puntaje = 0;
    }

    
    public Jugador(String nombre, int puntaje){
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return "Jugador{" + "nombre=" + nombre + ", puntaje=" + puntaje + '}';
    }
    
}
