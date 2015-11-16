package uno;

import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private ArrayList<Carta> cartas;
    private int num_carta_sel;
    
    public Jugador(){
        this.nombre = "";
        this.cartas = new ArrayList();
        this.num_carta_sel = -1;
    }
    public Jugador(String nombre){
        this.nombre = nombre;
        this.cartas = new ArrayList();
        this.num_carta_sel = -1;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCartas(Carta carta){
        this.cartas.add(carta);
    }
    public void setNum_Carta_Sel(int n){
        this.num_carta_sel = n;
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList getCartas(){
        return this.cartas;
    }
    public Carta getCarta(int p){
        return this.cartas.get(p);
    }
    public int getNum_Carta_Sel(){
        return this.num_carta_sel;
    }
    
    public void Mostrar(){
        for(int i = 0; i < this.cartas.size(); i++){
            this.cartas.get(i).Mostrar();
        }
    }
}
