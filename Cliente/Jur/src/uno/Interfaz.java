package uno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Interfaz extends JFrame implements ActionListener{
    private JMenuBar barra;
    private Imagen jugador1, jugador2, jugador3, jugador4, baraja, botada, mostrador;
    private Control control;
    private JButton b1, b2, b3, b4; 
    private boolean posiTirar, posiPasar, posiAgarrar;
    private JLabel l1, l2, l3;
    
    public Interfaz(){
        super("Uno");
        this.posiPasar = false;
        this.posiTirar = true;
        this.posiAgarrar = true;
        this.setSize(800,700);
        this.setLayout(null);
        int turno = (int) Math.round((Math.random() * 3));
        this.control = new Control(turno + 1);
        this.control.Comenzar_Juego();
        this.Inicial_Menu();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.control.Jugar();
        this.Actualizar_Botada();
        this.Actualizar_Jugadores();
    }
    
    private void Inicial_Menu(){
        this.barra = new JMenuBar();
        this.barra.setBounds(0, 0, this.getWidth(), 25);
        this.add(this.barra);
        
        this.jugador2 = new Imagen(90, 130, "Boca_Abajo.png");
        this.jugador2.setLocation(550, 50); 
        this.add(this.jugador2);
        this.jugador3 = new Imagen(90, 130, "Boca_Abajo.png");
        this.jugador3.setLocation(350, 50);
        this.add(this.jugador3);
        this.jugador4 = new Imagen(90, 130, "Boca_Abajo.png");
        this.jugador4.setLocation(150, 50);
        this.add(this.jugador4);
        
        this.l1 = new JLabel();
        this.l1.setBounds(640, 180, 40, 25);
        this.add(this.l1);
        this.l2 = new JLabel();
        this.l2.setBounds(440, 180, 40, 25);
        this.add(this.l2);
        this.l3 = new JLabel();
        this.l3.setBounds(240, 180, 40, 25);
        this.add(this.l3);
        
        this.baraja = new Imagen(70, 110, "Boca_Abajo.png");
        this.baraja.setLocation(320, 270);
        this.add(this.baraja);
        if(!this.control.getBotada().isEmpty()){
            Carta c = this.control.getBotada().get(0);
            if(c.getTipo().equals("Normal"))
                this.botada = new Imagen(70, 110, c.getColor() + "_" + c.getNumero() + ".png");
            else
                this.botada = new Imagen(70, 110, c.getColor() + "_ID.png");
        }else
            this.botada = new Imagen(70, 110, "");
        this.botada.setLocation(400, 290);
        this.add(this.botada);
        
        this.jugador1 = new Imagen(90, 130, "Boca_Abajo.png");
        this.jugador1.setLocation(320, 450);
        this.add(this.jugador1);
        if(!this.control.getJugador1().getCartas().isEmpty()){
            Carta c = this.control.getJugador1().getCarta(0);
            if(c.getTipo().equals("Normal"))
                this.mostrador = new Imagen(90, 130, c.getColor() + "_" + c.getNumero() + ".png");
            else if(c.getEfecto() == 4 || c.getEfecto() == 5){
                if(c.getEfecto() == 4)
                    this.mostrador = new Imagen(90, 130, "C_C.png");
                else
                    this.mostrador = new Imagen(90, 130, "R4_CC.png");
            }else{
                if(c.getEfecto() == 1)
                    this.mostrador = new Imagen(90, 130, c.getColor() + "_R2.png");
                else if(c.getEfecto() == 2)
                    this.mostrador = new Imagen(90, 130, c.getColor() + "_ID.png");
                else
                    this.mostrador = new Imagen(90, 130, c.getColor() + "_PT.png");
            }
        }else
            this.mostrador = new Imagen(90, 130, "");
        this.mostrador.setLocation(430, 450);
        this.add(this.mostrador);
        
        this.b1 = new JButton("Next");
        this.b1.setBounds(390, 590, 60, 20);
        this.b1.addActionListener(this);
        this.add(this.b1);
        this.b2 = new JButton("Tirar");
        this.b2.setBounds(280, 620, 80, 20);
        this.b2.addActionListener(this);
        this.add(this.b2);
        this.b3 = new JButton("Agarrar");
        this.b3.setBounds(380, 620, 80, 20);
        this.b3.addActionListener(this);
        this.add(this.b3);
        this.b4 = new JButton("Pasar");
        this.b4.setBounds(480, 620, 80, 20);
        this.b4.addActionListener(this);
        this.add(this.b4);
    }
    
    private void Actualizar_Botada(){
        if(!this.control.getBotada().isEmpty()){
            Carta c = this.control.getBotada().get(0);
            if(c.getTipo().equals("Normal"))
                this.botada.setDireccion(c.getColor() + "_" + c.getNumero() + ".png");
            else if(c.getEfecto() == 4 || c.getEfecto() == 5){
                if(c.getEfecto() == 4)
                    this.botada.setDireccion("C_C.png");
                else
                    this.botada.setDireccion("R4_CC.png");
            }else{
                if(c.getEfecto() == 1)
                    this.botada.setDireccion(c.getColor() + "_R2.png");
                else if(c.getEfecto() == 2)
                    this.botada.setDireccion(c.getColor() + "_ID.png");
                else
                    this.botada.setDireccion(c.getColor() + "_PT.png");
            }
        }else
            this.botada.setDireccion("");
        this.botada.repaint();
    }
    
    private void Actualizar_Mostrador(int n){
        if(!this.control.getJugador1().getCartas().isEmpty()){
            Carta c = this.control.getJugador1().getCarta(n);
            if(c.getTipo().equals("Normal"))
                this.mostrador.setDireccion(c.getColor() + "_" + c.getNumero() + ".png");
            else if(c.getEfecto() == 4 || c.getEfecto() == 5){
                if(c.getEfecto() == 4)
                    this.mostrador.setDireccion("C_C.png");
                else
                    this.mostrador.setDireccion("R4_CC.png");
            }else{
                if(c.getEfecto() == 1)
                    this.mostrador.setDireccion(c.getColor() + "_R2.png");
                else if(c.getEfecto() == 2)
                    this.mostrador.setDireccion(c.getColor() + "_ID.png");
                else
                    this.mostrador.setDireccion(c.getColor() + "_PT.png");
            }
        }else
            this.mostrador.setDireccion("");
        this.mostrador.repaint();
    }
    
    private void Actualizar_Jugadores(){
        this.l1.setText(String.valueOf(this.control.getJugador2().getCartas().size()));
        this.l2.setText(String.valueOf(this.control.getJugador3().getCartas().size()));
        this.l3.setText(String.valueOf(this.control.getJugador4().getCartas().size()));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.b1){
            int n = this.control.getJugador1().getNum_Carta_Sel();
            if(n < this.control.getJugador1().getCartas().size() - 1)
                n++;
            else
                n = 0;
            this.control.getJugador1().setNum_Carta_Sel(n);
            this.Actualizar_Mostrador(n);            
        }else if(e.getSource() == this.b2){
            if(this.posiTirar){
                if(this.control.Tirar()){
                    this.Actualizar_Botada();
                    this.Actualizar_Mostrador(this.control.getJugador1().getNum_Carta_Sel());
                    if(this.control.getJugador1().getCartas().size() < 2){
                        this.jugador1.setDireccion("");
                        this.jugador1.repaint();
                    }
                    if(this.control.getGanador() != 0)
                        JOptionPane.showMessageDialog(null, "Ganador fue jugador" + this.control.getGanador(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    this.posiTirar = false;
                    this.posiPasar = true;
                    this.posiAgarrar = false;
                }else
                    JOptionPane.showMessageDialog(null, "No puede tirar esta carta", "Aviso", JOptionPane.WARNING_MESSAGE);
            }else    
                JOptionPane.showMessageDialog(null, "Ya tiraste una carta", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(e.getSource() == this.b3){
            if(this.posiAgarrar){
                if(this.control.Agarrar()){
                    this.Actualizar_Mostrador(this.control.getJugador1().getNum_Carta_Sel());
                    if(this.control.getJugador1().getCartas().size() >= 2){
                        this.jugador1.setDireccion("Boca_Abajo.png");
                        this.jugador1.repaint();
                    }
                }else
                    JOptionPane.showMessageDialog(null, "Usted tiene cartas para jugar", "Aviso", JOptionPane.WARNING_MESSAGE);
            }else
                JOptionPane.showMessageDialog(null, "Ya jugó", "Aviso", JOptionPane.WARNING_MESSAGE);
        }else if(e.getSource() == this.b4){
            if(this.posiPasar){
                this.control.Cambiar_Turno();
                this.control.Jugar();
                if(this.control.getGanador() != 0)
                    JOptionPane.showMessageDialog(null, "Ganador fue jugador" + this.control.getGanador(), "Aviso", JOptionPane.INFORMATION_MESSAGE);
                this.Actualizar_Botada();
                this.Actualizar_Jugadores();
                this.posiPasar = false;
                this.posiTirar = true;
                this.posiAgarrar = true;
            }else
                JOptionPane.showMessageDialog(null, "No has jugado, no puede pasar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
}