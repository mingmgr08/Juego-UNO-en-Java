package servidor;

public class Servidor {
    public static void main(String[] args) {
        Control c = new Control();
        VentanaServidor ventana = new VentanaServidor(c);
        c.addObserver(ventana);
        
        ventana.iniciar();
        c.initServer();
    }
}
