/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Modelo;

import cliente.Entidad.Jugador;
import java.util.Observable;

/**
 *
 * @author Kvn
 */
public class Modelo extends Observable{
    public Modelo() {
        contenedorJugadores=new ContenedorJugadores();
    }
    
    public void actualizar() {
        setChanged();
        notifyObservers();
    }
    
    public void actualizar(Object arg) {
        setChanged();
        notifyObservers(arg);
    }

    public boolean agregarJugador(Jugador jugador) {
        if(contenedorJugadores.agregarJugador(jugador)){
            actualizar("Se agrego un nuevo Jugador");
            return true;
        }else
        {
            return false;
        }
        
    }

    public Object[][] recuperarTodosJugadoresTablaRegistro() {
        return contenedorJugadores.recuperarJugadoresTabla();
       
    }

    public Object obtenerObjJugadorRegistro(String nick) {
        return contenedorJugadores.obtenerJugador(nick);
    }
    public void eliminarJugador(String nick){
        contenedorJugadores.eliminaJugador(nick);
        actualizar();
    }
    private ContenedorJugadores contenedorJugadores;

    public static class Cliente {

        public Cliente() {
        }
    }
    
}
