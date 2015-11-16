package cliente.Entidad;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel{
    private String direccion;
    
    public Imagen(int width, int height, String direccion){
        this.setSize(width,height);
        this.direccion = direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
    
    @Override
    public void paintComponent (Graphics g){
        if(this.direccion.equals("")){
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0,0, this.getWidth(), this.getHeight());
        }else{
            ImageIcon imagenFondo = new ImageIcon(this.direccion);
            g.drawImage(imagenFondo.getImage(),0,0,this.getWidth(), this.getHeight(), null);
        }
    }
}
