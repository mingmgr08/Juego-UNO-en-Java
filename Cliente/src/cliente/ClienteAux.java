package cliente;

import cliente.Vista.Interfaz;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteAux extends Thread{
    
    Interfaz i;
    Socket socket;
    private DataInputStream entrada;
    
    public ClienteAux(Interfaz i, Socket socket){
        this.i = i;
        this.socket = socket;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                entrada = new DataInputStream(this.socket.getInputStream());
                int opcion = entrada.readInt();
                switch(opcion){
                    case 1:
                        this.Determinar_Ganador();
                        break;
                    case 2:
                        this.Tirar();
                        break;
                    case 3:
                        Agarrar();
                        break;
                    case 4:
                        this.i.Establecer_Turno();
                        break;
                    case 5:
                        this.i.Abrir_Escoger_Color(true);
                        break;
                    case 6:
                        this.Establecer_Color();
                        break;
                }
            }catch(IOException e ){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
    }
    
    private void Tirar(){
        try{
            this.i.getControl().getBotada().clear();
            entrada = new DataInputStream(this.socket.getInputStream());
            int cantidad = entrada.readInt();
            this.i.getControl().setCantidad(cantidad);
            String aux = entrada.readUTF();
            this.i.getControl().setColor(aux);
            aux = entrada.readUTF();
            if(aux.equals("Botada"))
                this.i.Llenar_Lista_Carta(this.i.getControl().getBotada());
            boolean saber = entrada.readBoolean();
            if(saber){
                this.i.getControl().getJugador().getCartas().clear();
                aux = entrada.readUTF();
                if(aux.equals("Jugador"))
                    this.i.Llenar_Lista_Carta(this.i.getControl().getJugador().getCartas());
                
                if(this.i.getControl().getJugador().getCartas().isEmpty())
                    this.i.getControl().getJugador().setNum_Carta_Sel(0);
                else if(this.i.getControl().getJugador().getNum_Carta_Sel() + 1 > this.i.getControl().getJugador().getCartas().size())
                    this.i.getControl().getJugador().setNum_Carta_Sel(this.i.getControl().getJugador().getCartas().size() - 1);
            }else{
                this.i.getControl().getOponentes().clear();
                aux = entrada.readUTF();
                if(aux.equals("Oponente"))
                    this.i.Llenar_Oponentes();
            }
            this.i.Actualizar_Todo();
            this.i.Mostrar();
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    
    private void Agarrar(){
        try{
            this.i.getControl().getBaraja().clear();
            entrada = new DataInputStream(this.socket.getInputStream());
            int cantidad = entrada.readInt();
            this.i.getControl().setCantidad(cantidad);
            String aux = entrada.readUTF();
            this.i.getControl().setColor(aux);
            aux = entrada.readUTF();
            if(aux.equals("Baraja"))
                this.i.Llenar_Lista_Carta(this.i.getControl().getBaraja());
            boolean saber = entrada.readBoolean();
            if(saber){
                this.i.getControl().getJugador().getCartas().clear();
                aux = entrada.readUTF();
                if(aux.equals("Jugador"))
                    this.i.Llenar_Lista_Carta(this.i.getControl().getJugador().getCartas());
                this.i.getControl().getJugador().setNum_Carta_Sel(this.i.getControl().getJugador().getCartas().size() - 1);
            }else{
                this.i.getControl().getOponentes().clear();
                aux = entrada.readUTF();
                if(aux.equals("Oponente"))
                    this.i.Llenar_Oponentes();
            }
            this.i.Actualizar_Todo();
            this.i.Mostrar();
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    
    private void Determinar_Ganador(){
        try{
            entrada = new DataInputStream(this.socket.getInputStream());
            boolean ganador = entrada.readBoolean();
            if(ganador){
                String aux = entrada.readUTF();
                this.i.Determinar_Ganador(aux);
            }
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    
    private void Establecer_Color(){
        try{
            entrada = new DataInputStream(this.socket.getInputStream());
            String color = entrada.readUTF();
            this.i.getControl().setColor(color);
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
}
