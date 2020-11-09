/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import proyecto2.Instrucciones;
import proyecto2.VistaLogin;
import proyecto2.VistaMenu;

/**
 *
 * @author vtrej
 */
public class Pantalla extends JPanel implements Runnable, KeyListener, ActionListener {
    ArrayList array = new ArrayList();
    private TanqueEnemigo tanq1 = null;
    private TanqueEnemigo tanq2 = null;
    private TanqueEnemigo tanq3 = null;
    private TanqueEnemigo tanq4 = null;
    private TanqueEnemigo tanq5 = null;
    private TanqueEnemigo tanq6 = null;
    private TanqueEnemigo explosion = null;
    private TanqueJugador tanqJug  = null;
    
    private Instrucciones inst;
    private VistaLogin vLogin;
    private VistaMenu vMenu;
    
    ArrayList enemigos = new ArrayList();
    String ruta;
    String ruta2;
    String ruta3;
    
    private Thread hiloControl;
    Coordenada a = new Coordenada(0,0);
    Coordenada b = new Coordenada(0,0);
    
    Coordenada movIzq = new Coordenada(-15,0);
    Coordenada movDer = new Coordenada(15,0);
    Coordenada movArriba = new Coordenada(0,-15);
    Coordenada movAbajo = new Coordenada(0,15);
    Coordenada movNulo = new Coordenada(0,0);
    
    int contTanq = 6;
    
    int score;
    int cantVidas = 5;
    PantallaDatos puntos = new PantallaDatos();
    PantallaDatos vidas = new PantallaDatos();
    Boolean termina = true;
    
    public Pantalla(Instrucciones i, VistaLogin vl, VistaMenu vm){
    inst = i;
    vMenu = vm;
    vLogin = vl;
    ruta = "tanqueEnemigo.png" ;
    ruta2 = "tanqueJugador.png";
    ruta3  = "explosion.gif";
    tanq1 = new TanqueEnemigo(a,ruta);
    tanq2 = new TanqueEnemigo(a,ruta);
    tanq3 = new TanqueEnemigo(a,ruta);
    tanq4 = new TanqueEnemigo(a,ruta);
    tanq5 = new TanqueEnemigo(a,ruta);
    tanq6 = new TanqueEnemigo(a,ruta);
    explosion = new TanqueEnemigo(a,ruta3);
    tanqJug = new TanqueJugador(b,ruta2);
    hiloControl = new Thread(this);//Se crea el hilo
    hiloControl.start(); //se inicia
    array.add(tanq1);
    array.add(tanq2);
    array.add(tanq3);
    array.add(tanq4);
    array.add(tanq5);
    array.add(tanq6);
    //array.add(explosion);
    array.add(tanqJug);
    
    enemigos.add(tanq1);
    enemigos.add(tanq2);
    enemigos.add(tanq3);
    enemigos.add(tanq4);
    enemigos.add(tanq5);
    enemigos.add(tanq6);
    this.addKeyListener(this);
    setFocusable(true);
    
    tanq1.setY(0);
    tanq1.setX(550);
    tanq2.setY(0);
    tanq2.setX(200);
    tanq3.setY(0);
    tanq3.setX(900);
    tanq4.setY(150);
    tanq4.setX(350);
    tanq5.setY(150);
    tanq5.setX(750);
    tanq6.setY(150);
    tanq6.setX(25);
    tanqJug.setY(800);
    tanqJug.setX(550);
    
    PantallaDatos vidasTxt = new PantallaDatos("Vidas",Color.black,2700,50);
    vidasTxt.setSize(35);
    array.add(vidasTxt);
    PantallaDatos score = new PantallaDatos("Puntos",Color.black,2700,250);
    score.setSize(35);
    array.add(score);

    puntos = new PantallaDatos("0",Color.red,2700,350);
    puntos.setSize(40);
    array.add(puntos);

    vidas = new PantallaDatos(""+cantVidas,Color.red,2700,150);
    vidas.setSize(40);
    array.add(vidas);
    
    Jugador jg = new Jugador("", 0);
    
    PantallaDatos nombreJug = new PantallaDatos(jg.getNombre(),Color.black,2700,450);
    nombreJug.setSize(50);
    array.add(nombreJug);
    
    this.inst.getVolverBtn().addActionListener(this);
    this.inst.getIniciaPartidaBtn().addActionListener(this);
    this.vLogin.getInicioPartida().addActionListener(this);
    this.vMenu.getInstrucciones().addActionListener(this);
    this.vMenu.getNuevaPartida().addActionListener(this);
    
    }
    
    public void paint(Graphics g){
        
        super.paintComponent(g); //Permite la funcionalidad básica para dibujar el panel
//        int anchoPanel = this.getWidth() / 10;
//        int altoPanel = this.getHeight() / 10;
        
        Dimension d = getSize();
        Image Imagen = createImage(d.width,d.height);
        Graphics buff = Imagen.getGraphics();
        Dibujable div;
        for(int i = 0; i < array.size(); i++)
        {
            div =  (Dibujable) array.get(i);
            div.dibujar(buff);
        }
        g.drawImage(Imagen, 0, 0, null);
    } 
    public void update(Graphics g){
        paint(g);
    }
    
    void colision(){
        for(int i = 0; i < tanqJug.balas.size(); i++){
            BalaGrafica balaJug = (BalaGrafica) tanqJug.balas.get(i);
            for(int j = 0; j < enemigos.size(); j++){
                TanqueEnemigo tEnemigo = (TanqueEnemigo) enemigos.get(j);
                
                Coordenada corBalaJug = new Coordenada(balaJug.getX(),balaJug.getY());
                Coordenada derecha = new Coordenada(tEnemigo.getX()+100,tEnemigo.getY()+75);
                Coordenada izquierda = new Coordenada(tEnemigo.getX(),tEnemigo.getY()+75);
                Coordenada medio = new Coordenada(tEnemigo.getX()+50,tEnemigo.getY()+75);
                
                if(corBalaJug.getX() > izquierda.getX() && corBalaJug.getX() < derecha.getX() && corBalaJug.getY() < medio.getY() && corBalaJug.getY()+25 > medio.getY()){
                    
                    array.add(explosion);
                    explosion.setY(tEnemigo.getY()-50);
                    explosion.setX(tEnemigo.getX());
                    tEnemigo.setY(1500);
                    balaJug.setY(1500);
                    tEnemigo.borrarBalas();
                    tanqJug.balas.remove(balaJug);
                    enemigos.remove(tEnemigo);
                    contTanq--;
                    score+=2;
                    puntos.setColor(Color.white);
                    String nuevoScore = ""+score;
                    PantallaDatos nPuntos = new PantallaDatos(nuevoScore, Color.red, 2700, 350);
                    nPuntos.setSize(40);
                    puntos = nPuntos;
                    array.add(puntos);
                }
            }
        }
        for(int i = 0; i < tanq1.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq1.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+125,tJug.getY()+25);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+25);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+25);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY() > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
        for(int i = 0; i < tanq2.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq2.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+100,tJug.getY()+50);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+50);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+50);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY()+25 > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
        for(int i = 0; i < tanq3.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq3.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+100,tJug.getY()+50);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+50);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+50);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY()+25 > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
        for(int i = 0; i < tanq4.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq4.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+100,tJug.getY()+50);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+50);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+50);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY()+25 > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
        for(int i = 0; i < tanq5.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq5.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+100,tJug.getY()+50);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+50);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+50);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY()+25 > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
        for(int i = 0; i < tanq6.balas.size(); i++){
            BalaGrafica balaEnemigo = (BalaGrafica) tanq6.balas.get(i);
            
            TanqueJugador tJug = (TanqueJugador) tanqJug;

            Coordenada corBalaEnemiga = new Coordenada(balaEnemigo.getX(),balaEnemigo.getY());
            Coordenada derecha = new Coordenada(tJug.getX()+100,tJug.getY()+50);
            Coordenada izquierda = new Coordenada(tJug.getX(),tJug.getY()+50);
            Coordenada medio = new Coordenada(tJug.getX()+50,tJug.getY()+50);

            if(corBalaEnemiga.getX() > izquierda.getX() && corBalaEnemiga.getX() < derecha.getX() && corBalaEnemiga.getY() < medio.getY() && corBalaEnemiga.getY()+25 > medio.getY()){

                array.add(explosion);
                explosion.setY(tJug.getY()-50);
                explosion.setX(tJug.getX()-50);
                balaEnemigo.setY(1500);
                tanqJug.balas.remove(balaEnemigo);
                cantVidas--;
                String nuevaVida = ""+cantVidas;
                vidas.setColor(Color.white);
                PantallaDatos nVidas = new PantallaDatos(nuevaVida, Color.red, 2700, 150);
                nVidas.setSize(40);
                vidas = nVidas;
                array.add(vidas);
            }
        }
    }
    
    @Override
    public void run() {
        while(termina){
            
            try{

                if(!tanqJug.balas.isEmpty()){
                    tanqJug.cicloBala();
                }

                for(int i = 0; i < enemigos.size(); i++){
                    TanqueEnemigo te = (TanqueEnemigo)enemigos.get(i);
                    te.ciclo();
                    Random rn = new Random();
                    int answer = rn.nextInt(30) + 1;
                    if(answer == 3){
                        BalaGrafica bala = te.Bala();
                        te.balas.add(bala);
                        array.add(bala);
                    }
                    if(!te.balas.isEmpty()){
                    te.cicloBala();
                    }
                }
                if(cantVidas == 0){
                    termina = false;
                    JOptionPane.showMessageDialog(null, "HAS PERDIDO");
                }
                if(contTanq == 0){
                    termina = false;
                    JOptionPane.showMessageDialog(null, "HAS GANADO");
                }
                colision();
                
                Thread.sleep(80);
            }catch(InterruptedException err){
                System.out.println(err);
            }
            
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            if(tanqJug.getX() > 0)
            {
                this.tanqJug.mover(movIzq);
            }
        }
        if(tecla == KeyEvent.VK_RIGHT){
            if(tanqJug.getX() < 1100)
            {
                this.tanqJug.mover(movDer);
            }
        }
        if(tecla == KeyEvent.VK_UP){
            if(tanqJug.getY() > 10)
            {
                this.tanqJug.mover(movArriba);
            }
        }
        if(tecla == KeyEvent.VK_DOWN){
            if(tanqJug.getY() < 800)
            {
                this.tanqJug.mover(movAbajo);
            }
        }
        if(tecla == KeyEvent.VK_Q){
            BalaGrafica bala = tanqJug.Bala();
            tanqJug.balas.add(bala);
            array.add(bala);
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        int tecla = ke.getKeyCode();
        
        if(tecla == KeyEvent.VK_LEFT){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_RIGHT){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_UP){
            this.tanqJug.mover(movNulo);
        }
        if(tecla == KeyEvent.VK_DOWN){
            this.tanqJug.mover(movNulo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource().equals(inst.getVolverBtn())){
            inst.setVisible(false);
            vMenu.inciar();
        }
        if(ae.getSource().equals(vLogin.getInicioPartida())){
            JFrame ventana = new JFrame("Guerra de Tanques");
            Instrucciones i = new Instrucciones();
            VistaLogin vl = new VistaLogin();
            VistaMenu vm = new VistaMenu();
            ventana.setSize(1524, 1000);
            ventana.setLocationRelativeTo(null);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.getContentPane().add(new Pantalla(i,vl,vm), BorderLayout.CENTER);
            ventana.setVisible(true);
            ventana.setBackground(Color.white);
            
            
            Pantalla pantalla = new Pantalla(i,vl,vm);
            pantalla.run();
        }
        if(ae.getSource().equals(vMenu.getInstrucciones())){
            vMenu.setVisible(false);
            inst.inciar();
        }
        if(ae.getSource().equals(vMenu.getNuevaPartida())){
            vMenu.setVisible(false);
            vLogin.inciar();
        }
        
    }
    
}
