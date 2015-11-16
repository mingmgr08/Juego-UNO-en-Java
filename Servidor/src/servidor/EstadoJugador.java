package servidor;

import java.util.ArrayList;

public class EstadoJugador {
    private String nombre;
    private ArrayList<Carta> cartas;
    private ArrayList<Oponente> oponentes;
    
    public EstadoJugador(){
        this.nombre = "";
        this.cartas = new ArrayList();
        this.oponentes = new ArrayList();
    }
    public EstadoJugador(String nombre){
        this.nombre = nombre;
        this.cartas = new ArrayList();
        this.oponentes = new ArrayList();
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCarta(Carta carta){
        this.cartas.add(carta);
    }
    public void setOponente(Oponente o){
        this.oponentes.add(o);
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Carta> getCartas(){
        return this.cartas;
    }
    public ArrayList<Oponente> getOponentes(){
        return this.oponentes;
    }
    
    public void Mostrar(){
        System.out.println("\nJugador " + this.getNombre() + " tiene las siguientes cartas:\n");
        for(Carta c : this.cartas)
            c.Mostrar();
        System.out.println("\nSus oponentes son:\n");
        for(Oponente o : this.oponentes)
            o.Mostrar();
    }
}
