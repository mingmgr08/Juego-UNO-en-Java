
package cliente.Vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author ville
 */
public class FrameHistorial extends JFrame implements Observer{
    public JTextArea areaHistorial;

    public JTextArea getAreaHistorial() {
        return areaHistorial;
        
    }
    
    public FrameHistorial() {
        super("Historial");
        configuracionInicial();
        iniciarComponentes();
        ajustarComponentes(getContentPane());
    }
    
    
    private void configuracionInicial(){
        Dimension d  = new Dimension(480,550);
        setSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setLocation(50,50);
        
        
    }
    private void iniciarComponentes(){
        
        areaHistorial = new JTextArea();
    }
    private void ajustarComponentes(Container c){
        c.setLayout(new BorderLayout());
        c.add(new JScrollPane(areaHistorial), BorderLayout.CENTER);
    }
    public void iniciar(){
        setVisible(true);
    }
    private void limpiarHistorial(){
        areaHistorial.setText("");
    }
    @Override
    public void update(Observable o, Object arg) {
        limpiarHistorial();
        areaHistorial.setText(null);
    }
//    public static void main(String [] args){
//        FrameHistorial mi = new FrameHistorial();
//        mi.iniciar();
//        
//    }
    
}
