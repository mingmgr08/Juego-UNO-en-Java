package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Control extends Observable{
    final int PUERTO = 5000;
    private ServerSocket serverS; 
    private Socket socket;
    ArrayList <Socket> listaS;
    
    private ArrayList<Carta> baraja, botada;
    private ArrayList<EstadoJugador> jugadores;
    private int turno, ganador, cantidad;
    private String direccion, color;
    
    private ContenedorGanadores jugadoresGanadores;
    private Thread hiloTiempo;
    private long tiempo;//segundos 
    private Serializable serializable;
    private static String ETQ_RAIZ = "XMLganadores";
    private boolean bandera;
    
    public Control(){
        this.jugadoresGanadores = new ContenedorGanadores();
        this.tiempo = 0;
        this.serializable = new Serializable();
        
        this.baraja = new ArrayList();
        this.botada = new ArrayList();
        this.jugadores = new ArrayList();
        this.listaS = new ArrayList();
        this.turno = -1;
        this.direccion = "Derecha";
        this.ganador = 0;
        this.cantidad = 0;
        this.color = "";
        this.Cargar_Baraja();
    }

    public void initServer(){
        try{
            this.serverS = new ServerSocket(PUERTO);
            while(this.listaS.size() < 4){
                this.socket = new Socket();
                this.socket = this.serverS.accept(); 
                System.out.println("Un cliente se ha conectado");
                DataInputStream entrada = new DataInputStream(this.socket.getInputStream());
                String nombre = entrada.readUTF();
                this.jugadores.add(new EstadoJugador(nombre));
                this.listaS.add(this.socket);
                ServidorAux aux = new ServidorAux(this, this.socket);
                aux.start();
            }
            this.Comenzar_Juego();
        }catch(IOException e ){
            System.out.println("Error Servidor: " + e.getMessage());
        }
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
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setTurno(int turno){
        this.turno = turno;
    }
    public void setGanador(int ganador){
        this.ganador = ganador;
    }
    
    public ArrayList<Carta> getBaraja(){
        return this.baraja;
    }
    public ArrayList<Carta> getBotada(){
        return this.botada;
    }
    public ArrayList<EstadoJugador> getJugadores(){
        return this.jugadores;
    }
    public int getTurno(){
        return this.turno;
    }
    public int getGanador(){
        return this.ganador;
    }
    public String getDireccion(){
        return this.direccion;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public String getColor(){
        return this.color;
    }
    
    private void Desordenar_Baraja(){
        Collections.shuffle(this.baraja);
    }
    
    public void Volver_Baraja(){
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
            this.jugadores.get(this.turno - 1).setCarta(this.baraja.get(0));
            this.baraja.remove(0);
            this.Cambiar_Turno();
            this.jugadores.get(this.turno - 1).setCarta(this.baraja.get(0));
            this.baraja.remove(0);
            this.Cambiar_Turno();
            this.jugadores.get(this.turno - 1).setCarta(this.baraja.get(0));
            this.baraja.remove(0);
            this.Cambiar_Turno();
            this.jugadores.get(this.turno - 1).setCarta(this.baraja.get(0));
            this.baraja.remove(0);
            this.Cambiar_Turno();
        }
    }
    
    public void Oponentes(){
        int contador = 1;
        while(contador <= 4){
            this.turno = contador;
            this.Cambiar_Turno();
            this.jugadores.get(contador - 1).getOponentes().clear();
            while(this.turno != contador){
                this.jugadores.get(contador - 1).setOponente(new Oponente(this.jugadores.get(this.turno - 1).getNombre(), this.jugadores.get(this.turno - 1).getCartas().size()));
                this.Cambiar_Turno();
            }
            contador++;
        }
    }
    
    public void Enviar_Oponentes(ArrayList<Oponente> l, DataOutputStream salida){
        try{
            salida.writeInt(l.size());
            for(int i = 0; i < l.size(); i++){
                salida.writeUTF(l.get(i).getNombre());
                salida.writeInt(l.get(i).getCantidadC());
            }
        }catch(IOException e ){
            System.out.println("Error Servidor: " + e.getMessage());
        }
    }
    public void Enviar_Cartas_Jugador(ArrayList<Carta> l, DataOutputStream salida){
        try{
            salida.writeInt(l.size());
            for(int i = 0; i < l.size(); i++){
                salida.writeUTF(l.get(i).getTipo());
                salida.writeInt(l.get(i).getNumero());
                salida.writeInt(l.get(i).getEfecto());
                salida.writeInt(l.get(i).getCodigo());
                salida.writeUTF(l.get(i).getColor());
            }
        }catch(IOException e ){
            System.out.println("Error Servidor: " + e.getMessage());
        }
    }
    
    public void Mostrar(){
        System.out.println("/////////////////////////////////////////////////////////////////////////////");
        System.out.println("Turno del jugador " + this.jugadores.get(this.turno - 1).getNombre());
        for(EstadoJugador j : this.jugadores)
            j.Mostrar();
    }
    
    public void Efecto(int num){
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
                try{
                    DataOutputStream salida = new DataOutputStream(this.listaS.get(this.turno - 1).getOutputStream());
                    salida.writeInt(5);
                }catch(IOException e){
                    System.out.println("Error Servidor: " + e.getMessage());
                }
                break;
            case 5:
                System.out.println("\nSe aplico efecto roba 4 cartas..................................................");
                this.cantidad += 4;
                break;
        }
    }
    
    public void Comenzar_Juego(){
         contarTiempo();
        int selecTurno = (int) Math.round((Math.random() * 3)) + 1;
        this.turno = selecTurno;
        this.Desordenar_Baraja();
        this.Repartir_Cartas();
        
       
        
        this.Oponentes();
        this.turno = selecTurno;
        boolean correcto = true;
        while(correcto){
            Carta c = this.baraja.get(0);
            if(c.getTipo().equals("Efecto")){
                this.baraja.remove(0);
                int p = (int) Math.round((Math.random() * this.baraja.size() - 1));
                this.baraja.add(p, c);
            }else {
                this.botada.add(c);
                this.baraja.remove(0);
                correcto = false;
            }
        }

        for(int i = 0; i < this.jugadores.size(); i++){
            try{
                DataOutputStream salida = new DataOutputStream(this.listaS.get(i).getOutputStream());
                salida.writeUTF("Baraja");
                this.Enviar_Cartas_Jugador(this.baraja, salida); 
                salida.writeUTF("Jugador");
                this.Enviar_Cartas_Jugador(this.jugadores.get(i).getCartas(), salida);
                salida.writeUTF("Oponente");
                this.Enviar_Oponentes(this.jugadores.get(i).getOponentes(), salida);
                salida.writeUTF("Botada");
                this.Enviar_Cartas_Jugador(this.botada, salida); 
                salida.writeUTF("Turno");
                if(i == this.turno - 1)
                    salida.writeBoolean(true);
                else
                    salida.writeBoolean(false);
            }catch(IOException e ){
                System.out.println("Error Servidor: " + e.getMessage());
            }
        }
        this.Mostrar();
    }
    
    private void guardarGanadores() {
        System.out.println("Guardando personanas");


        File file = null;


        file = new File("ganadores.xml");//seleccionarXML.getSelectedFile();
        //System.out.println("...jdjdj >  " + seleccionarXML.getSelectedFile().getAbsolutePath());
        Document documentoXML = serializable.crearXMLFile();
        Element elementoRaiz = documentoXML.createElement(ETQ_RAIZ);
        elementoRaiz.appendChild(jugadoresGanadores.guardarXML(documentoXML));
        serializable.guardarXMLFile(elementoRaiz, file);

        System.out.println("Ganadores Guardadas");
    }

    private void leerGanadores() {


        File file = null;
        //int resultado = seleccionarXML.showSaveDialog(null);
        //if (resultado == JFileChooser.APPROVE_OPTION) {
        file = new File("ganadores.xml");//seleccionarXML.getSelectedFile();
        if (file.exists()) {
            try {
                System.out.println("Abriendo documento...");
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);

                doc.getDocumentElement().normalize();//?????

                System.out.println("Root element : " + doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getElementsByTagName("JugadorGanador");

                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nCurrent Element: " + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
//                        System.out.println("Nombre: " + eElement.getAttribute("nombre"));
                        jugadoresGanadores.agregarGanador(eElement.getAttribute("nickname"), Integer.parseInt(eElement.getAttribute("tiempo")));
                    }

                }

            } catch (ParserConfigurationException ex) {
                System.err.println("Me cai aqui1" + ex.getMessage());
                Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                System.err.println("Me cai aqui2" + ex.getMessage());

                Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                System.err.println("Me cai aqui3" + ex.getMessage());
                Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
    
    
    
    public void contarTiempo() {
        bandera = true;
        hiloTiempo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera) {
                    setChanged();
                    notifyObservers(new Long(tiempo));
                    System.out.println("\nTiempo: " + tiempo);
                    try {
                        tiempo++;
                        
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServidorAux.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        hiloTiempo.start();
    }
}