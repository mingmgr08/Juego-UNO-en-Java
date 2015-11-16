
package cliente.Vista;

import cliente.Control.Control;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Kvn
 */
public class LogIn extends JFrame implements Observer{
    public LogIn(Control nuevoGestor){
        super("UNO: Registro");
        gestorPrincipal = nuevoGestor;
        this.setSize(300,180);
        this.setLayout(null);
        Container contenedorPorDefecto = this.getContentPane();
        ajustarComponentes(contenedorPorDefecto);
        
        ajustarConfiguracionInicial();
        mostrar();

        
    }
    private void ajustarComponentes(Container c){
        LayoutManager LayoutFlow= new FlowLayout(FlowLayout.LEFT);
        c.setLayout(LayoutFlow);
        Container contenedorCental = new Container();
        contenedorCental.setSize(c.getSize());
        
        LayoutManager LayoutGridBag=new GridBagLayout();
        GridBagConstraints gbc=new GridBagConstraints();
        contenedorCental.setLayout(LayoutGridBag);
        
        a=new JTextField();
        b=new JLabel("Digite su Nombre de Usuario");
        name=new JButton("ENTRAR");
        
        
        gbc.gridx=0; //
        gbc.gridy=0;//
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=2;
        gbc.fill= GridBagConstraints.CENTER;
        gbc.insets=new Insets(20,50,0,0);
        contenedorCental.add(b,gbc);
        
        gbc.gridx=0; //
        gbc.gridy=1;//
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=2;
        gbc.fill= GridBagConstraints.CENTER;
        gbc.ipadx=200;
        gbc.ipady=5;
        gbc.insets=new Insets(10, 40,0,0);
        
        contenedorCental.add(a,gbc);
        
        gbc.gridx=0; //
        gbc.gridy=2;//
        gbc.gridheight=1;//Cantidad de celdas que ocupara verticalmente
        gbc.gridwidth=1;
        gbc.ipadx=80;
        gbc.ipady=5;
        gbc.fill= GridBagConstraints.CENTER;
        gbc.insets=new Insets(10, 60,0,0);
        contenedorCental.add(name,gbc);
        
        
        
        
        c.add(contenedorCental);
        
    }
    private void ajustarConfiguracionInicial(){
        getContentPane().setBackground(Color.WHITE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        name.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre=a.getText();
                setVisible(false);
                Preparacion p=new Preparacion(gestorPrincipal);
                p.nombre(nombre);
                
                p.mostrar();
            }
        });
        
        
        
    }
    public void mostrar() {
        setVisible(true); 
    }
    
    private JButton name;
    private JTextField a;
    private JLabel b;
    Control gestorPrincipal;

    @Override
    public void update(Observable o, Object arg) {
    }
}
