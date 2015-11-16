
package servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ville
 */
public class VentanaServidor extends JFrame implements Observer{
    private JPanel panelTiempo;
    private JButton btnApagarServidor;
    private String tiempo;
    private Control controlPrincipal;

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
        panelTiempo.repaint();
    }
    
    
    public VentanaServidor(Control nuevoControl){
        super("SERVIDOR");
        controlPrincipal = nuevoControl;
        tiempo = "0";
        ajustarConfiguracionInicial();
        IniciarComponentes();
        ajustarComponetes(getContentPane());
        ajustarEventos();
        
    }
    private void ajustarConfiguracionInicial(){
        Dimension d = new Dimension(200, 200);
        setSize(d);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
    public void iniciar(){
        setVisible(true);
    }
    private void IniciarComponentes(){
        panelTiempo = new JPanel(){

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D gr = (Graphics2D)g;
                gr.setColor(Color.WHITE);
                gr.drawString("TIEMPO DE JUEGO", this.getWidth()/2 - 40, getHeight()/2 - 50 );
                gr.drawString( getTiempo() + " segundos", this.getWidth()/2 - 30, getHeight()/2 );
            }
        };
        panelTiempo.setBackground(Color.BLACK);
        btnApagarServidor = new JButton("Apagar Servidor");
        
    }
    private void ajustarComponetes(Container c){
        c.setLayout(new BorderLayout());
        c.add(panelTiempo,BorderLayout.CENTER);
        c.add(btnApagarServidor, BorderLayout.SOUTH);
    }
    private void ajustarEventos(){
        btnApagarServidor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        Long l = (Long)arg;
     
        System.out.println("TIEMPO EN EL UPDATE");
        setTiempo(l.toString());
    }
}
