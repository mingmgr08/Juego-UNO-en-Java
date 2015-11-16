package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServidorAux extends Thread{
    private Control control;
    private Socket socket;
    private DataInputStream entrada;
    
    
    public ServidorAux(Control control, Socket socket){

        this.control = control;
        this.socket = socket;
     
   
    }
    
    @Override
    public void run(){
   
        while(true){
            try{
                entrada = new DataInputStream(this.socket.getInputStream());
                int opcion = entrada.readInt();
                switch(opcion){
                    case 2:
                        int codigo = entrada.readInt();
                        this.Tirar(codigo);
                        this.Determinar_Ganador();
                        break;
                    case 3:
                        this.Agarrar();
                        break;
                    case 4:
                        this.Definir_Turno();
                        break;
                    case 5:
                        this.Establecer_Color();
                        break;
                }
            }catch(IOException e ){
                System.out.println("Error Servidor: " + e.getMessage());
                try {
                    this.socket.close();
                } catch (IOException ex) {
                    System.out.println("Error Servidor: " + ex.getMessage());
                }
                break;
            }
        }
    }
    
    private void Tirar(int codigo){
        for(int i = 0; i < this.control.getJugadores().get(this.control.getTurno() - 1).getCartas().size(); i++){
            if(this.control.getJugadores().get(this.control.getTurno() - 1).getCartas().get(i).getCodigo() == codigo){
                this.control.getBotada().add(0, this.control.getJugadores().get(this.control.getTurno() - 1).getCartas().get(i));
                this.control.getJugadores().get(this.control.getTurno() - 1).getCartas().remove(i);
                break;
            }
        }
        if(!this.control.getColor().equals(""))
            this.control.setColor("");
        if(this.control.getJugadores().get(this.control.getTurno() - 1).getCartas().isEmpty())
            this.control.setGanador(this.control.getTurno());
        
        if(this.control.getBotada().get(0).getEfecto() == 1)
            this.control.Efecto(1);
        else if(this.control.getBotada().get(0).getEfecto() == 2)
            this.control.Efecto(2);
        else if(this.control.getBotada().get(0).getEfecto() == 4)
            this.control.Efecto(4);
        else if(this.control.getBotada().get(0).getEfecto() == 5){
            this.control.Efecto(4);
            this.control.Efecto(5);
        }
        
        int turno = this.control.getTurno();
        this.control.Oponentes();
        for(int i = 0; i < this.control.listaS.size(); i++){
            try{
                DataOutputStream salida = new DataOutputStream(this.control.listaS.get(i).getOutputStream());
                salida.writeInt(2);
                salida.writeInt(this.control.getCantidad());
                salida.writeUTF(this.control.getColor());
                salida.writeUTF("Botada");
                this.control.Enviar_Cartas_Jugador(this.control.getBotada(), salida);
                if(turno - 1 == i){
                    salida.writeBoolean(true);
                    salida.writeUTF("Jugador");
                    this.control.Enviar_Cartas_Jugador(this.control.getJugadores().get(i).getCartas(), salida);
                }else{
                    salida.writeBoolean(false);
                    salida.writeUTF("Oponente");
                    this.control.Enviar_Oponentes(this.control.getJugadores().get(i).getOponentes(), salida);
                }
            }catch(IOException e){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
        this.control.setTurno(turno);
        
        if(this.control.getBotada().get(0).getEfecto() == 3)
            this.control.Efecto(3);
    }
    
    private void Definir_Turno(){
        this.control.Cambiar_Turno();
        for(int i = 0; i < this.control.getJugadores().size(); i++){
            try{
                DataOutputStream salida = new DataOutputStream(this.control.listaS.get(i).getOutputStream());
                salida.writeInt(4);
                if(i == this.control.getTurno() - 1)
                    salida.writeBoolean(true);
                else
                    salida.writeBoolean(false);
            }catch(IOException e){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
        this.control.Mostrar();
    }
    
    private void Agarrar(){
        if(this.control.getCantidad() != 0){
            for(int i = 0; i < this.control.getCantidad(); i++){
                if(this.control.getBaraja().isEmpty())
                    this.control.Volver_Baraja();
                this.control.getJugadores().get(this.control.getTurno() - 1).setCarta(this.control.getBaraja().get(0));
                this.control.getBaraja().remove(0);
            }
            this.control.setCantidad(0);
        }else{
            if(this.control.getBaraja().isEmpty())
                this.control.Volver_Baraja();
            this.control.getJugadores().get(this.control.getTurno() - 1).setCarta(this.control.getBaraja().get(0));
            this.control.getBaraja().remove(0);
        }
        
        int turno = this.control.getTurno();
        this.control.Oponentes();
        for(int i = 0; i < this.control.listaS.size(); i++){
            try{
                DataOutputStream salida = new DataOutputStream(this.control.listaS.get(i).getOutputStream());
                salida.writeInt(3);
                salida.writeInt(this.control.getCantidad());
                salida.writeUTF(this.control.getColor());
                salida.writeUTF("Baraja");
                this.control.Enviar_Cartas_Jugador(this.control.getBaraja(), salida); 
                if(turno - 1 == i){
                    salida.writeBoolean(true);
                    salida.writeUTF("Jugador");
                    this.control.Enviar_Cartas_Jugador(this.control.getJugadores().get(i).getCartas(), salida);
                }else{
                    salida.writeBoolean(false);
                    salida.writeUTF("Oponente");
                    this.control.Enviar_Oponentes(this.control.getJugadores().get(i).getOponentes(), salida);
                }
            }catch(IOException e){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
        this.control.setTurno(turno);
    }
    
    private void Determinar_Ganador(){
        for(int i = 0; i < this.control.listaS.size(); i++){
            try{
                DataOutputStream salida = new DataOutputStream(this.control.listaS.get(i).getOutputStream());
                salida.writeInt(1);
                if(this.control.getGanador() != 0){
                    salida.writeBoolean(true);
                    salida.writeUTF(this.control.getJugadores().get(this.control.getGanador()- 1).getNombre());  
                    
                }else
                    salida.writeBoolean(false);
            }catch(IOException e){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
    }
    
    private void Establecer_Color(){
        try{
            entrada = new DataInputStream(this.socket.getInputStream());
            String color = entrada.readUTF();
            this.control.setColor(color);
            for(int i = 0; i < this.control.listaS.size(); i++){
                try{
                    DataOutputStream salida = new DataOutputStream(this.control.listaS.get(i).getOutputStream());
                    salida.writeInt(6);
                    salida.writeUTF(this.control.getColor());
                }catch(IOException e){
                    System.out.println("Error Servidor: " + e.getMessage());
                }
            }
        }catch(IOException e){
            System.out.println("Error Servidor: " + e.getMessage());
        }
    }
      
}