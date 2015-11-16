package uno;

public class Carta {
    private int numero, efecto, codigo;
    private String color, tipo;

    public Carta(){
        this.numero = -1;
        this.efecto = 0;
        this.codigo = 0;
        this.color = "";
        this.tipo = "";
    }
    public Carta(String tipo, int numero, int efecto, int codigo, String color){ 
        this.numero = numero;
        this.efecto = efecto;
        this.codigo = codigo;
        this.color = color;
        this.tipo = tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    public void setEfecto(int efecto){
        this.efecto = efecto;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    public String getTipo(){
        return this.tipo;
    }
    public int getNumero(){
        return this.numero;
    }
    public int getEfecto(){
        return this.efecto;
    }
    public String getColor(){
        return this.color;
    }
    public int getCodigo(){
        return this.codigo;
    }
    
    public void Mostrar(){
        System.out.println("Codigo: " + this.getCodigo());
        System.out.println("Numero: " + this.getNumero());
        System.out.println("Efecto: " + this.getEfecto());
        System.out.println("Color: " + this.getColor());
    }
}
