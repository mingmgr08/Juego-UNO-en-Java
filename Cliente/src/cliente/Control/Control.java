package cliente.Control;

import cliente.Entidad.Oponente;
import cliente.Entidad.Carta;
import cliente.Entidad.Jugador;
import cliente.Modelo.Modelo;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Control {
    
    private ArrayList<Carta> baraja, botada;
    private Jugador jugador;
    private ArrayList<Oponente> oponentes;
    private int cantidad;
    private String color;
    
    public Control(){
        this.baraja = new ArrayList();
        this.botada = new ArrayList();
        this.jugador = new Jugador();
        this.oponentes = new ArrayList();
        this.cantidad = 0;
        this.color = "";
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setColor(String color){
        this.color = color;
    }
    
    public ArrayList<Carta> getBaraja(){
        return this.baraja;
    }
    public ArrayList<Carta> getBotada(){
        return this.botada;
    }
    public Jugador getJugador(){
        return this.jugador;
    }
    public ArrayList<Oponente> getOponentes(){
        return this.oponentes;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public String getColor(){
        return this.color;
    }
    
    public boolean Tirar(Socket socket){
        boolean tirar = false;
        if(this.jugador.getCartas().size() == 1 && this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getTipo().equals("Efecto"))
            tirar = false;
        else if(this.cantidad != 0){
            if(this.botada.get(0).getEfecto() == 1){
                if(this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 1 ||
                   this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 5)
                    tirar = true;
            }else if(this.botada.get(0).getEfecto() == 5){
                if(this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 5)
                    tirar = true;
            }
        }else if(!this.color.equals("")){
            if(this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getColor().equals(this.color))
                tirar = true;
        }else if(this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getColor().equals(this.botada.get(0).getColor()) ||
           this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getNumero() == this.botada.get(0).getNumero() &&
           this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 0 ||
           this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 4 ||
           this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getEfecto() == 5)
            tirar = true;
        if(tirar){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(2);
                salida.writeInt(this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getCodigo());
            }catch(IOException e){
                System.out.println("Error Cliente: " + e.getMessage());
            }
        }
        return tirar;
    }
    
    public boolean Agarrar(Socket socket){
        boolean agarrar = true;
        if(this.cantidad != 0){
            agarrar = true;
        }else if(this.jugador.getCartas().size() == 1 && this.jugador.getCartas().get(this.jugador.getNum_Carta_Sel()).getTipo().equals("Efecto"))
            agarrar = true;
        else{
            for(int i = 0; i < this.jugador.getCartas().size(); i++){
                if(!this.color.equals("")){
                    if(this.jugador.getCartas().get(i).getColor().equals(this.color))
                        agarrar = false;
                }else if(this.jugador.getCartas().get(i).getColor().equals(this.botada.get(0).getColor()) ||
                   this.jugador.getCartas().get(i).getNumero() == this.botada.get(0).getNumero() &&
                   this.jugador.getCartas().get(i).getEfecto() == 0 ||
                   this.jugador.getCartas().get(i).getEfecto() == 4 ||
                   this.jugador.getCartas().get(i).getEfecto() == 5)
                    agarrar = false;
            }
        }
        if(agarrar){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(3);
            }catch(IOException e){
                System.out.println("Error Cliente: " + e.getMessage());
            }
        }
        return agarrar;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Control(Modelo nuevoModeloDatos) {
        modeloDatos = nuevoModeloDatos;
    }
    public boolean agregarJugador(Jugador jugador){
        if(modeloDatos.agregarJugador(jugador)){
            return true;
        }else{
            return false;
        }
    }
    
    public Object[][] recuperarTodosJugadoresTablaRegistro() {
        return modeloDatos.recuperarTodosJugadoresTablaRegistro();
    }
    
    public Object obtenerObjRegistroJugador(String nick) {
        return modeloDatos.obtenerObjJugadorRegistro(nick);
       
    }
    
    public void eliminarJugador(String nick){
        modeloDatos.eliminarJugador(nick);
    }
    
    
    private Modelo modeloDatos;
}