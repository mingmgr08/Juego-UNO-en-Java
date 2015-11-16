package cliente;

import cliente.Control.Control;
import cliente.Vista.Interfaz;
import cliente.Modelo.Modelo;
import cliente.Vista.LogIn;
import javax.swing.JOptionPane;

public class Cliente {
    public static void main(String[] args) {
        muestraInterfaz();
    }
    public static void muestraInterfaz(){
        Modelo modelo = new Modelo();
        Control control = new Control(modelo);
        LogIn log=new LogIn(control);
    }
}