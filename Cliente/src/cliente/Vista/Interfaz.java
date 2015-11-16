package cliente.Vista;

import cliente.ClienteAux;
import cliente.Entidad.Imagen;
import cliente.Control.Control;
import cliente.Entidad.Oponente;
import cliente.Entidad.Carta;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Interfaz extends JFrame implements ActionListener{
    final String HOST = "localhost";
    final int PUERTO = 5000;
    Socket socket;
    
    private JMenuBar barra;
    private Imagen imagenFondo, jugador, oponente1, oponente2, oponente3, baraja, botada, mostrador;
    private Control control;
    private JButton b1, b2, b3, b4, color1, color2, color3, color4; 
    private boolean posiPasar, posiAgarrar, posiTirar;
    private JLabel l1, l2, l3;
    
    public Interfaz(String nombre){
        super(nombre);
        this.setSize(1200,700);
        this.setLayout(null);
        this.control = new Control();
        this.control.getJugador().setNombre(nombre);
        this.posiPasar = false;
        this.posiAgarrar = true;
        this.posiTirar = true;
        this.Inicial_Menu();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    private void Inicial_Menu(){
        this.barra = new JMenuBar();
        this.barra.setBounds(0, 0, this.getWidth(), 25);
        this.add(this.barra);
        
        this.l1 = new JLabel();
        this.l1.setBounds(900, 115, 120, 25);
        this.add(this.l1);
        this.l2 = new JLabel();
        this.l2.setBounds(570, 25, 120, 25);
        this.add(this.l2);
        this.l3 = new JLabel();
        this.l3.setBounds(270, 175, 120, 25);
        this.add(this.l3);
        
        this.oponente1 = new Imagen(110, 150, "Oponente_0.png");
        this.oponente1.setLocation(900, 140);
        this.add(this.oponente1);
        this.oponente2 = new Imagen(110, 150, "Oponente_0.png");
        this.oponente2.setLocation(570, 50);
        this.add(this.oponente2);
        this.oponente3 = new Imagen(110, 150, "Oponente_0.png");
        this.oponente3.setLocation(270, 200);
        this.add(this.oponente3);
        
        this.baraja = new Imagen(70, 110, "Boca_Abajo.png");
        this.baraja.setLocation(550, 270);
        this.add(this.baraja);
        if(!this.control.getBotada().isEmpty()){
            Carta c = this.control.getBotada().get(0);
            if(c.getTipo().equals("Normal"))
                this.botada = new Imagen(70, 110, c.getColor() + "_" + c.getNumero() + ".png");
        }else
            this.botada = new Imagen(70, 110, "");
        this.botada.setLocation(630, 290);
        this.add(this.botada);
        
        this.jugador = new Imagen(90, 130, "Boca_Abajo.png");
        this.jugador.setLocation(550, 490);
        this.add(this.jugador);
        if(!this.control.getJugador().getCartas().isEmpty()){
            Carta c = this.control.getJugador().getCartas().get(0);
            if(c.getTipo().equals("Normal"))
                this.mostrador = new Imagen(90, 130, c.getColor() + "_" + c.getNumero() + ".png");
        }else
            this.mostrador = new Imagen(90, 130, "");
        this.mostrador.setLocation(660, 490);
        this.add(this.mostrador);
        
        this.b1 = new JButton("Next");
        this.b1.setBounds(620, 625, 60, 20);
        this.b1.addActionListener(this);
        this.add(this.b1);
        this.b2 = new JButton("Tirar");
        this.b2.setBounds(770, 505, 80, 20);
        this.b2.addActionListener(this);
        this.add(this.b2);
        this.b3 = new JButton("Agarrar");
        this.b3.setBounds(770, 545, 80, 20);
        this.b3.addActionListener(this);
        this.add(this.b3);
        this.b4 = new JButton("Pasar");
        this.b4.setBounds(770, 585, 80, 20);
        this.b4.addActionListener(this);
        this.add(this.b4);
        
        this.color1 = new JButton();
        this.color1.setBackground(Color.yellow);
        this.color1.setBounds(480, 420, 40, 40);
        this.color1.setVisible(false);
        this.color1.addActionListener(this);
        this.add(this.color1);
        this.color2 = new JButton();
        this.color2.setBackground(Color.blue);
        this.color2.setBounds(540, 420, 40, 40);
        this.color2.setVisible(false);
        this.color2.addActionListener(this);
        this.add(this.color2);
        this.color3 = new JButton();
        this.color3.setBackground(Color.red);
        this.color3.setBounds(600, 420, 40, 40);
        this.color3.setVisible(false);
        this.color3.addActionListener(this);
        this.add(this.color3);
        this.color4 = new JButton();
        this.color4.setBackground(Color.green);
        this.color4.setBounds(660, 420, 40, 40);
        this.color4.setVisible(false);
        this.color4.addActionListener(this);
        this.add(this.color4);
        
        this.imagenFondo = new Imagen(this.getWidth() - 5, this.getHeight() - 50, "Mesa.png");
        this.imagenFondo.setLocation(0, 25);
        this.add(this.imagenFondo);
    }
    
    public Control getControl(){
        return this.control;
    }
    
    public void initClient(){
        try{
            this.socket = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
            DataOutputStream salida = new DataOutputStream(this.socket.getOutputStream());
            salida.writeUTF(this.control.getJugador().getNombre());
            
            this.Llenar_Todo();
            this.Actualizar_Todo();
            this.Mostrar();
            
            ClienteAux aux = new ClienteAux(this, this.socket);
            aux.start();
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    
    public void Mostrar(){
        this.control.getJugador().Mostrar();
        System.out.println("Tamaño de la baraja: " + this.control.getBaraja().size());
        System.out.println("Tamaño de la botada: " + this.control.getBotada().size());
    }
    
    private void Llenar_Todo(){
        this.control.getBaraja().clear();
        this.control.getBotada().clear();
        this.control.getOponentes().clear();
        this.control.getJugador().getCartas().clear();
        try{
            DataInputStream entrada = new DataInputStream(this.socket.getInputStream());
            String aux = entrada.readUTF();
            if(aux.equals("Baraja"))
                this.Llenar_Lista_Carta(this.control.getBaraja());
            aux = entrada.readUTF();
            if(aux.equals("Jugador"))
                this.Llenar_Lista_Carta(this.control.getJugador().getCartas());
            aux = entrada.readUTF();
            if(aux.equals("Oponente"))
                this.Llenar_Oponentes();
            aux = entrada.readUTF();
            if(aux.equals("Botada"))
                this.Llenar_Lista_Carta(this.control.getBotada());
            aux = entrada.readUTF();
            if(aux.equals("Turno"))
                this.Establecer_Turno();
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    public void Establecer_Turno(){
        try{
            DataInputStream entrada = new DataInputStream(this.socket.getInputStream());
            boolean aux = entrada.readBoolean();
            if(aux){
                this.b2.setEnabled(true);
                this.b3.setEnabled(true);
                this.b4.setEnabled(true);
            }else{
                this.b2.setEnabled(false);
                this.b3.setEnabled(false);
                this.b4.setEnabled(false);
            }
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    public void Llenar_Oponentes(){
        try{
            DataInputStream entrada = new DataInputStream(this.socket.getInputStream());
            int cantidad = entrada.readInt();
            String nombre;
            int cantidadC;
            while(cantidad > 0){
                nombre = entrada.readUTF();
                cantidadC = entrada.readInt();
                Oponente o = new Oponente(nombre, cantidadC);
                this.control.getOponentes().add(o);
                cantidad--;
            }
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
        this.l1.setText(this.control.getOponentes().get(0).getNombre());
        this.l2.setText(this.control.getOponentes().get(1).getNombre());
        this.l3.setText(this.control.getOponentes().get(2).getNombre());
    }
    public void Llenar_Lista_Carta(ArrayList<Carta> l){
        try{
            DataInputStream entrada = new DataInputStream(this.socket.getInputStream());
            int cantidad = entrada.readInt();
            String tipo, color;
            int numero, efecto, codigo;
            while(cantidad > 0){
                tipo = entrada.readUTF();
                numero = entrada.readInt();
                efecto = entrada.readInt();
                codigo = entrada.readInt();
                color = entrada.readUTF();
                Carta c = new Carta(tipo, numero, efecto, codigo, color);
                l.add(c);
                cantidad--;
            }
        }catch(IOException e){
            System.out.println("Error Cliente: " + e.getMessage());
        }
    }
    
    public void Actualizar_Todo(){
        if(this.control.getJugador().getCartas().isEmpty())
            this.Actualizar_Mostrar_Carta(this.mostrador, null, 
                 this.control.getJugador().getCartas().isEmpty()); 
        else
            this.Actualizar_Mostrar_Carta(this.mostrador, 
                 this.control.getJugador().getCartas().get(this.control.getJugador().getNum_Carta_Sel()), 
                 this.control.getJugador().getCartas().isEmpty()); 
        this.Actualizar_Oponentes();
        this.Actualizar_Mostrar_Carta(this.botada, 
             this.control.getBotada().get(0), 
             this.control.getBotada().isEmpty()); 
        
        if(this.control.getJugador().getCartas().size() < 2){
            this.jugador.setDireccion("");
            this.jugador.repaint();
        }else if(this.control.getJugador().getCartas().size() > 1){
            this.jugador.setDireccion("Boca_Abajo.png");
            this.jugador.repaint();
        }
        if(this.control.getBaraja().isEmpty()){
            this.baraja.setDireccion("");
            this.baraja.repaint();
        }else if(!this.control.getBaraja().isEmpty()){
            this.baraja.setDireccion("Boca_Abajo.png");
            this.baraja.repaint();
        }
    }
    private void Actualizar_Mostrar_Carta(Imagen i, Carta c, boolean vacia){
        if(!vacia){
            if(c.getTipo().equals("Normal"))
                i.setDireccion(c.getColor() + "_" + c.getNumero() + ".png");
            else if(c.getEfecto() == 4 || c.getEfecto() == 5){
                if(c.getEfecto() == 4)
                    i.setDireccion("C_C.png");
                else
                    i.setDireccion("R4_CC.png");
            }else{
                if(c.getEfecto() == 1)
                    i.setDireccion(c.getColor() + "_R2.png");
                else if(c.getEfecto() == 2)
                    i.setDireccion(c.getColor() + "_ID.png");
                else
                    i.setDireccion(c.getColor() + "_PT.png");
            }
        }else
            i.setDireccion("");
        i.repaint();
    }
    private void Actualizar_Oponentes(){
        if(this.control.getOponentes().get(0).getCantidadC() <= 7)
            this.oponente1.setDireccion("Oponente_" + this.control.getOponentes().get(0).getCantidadC() + ".png");
        else
            this.oponente1.setDireccion("Oponente_M.png");
        
        if(this.control.getOponentes().get(1).getCantidadC() <= 7)
            this.oponente2.setDireccion("Oponente_" + this.control.getOponentes().get(1).getCantidadC() + ".png");
        else
            this.oponente2.setDireccion("Oponente_M.png");
        
        if(this.control.getOponentes().get(2).getCantidadC() <= 7)
            this.oponente3.setDireccion("Oponente_" + this.control.getOponentes().get(2).getCantidadC() + ".png");
        else
            this.oponente3.setDireccion("Oponente_M.png");
        
        this.oponente1.repaint();
        this.oponente2.repaint();
        this.oponente3.repaint();
    }
    
    public void Determinar_Ganador(String nombre){
        if(nombre.equals(this.control.getJugador().getNombre())){
            this.b2.setEnabled(false);
            this.b3.setEnabled(false);
            this.b4.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Felicidades Ganaste", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }else
            JOptionPane.showMessageDialog(null, "Lo siento, el ganador es el jugador " + nombre, "Aviso", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void Abrir_Escoger_Color(boolean estado){
        if(estado){
            this.color1.setVisible(true);
            this.color2.setVisible(true);
            this.color3.setVisible(true);
            this.color4.setVisible(true);
            this.b2.setEnabled(false);
            this.b3.setEnabled(false);
            this.b4.setEnabled(false);
        }else{
            this.color1.setVisible(false);
            this.color2.setVisible(false);
            this.color3.setVisible(false);
            this.color4.setVisible(false);
            this.b2.setEnabled(true);
            this.b3.setEnabled(true);
            this.b4.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.b1){
            int n = this.control.getJugador().getNum_Carta_Sel();
            if(n < this.control.getJugador().getCartas().size() - 1)
                n++;
            else
                n = 0;
            this.control.getJugador().setNum_Carta_Sel(n);
            this.Actualizar_Mostrar_Carta(this.mostrador, 
                 this.control.getJugador().getCartas().get(this.control.getJugador().getNum_Carta_Sel()), 
                 this.control.getJugador().getCartas().isEmpty());            
        }else if(e.getSource() == this.b2){
            if(this.posiTirar){
                if(this.control.Tirar(this.socket)){
                    this.posiPasar = true;
                    this.posiAgarrar = false;
                    this.posiTirar = false;
                }else
                    JOptionPane.showMessageDialog(null, "No puede tirar esta carta", "Aviso", JOptionPane.WARNING_MESSAGE);
            }else    
                JOptionPane.showMessageDialog(null, "Ya tiraste una carta", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(e.getSource() == this.b3){
            if(this.posiAgarrar){
                if(!this.control.Agarrar(this.socket))
                    JOptionPane.showMessageDialog(null, "Usted tiene cartas para jugar", "Aviso", JOptionPane.WARNING_MESSAGE);
            }else
                JOptionPane.showMessageDialog(null, "Ya jugó", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(e.getSource() == this.b4){
            if(this.posiPasar){
                try{
                    DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                    salida.writeInt(4);
                }catch(IOException ex){
                    System.out.println("Error Cliente: " + ex.getMessage());
                }
                this.posiPasar = false;
                this.posiAgarrar = true;
                this.posiTirar = true;
            }else
                JOptionPane.showMessageDialog(null, "No has jugado, no puede pasar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(e.getSource() == this.color1){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(5);
                salida.writeUTF("Amarillo");
            }catch(IOException ex){
                System.out.println("Error Cliente: " + ex.getMessage());
            }
            this.Abrir_Escoger_Color(false);
        }else if(e.getSource() == this.color2){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(5);
                salida.writeUTF("Azul");
            }catch(IOException ex){
                System.out.println("Error Cliente: " + ex.getMessage());
            }
            this.Abrir_Escoger_Color(false);
        }else if(e.getSource() == this.color3){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(5);
                salida.writeUTF("Roja");
            }catch(IOException ex){
                System.out.println("Error Cliente: " + ex.getMessage());
            }
            this.Abrir_Escoger_Color(false);
        }else if(e.getSource() == this.color4){
            try{
                DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
                salida.writeInt(5);
                salida.writeUTF("Verde");
            }catch(IOException ex){
                System.out.println("Error Cliente: " + ex.getMessage());
            }
            this.Abrir_Escoger_Color(false);
        }
    }
}