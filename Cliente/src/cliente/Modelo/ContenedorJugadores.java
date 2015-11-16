/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Modelo;

import cliente.Entidad.Jugador;
import java.util.ArrayList;

/**
 *
 * @author Kvn
 */
public class ContenedorJugadores {
    
    public boolean agregarJugador(Object jugador){
        Jugador nuevoJugador = (Jugador) jugador;
        if(jugadores.contains(nuevoJugador)){
            return false;
        }else{
            jugadores.add(nuevoJugador);
            return true;
        }
        
    }

    
    public String getCantidadJugadores(){
        return Integer.toString(jugadores.size());
    }
    
    public Object obtenerJugador(String nick){
        Object obj = null;
        for (Jugador jugador : jugadores) {
            if(nick==jugador.getNombre()){
                obj = jugador;
            }
        }
        return obj;
    }
    public Object[][] recuperarJugadoresTabla(){        
        Object[][] objetos = new Object[jugadores.size()][Jugador.NOMB_ATRIBUTOS.length];
        int numRegistro = 0;
        for (Jugador jugador : jugadores) {
            Object[] atributos = jugador.toStringArrayObjetc();
            for(int campo = 0; campo < objetos[numRegistro].length; campo++) {
                objetos[numRegistro][campo] = atributos[campo];
            }
            numRegistro++;
        }
        return objetos;
    }
    
    public void eliminaJugador(String nick){
        for (Jugador jugador : jugadores) {
            if(nick==jugador.getNombre()){
                jugadores.remove(jugador);
            }
        }
    }
  
    
    
    
    private ArrayList<Jugador> jugadores;
    
}
