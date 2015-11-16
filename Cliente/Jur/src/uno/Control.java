package uno;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

public class Control {
    private ArrayList<Carta> baraja, botada;
    private Jugador jugador1, jugador2, jugador3, jugador4;
    private int turno, ganador, cantidad;
    private String direccion, color;
    
    public Control(int turno){
        this.baraja = new ArrayList();
        this.botada = new ArrayList();
        this.jugador1 = new Jugador();
        this.jugador2 = new Jugador();
        this.jugador3 = new Jugador();
        this.jugador4 = new Jugador();
        this.turno = turno;
        this.direccion = "Derecha";
        this.ganador = 0;
        this.cantidad = 0;
        this.color = "";
        this.Cargar_Baraja();
    }
    
    private void Cargar_Baraja(){
        int codigo = 1;
        //////////////////////////////////Amarillo
        for(int i = 0; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Amarillo"));
            codigo++;
        }
        for(int i = 1; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Amarillo"));
            codigo++;
        }
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Amarillo"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Amarillo"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Amarillo"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Amarillo"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Amarillo"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Amarillo"));
        codigo++;
        //////////////////////////////////Azul
        for(int i = 0; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Azul"));
            codigo++;
        }
        for(int i = 1; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Azul"));
            codigo++;
        }
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Azul"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Azul"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Azul"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Azul"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Azul"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Azul"));
        codigo++;
        //////////////////////////////////Roja
        for(int i = 0; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Roja"));
            codigo++;
        }
        for(int i = 1; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Roja"));
            codigo++;
        }
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Roja"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Roja"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Roja"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Roja"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Roja"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Roja"));
        codigo++;
        //////////////////////////////////Verde
        for(int i = 0; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Verde"));
            codigo++;
        }
        for(int i = 1; i < 10; i++){
            this.baraja.add(new Carta("Normal", i, 0, codigo, "Verde"));
            codigo++;
        }
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Verde"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 1, codigo, "Verde"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Verde"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 2, codigo, "Verde"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Verde"));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 3, codigo, "Verde"));
        codigo++;
        //////////////////////////////////Cambio de Color
        this.baraja.add(new Carta("Efecto", -1, 4, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 4, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 4, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 4, codigo, ""));
        codigo++;
        //////////////////////////////////Roba 4 Cartas y Cambio de Color
        this.baraja.add(new Carta("Efecto", -1, 5, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 5, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 5, codigo, ""));
        codigo++;
        this.baraja.add(new Carta("Efecto", -1, 5, codigo, ""));
    }
    
    public ArrayList<Carta> getBaraja(){
        return this.baraja;
    }
    public ArrayList<Carta> getBotada(){
        return this.botada;
    }
    public Jugador getJugador1(){
        return this.jugador1;
    }
    public Jugador getJugador2(){
        return this.jugador2;
    }
    public Jugador getJugador3(){
        return this.jugador3;
    }
    public Jugador getJugador4(){
        return this.jugador4;
    }
    public int getTurno(){
        return this.turno;
    }
    public int getGanador(){
        return this.ganador;
    }
    
    private void Desordenar_Baraja(){
        Collections.shuffle(this.baraja);
    }
    
    private void Volver_Baraja(){
        for(int i = 1; i < this.botada.size(); i++){
            this.baraja.add(this.botada.get(i));
            this.botada.remove(i);
        }
        this.Desordenar_Baraja();
    }
    
    public void Cambiar_Turno(){
        if(this.direccion.equals("Derecha")){
            if(this.turno == 4)
                this.turno = 1;
            else
                this.turno++;
        }else{
            if(this.turno == 1)
                this.turno = 4;
            else
                this.turno--;
        }
    }
    
    public void Repartir_Cartas(){
        for(int i = 0; i < 7; i++){
            this.Repartir_Cartas_Aux();
            this.Cambiar_Turno();
            this.Repartir_Cartas_Aux();
            this.Cambiar_Turno();
            this.Repartir_Cartas_Aux();
            this.Cambiar_Turno();
            this.Repartir_Cartas_Aux();
            this.Cambiar_Turno();
        }
    }
    private void Repartir_Cartas_Aux(){
        if(this.turno == 1){
            this.jugador1.setCartas(this.baraja.get(0));
            this.baraja.remove(0);
        }else if(this.turno == 2){
            this.jugador2.setCartas(this.baraja.get(0));
            this.baraja.remove(0);
        }else if(this.turno == 3){
            this.jugador3.setCartas(this.baraja.get(0));
            this.baraja.remove(0);
        }else{
            this.jugador4.setCartas(this.baraja.get(0));
            this.baraja.remove(0);
        }  
    }
    
    private void Mostrar_Cartas(Jugador j){
        for(int i = 0; i < j.getCartas().size(); i++){
            j.getCarta(i).Mostrar();
        }
    }
    private void Mostrar(){
        System.out.println("\nCartas del Jugador 1:\n");
        Mostrar_Cartas(this.jugador1);
        System.out.println("Cantidad de cartas: " + this.jugador1.getCartas().size() + "\n");

        System.out.println("Cartas del Jugador 2:\n");
        Mostrar_Cartas(this.jugador2);
        System.out.println("Cantidad de cartas: " + this.jugador2.getCartas().size() + "\n");
        
        System.out.println("Cartas del Jugador 3:\n");
        Mostrar_Cartas(this.jugador3);
        System.out.println("Cantidad de cartas: " + this.jugador3.getCartas().size() + "\n");
        
        System.out.println("Cartas del Jugador 4:\n");
        Mostrar_Cartas(this.jugador4);
        System.out.println("Cantidad de cartas: " + this.jugador4.getCartas().size() + "\n");
    }
    
    private void Efecto(int num){
        int azar;
        switch(num){
            case 1:
                System.out.println("\nSe aplico efecto roba 2 cartas..................................................");
                this.cantidad += 2;
                break;
            case 2:
                System.out.println("\nSe aplico efecto cambio de direccion..................................................");
                if(this.direccion.equals("Derecha"))
                    this.direccion = "Izquierda";
                else
                    this.direccion = "Derecha";
                break;
            case 3:
                System.out.println("\nSe aplico efecto prohibir el turno..................................................");
                this.Cambiar_Turno();
                break;
            case 4:
                System.out.println("\nSe aplico efecto Seleccionar Color..................................................");
                azar = (int) Math.round((Math.random() * 3)) + 1;
                if(azar == 1)
                    this.color = "Amarillo";
                else if(azar == 2)
                    this.color = "Azul";
                else if(azar == 3)
                    this.color = "Roja";
                else if(azar == 4)
                    this.color = "Verde";
                System.out.println("El color seleccionado es " + this.color);
                break;
            case 5:
                System.out.println("\nSe aplico efecto roba 4 cartas..................................................");
                this.cantidad += 4;
                break;
        }
    }
    
    private void Aplicar_Efecto(){
        if(this.botada.get(0).getTipo().equals("Efecto")){
            if(this.botada.get(0).getEfecto() == 1)
                this.Efecto(1);
            else if(this.botada.get(0).getEfecto() == 2)
                this.Efecto(2);
            else if(this.botada.get(0).getEfecto() == 3)
                this.Efecto(3);
            else if(this.botada.get(0).getEfecto() == 4)
                this.Efecto(4);
            else{
                this.Efecto(4);
                this.Efecto(5);
            }
        }
    }
    
    public void Comenzar_Juego(){
        this.Desordenar_Baraja();
        this.Repartir_Cartas();
        boolean correcto = true;
        while(correcto){
            Carta c = this.baraja.get(0);
            if(c.getTipo().equals("Efecto") || c.getEfecto() == 1 || c.getEfecto() == 3){
                this.baraja.remove(0);
                int p = (int) Math.round((Math.random() * this.baraja.size() - 1));
                this.baraja.add(p, c);
            }else {
                this.botada.add(c);
                this.baraja.remove(0);
                correcto = false;
            }
        }
        if(this.botada.get(0).getEfecto() == 2)
            this.Efecto(2);
        this.jugador1.setNum_Carta_Sel(0);
    }
    
    private void Jugar_Aux(Jugador j){
        boolean estado = true;
        if(this.botada.get(0).getEfecto() == 1){
            for(int i = 0; i < j.getCartas().size(); i++){
                if(j.getCartas().size() > 1){
                    if(j.getCarta(i).getEfecto() == 5 || j.getCarta(i).getEfecto() == 1){
                        this.botada.add(0, j.getCarta(i));
                        j.getCartas().remove(i);
                        estado = false;
                        break;
                    }
                }
            }
            if(estado){
                for(int i = 0; i < this.cantidad; i++){
                    if(this.baraja.isEmpty())
                        this.Volver_Baraja();
                    j.setCartas(this.baraja.get(0));
                    this.baraja.remove(0);
                }
                this.cantidad = 0;
            }
        }else if(this.botada.get(0).getEfecto() == 5){
            for(int i = 0; i < j.getCartas().size(); i++){
                if(j.getCartas().size() > 1){
                    if(j.getCarta(i).getEfecto() == 5){
                        this.botada.add(0, j.getCarta(i));
                        j.getCartas().remove(i);
                        estado = false;
                        break;
                    }
                }
            }
            if(estado){
                for(int i = 0; i < this.cantidad; i++){
                    if(this.baraja.isEmpty())
                        this.Volver_Baraja();
                    j.setCartas(this.baraja.get(0));
                    this.baraja.remove(0);
                }
                this.cantidad = 0;
            }
        }
        
        while(estado){
            for(int i = 0; i < j.getCartas().size(); i++){
                if(!this.color.equals("")){
                    if(j.getCarta(i).getColor().equals(this.color)){
                        this.botada.add(0, j.getCarta(i));
                        j.getCartas().remove(i);
                        estado = false;
                        break;
                    }
                }else if(j.getCartas().size() == 1){
                    if(j.getCarta(i).getColor().equals(this.botada.get(0).getColor()) && j.getCarta(i).getEfecto() == 0){
                        this.botada.add(0, j.getCarta(i));
                        j.getCartas().remove(i);
                        estado = false;
                        break;
                    }
                }else if(j.getCarta(i).getColor().equals(this.botada.get(0).getColor()) ||
                         j.getCarta(i).getNumero() == this.botada.get(0).getNumero() && j.getCarta(i).getEfecto() == 0 ||
                         j.getCarta(i).getEfecto() == 4 && j.getCartas().size() > 1 ||
                         j.getCarta(i).getEfecto() == 5 && j.getCartas().size() > 1){
                    this.botada.add(0, j.getCarta(i));
                    j.getCartas().remove(i);
                    estado = false;
                    break;
                }
            }
            if(estado){
                if(this.baraja.isEmpty())
                    this.Volver_Baraja();
                j.setCartas(this.baraja.get(0));
                this.baraja.remove(0);
            }
        }
        if(!this.color.equals(""))
            this.color = "";
        this.Aplicar_Efecto();
    }
    
    public void Jugar(){
        while(this.turno != 1){
            System.out.println("Turno del jugador " + this.turno);
            if(this.turno == 2)
                this.Jugar_Aux(this.jugador2);
            else if(this.turno == 3)
                this.Jugar_Aux(this.jugador3);
            else 
                this.Jugar_Aux(this.jugador4);
            
            if(this.jugador2.getCartas().isEmpty()){
                this.Mostrar();
                System.out.println("\nJugador 2 ganó\n");
                this.ganador = 2;
                break;
            }else if(this.jugador3.getCartas().isEmpty()){
                this.Mostrar();
                System.out.println("\nJugador 3 ganó\n");
                this.ganador = 3;
                break;
            }else if(this.jugador4.getCartas().isEmpty()){
                this.Mostrar();
                System.out.println("\nJugador 4 ganó\n");
                this.ganador = 4;
                break;
            }else
                this.Mostrar();
            System.out.println("Tamaño de la baraja es: " + this.baraja.size());
            System.out.println("Carta en juego:");
            this.botada.get(0).Mostrar();
            System.out.println("Tamaño de la botada es: " + this.botada.size());
            System.out.println("Direccion del juego: " + this.direccion);
            this.Cambiar_Turno();
            System.out.println("Turno del siguiente jugador " + this.turno);
        }
    }
    
    public boolean Tirar(){
        boolean tirar = false;
        if(this.jugador1.getCartas().size() == 1 && this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getTipo().equals("Efecto")){
            tirar = false;
        }else if(this.cantidad != 0){
            if(this.botada.get(0).getEfecto() == 1){
                if(this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 1 ||
                   this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 5)
                    tirar = true;
            }else if(this.botada.get(0).getEfecto() == 5){
                if(this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 5)
                    tirar = true;
            }
        }else if(!this.color.equals("")){
            if(this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getColor().equals(this.color))
                tirar = true;
        }else if(this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getColor().equals(this.botada.get(0).getColor()) ||
                 this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getNumero() == this.botada.get(0).getNumero() &&
                 this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 0 ||
                 this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 4 ||
                 this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()).getEfecto() == 5)
            tirar = true;
        
        if(tirar){
            this.botada.add(0, this.jugador1.getCarta(this.jugador1.getNum_Carta_Sel()));
            this.jugador1.getCartas().remove(this.jugador1.getNum_Carta_Sel());
            if(this.jugador1.getNum_Carta_Sel() + 1 > this.jugador1.getCartas().size())
                this.jugador1.setNum_Carta_Sel(this.jugador1.getCartas().size() - 1);
            if(this.jugador1.getCartas().isEmpty()){
                this.Mostrar();
                System.out.println("\nJugador 1 ganó\n");
                this.ganador = 1;
            }
            if(!this.color.equals("")){
                this.color = "";
            }
            this.Aplicar_Efecto();
        }
        return tirar;
    }
    
    public boolean Agarrar(){
        if(this.cantidad != 0){
             for(int i = 0; i < this.cantidad; i++){
                if(this.baraja.isEmpty())
                    this.Volver_Baraja();
                this.jugador1.setCartas(this.baraja.get(0));
                this.baraja.remove(0);
            }
            this.jugador1.setNum_Carta_Sel(this.jugador1.getCartas().size() - 1);
            JOptionPane.showMessageDialog(null, "Se te aplico efecto roba cartas, que son: " + this.cantidad, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            this.cantidad = 0;
            return true;
        }else{
            for(int i = 0; i < this.jugador1.getCartas().size(); i++){
                if(!this.color.equals("")){
                    if(this.jugador1.getCarta(i).getColor().equals(this.color))
                        return false;
                }else if(this.jugador1.getCarta(i).getColor().equals(this.botada.get(0).getColor()) ||
                   this.jugador1.getCarta(i).getNumero() == this.botada.get(0).getNumero() &&
                   this.jugador1.getCarta(i).getEfecto() == 0 ||
                   this.jugador1.getCarta(i).getEfecto() == 4 ||
                   this.jugador1.getCarta(i).getEfecto() == 5)
                    return false;
            }
            if(this.baraja.isEmpty())
                this.Volver_Baraja();
            this.jugador1.setCartas(this.baraja.get(0));
            this.baraja.remove(0);
            this.jugador1.setNum_Carta_Sel(this.jugador1.getCartas().size() - 1);
            return true;
        }
    }
}