/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.Vista;

import cliente.Control.Control;
import cliente.Entidad.Jugador;



/**
 *
 * @author Kvn
 */
public class IMEC_Jugador {
    protected static boolean agregarJugador(String nombre,Control controlPrincipal){
        // Crear registro unico
        boolean bandera = false;
        System.out.println();
        try {
            Jugador jugador = new Jugador(nombre);
            if(controlPrincipal.agregarJugador(jugador)){
                bandera=true;
            }
            else{
                bandera=false;
            }
            
            System.out.println("Se guardo el jugador "+ nombre);
        } catch (Exception ex) {
            System.out.println("No se guardo el registro del jugador"+ nombre);
        }
        return bandera;
    }
    
    protected static Object[][] recuperarTodosJugadoresTablaRegistro(Control controlPrincipal){
        System.out.println();
        Object[][] registros = new Object[0][0];
        try {
            registros = controlPrincipal.recuperarTodosJugadoresTablaRegistro();
            System.out.println("Se recuperaron todos los registros de jugadores");
        } catch (Exception ex) {
            System.out.println("No se recuperaron todos los registros 1");
        }
        return registros;
    }
    protected static void EliminarJugador(String nick,Control controlPrincipal){
        try {
            controlPrincipal.eliminarJugador(nick);
        }
        catch(Exception ex) {
            System.out.println("Error al eliminar al jugador "+nick);
        }
    
    }
    protected static Jugador obtenerRegistro(String nick,Control controlPrincipal){
        // Recuperar un registro
        Jugador registroRecuperado = null;
        try {
            registroRecuperado = (Jugador)controlPrincipal.obtenerObjRegistroJugador(nick);
            if (registroRecuperado != null){
                System.out.println("Se recuperaro al jugador "+nick);
            } else {
                System.out.println("No se recupero al jugador "+nick);
            }
        } catch (Exception ex) {
            System.out.println("Error al recuperar al jugador "+nick);
        }
        return registroRecuperado;
    }
    
    protected static void generarPersonasPrueba(Control controlPrincipal){
        try {
            controlPrincipal.agregarJugador(new Jugador("nombre1"));
            controlPrincipal.agregarJugador(new Jugador("nombre2"));
            controlPrincipal.agregarJugador(new Jugador("nombre3"));
            controlPrincipal.agregarJugador(new Jugador("nombre4"));
            controlPrincipal.agregarJugador(new Jugador("nombre5"));
        } catch (Exception ex) {
        }
    }
    
}
