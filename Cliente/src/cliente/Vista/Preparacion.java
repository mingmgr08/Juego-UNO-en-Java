
package cliente.Vista;

import cliente.Control.Control;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

/**
 *
 * @author Kvn
 */
public class Preparacion extends JFrame implements Observer{
    public Preparacion(Control nuevoGestor){
        super("UNO: Preparacion del Juego");
        historialFrame =  new FrameHistorial();
        gestorPrincipal = nuevoGestor;
        this.setSize(1200,420);
        this.setLayout(null);
        Container contenedorPorDefecto = this.getContentPane();
        ajustarComponentes(contenedorPorDefecto);
        
        ajustarConfiguracionInicial();

        
    }
    private void ajustarConfiguracionInicial(){
        getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        empezar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Interfaz i = new Interfaz(name);
                i.initClient();
            }
        });
        salir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        historial.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                historialFrame.iniciar();
            }
        });
        
    }
    private void ajustarComponentes(Container c){
        LayoutManager LayoutFlow= new FlowLayout(FlowLayout.LEFT);
        c.setLayout(LayoutFlow);
        Container contenedorCental = new Container();
        contenedorCental.setSize(c.getSize());
        
        historial=new JButton("Historial de Victorias");
        empezar=new JButton("¡Estoy Listo Para Empezar!");
        salir=new JButton("Salir");
        name="";
        
        LayoutManager LayoutGridBag=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        contenedorCental.setLayout(LayoutGridBag);
        
        gbc.gridx=0; //
        gbc.gridy=0;//
        gbc.gridheight=3;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=1;
        gbc.ipadx = 590; //pixeles del largo de la celda
        gbc.ipady = 350; //pixeles del ancho de la celda
        gbc.fill= GridBagConstraints.HORIZONTAL; //
        gbc.insets=new Insets(10, 20,0,2);
        panelImagen = new JPanel(){ //Panel para la imagen
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawImage(imagenUno, 0, 0,this);
            }
        };
        contenedorCental.add(panelImagen,gbc);
        
        gbc.gridx=1; //numero columna
        gbc.gridy=0;//numero de fila
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=1;
        gbc.ipadx = 300; //pixeles del largo de la celda
        gbc.ipady = 40; //pixeles del ancho de la celda
        gbc.fill= GridBagConstraints.HORIZONTAL;
        gbc.insets=new Insets(45, 50,0,2);
        contenedorCental.add(historial,gbc);
        
        gbc.gridx=1; //numero columna
        gbc.gridy=1;//numero de fila
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=1;
        gbc.ipadx = 300; //pixeles del largo de la celda
        gbc.ipady = 40; //pixeles del ancho de la celda
        gbc.insets=new Insets(45, 50,0,2);
        contenedorCental.add(empezar,gbc);
        
        gbc.gridx=1; //numero columna
        gbc.gridy=2;//numero de fila
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=1;
        gbc.ipadx = 300; //pixeles del largo de la celda
        gbc.ipady = 40; //pixeles del ancho de la celda
        gbc.fill= GridBagConstraints.CENTER; //
        gbc.insets=new Insets(2, 50,0,2);
        contenedorCental.add(salir,gbc);
        
        /*contenedorTabla=new Container();
        tablaConectados=new JTable_Jugadores(gestorPrincipal);
        gbc.gridx=0; //numero columna
        gbc.gridy=3;//numero de fila
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=2;
        gbc.ipadx = 300; //pixeles del largo de la celda
        gbc.ipady = 40;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.7;
        gbc.insets=new Insets(10, 20,0,0);
        contenedorTabla.setLayout(new BorderLayout());
        tablaConectados.agregarAlContenedor(contenedorTabla);
        contenedorCental.add(contenedorTabla, gbc);*/   //TABLA
        
        c.add(contenedorCental,BorderLayout.CENTER);
    }
    
    
    public void mostrar() {
        try {
            imagenUno = ImageIO.read(getClass().getResourceAsStream(RUTA_IMAGEN_UNO));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        setVisible(true);
        panelImagen.repaint(); 
    }
    public void nombre(String name){
        this.name=name;
    }
    
    
    
    
    @Override
    public void update(Observable o, Object o1) {
        tablaConectados.actualizarTabla();
        
        System.out.println("update panel Jugadores");
    }
    
    private JButton historial;
    private JButton empezar;
    private JButton salir;
    
    private JTable_Jugadores tablaConectados;
    private JPanel panelImagen;
    
    private String name;
    private BufferedImage imagenUno;
    private static final String RUTA_IMAGEN_UNO = "Imagenes/UNO.jpg";
    
    private Control gestorPrincipal;
    private Container contenedorTabla;
    
    private FrameHistorial historialFrame;
    
    

    
    

}
