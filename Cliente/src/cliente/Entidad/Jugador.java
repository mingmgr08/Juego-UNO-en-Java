package cliente.Entidad;

import cliente.Entidad.Carta;
import java.util.ArrayList;

public class Jugador {
    private String nombre;
    private String estado;
    private ArrayList<Carta> cartas;
    private int num_carta_sel;
    
    public Jugador(){
        this.nombre = "";
        this.estado="Conectado";
        this.cartas = new ArrayList();
        this.num_carta_sel = 0;
    }
    public Jugador(String nombre){
        this.nombre = nombre;
        this.cartas = new ArrayList();
        this.num_carta_sel = 0;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCarta(Carta c){
        this.cartas.add(c);
    }
    public void setNum_Carta_Sel(int n){
        this.num_carta_sel = n;
    }
    public String getNombre(){
        return this.nombre;
    }
    public ArrayList<Carta> getCartas(){
        return this.cartas;
    }
    public int getNum_Carta_Sel(){
        return this.num_carta_sel;
    }
    
    public void Mostrar(){
        for(int i = 0; i < this.cartas.size(); i++){
            this.cartas.get(i).Mostrar();
        }
    }
public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    
    
    public Object[] toStringArrayObjetc() {
        Object[] r = new Object[2];
        r[0] = getNombre();
        r[1] = getEstado();
        return r;
    }
    
    public String[] toStringArrayString() {
        String[] r = new String[2];
        r[0] = getNombre();
        r[1] = getEstado();

        return r;
    }
    
    public static final String[] NOMB_ATRIBUTOS = {"Nombre","Estado"};
    
    public static final Class[] CLASS_ATRIBUTOS = { java.lang.String.class,
                                                    java.lang.String.class};
    
    public static final boolean[] BOOLEAN_ATRIBUTOS = {false,false};
}

