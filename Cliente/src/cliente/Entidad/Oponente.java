package cliente.Entidad;

public class Oponente {
    private String nombre;
    private int cantidadC;
    
    public Oponente(){
        this.nombre = "";
        this.cantidadC = 0;
    }
    public Oponente(String nombre, int cantidadC){
        this.nombre = nombre;
        this.cantidadC = cantidadC;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setCartasC(int cantidadC){
        this.cantidadC = cantidadC;
    }
    public String getNombre(){
        return this.nombre;
    }
    public int getCantidadC(){
        return this.cantidadC;
    }
}
