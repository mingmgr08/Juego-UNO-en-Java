package cliente.Vista;

import cliente.Control.Control;
import cliente.Entidad.Jugador;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Kvn
 */
public class JTable_Jugadores extends JTable{
    
    public JTable_Jugadores(Control controlPrincipal) {
        this.controlPrincipal = controlPrincipal;
        setFont(new Font("SansSerif", Font.PLAIN, 12));
        ajustarTabla();
    }
    
    private void ajustarTabla() {
        jTablaSetModel();
        jTablaScroll();
        jTablaTamanoColumn();
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setResizingAllowed(false);
    }
    
    public void agregarAlContenedor(Container c) {
        c.add(BorderLayout.CENTER, scrollTablaRegistros);
    }
    
    
    private void jTablaSetModel() {
        System.out.println("Se ejecuta el tableSetModel");
        Object[][] datos = IMEC_Jugador.recuperarTodosJugadoresTablaRegistro(controlPrincipal);
        System.out.println("pasa1");
        String[] cabeceras = Jugador.NOMB_ATRIBUTOS;
        System.out.println("Cabeceras = " +cabeceras[0] + " "+cabeceras[1]);
        setModel(new javax.swing.table.DefaultTableModel(datos, cabeceras) {
            
            Class[] tipoColumn = Jugador.CLASS_ATRIBUTOS;
            boolean[] editColum = Jugador.BOOLEAN_ATRIBUTOS;
            

            @Override
            public Class getColumnClass(int indColum) {
                System.out.println("devuelve ColumnClass");
                return tipoColumn[indColum];
            }

            @Override
            public boolean isCellEditable(int indFila, int indColum) {
                return editColum[indColum];
            }
        });
    }
    
    private void jTablaScroll() {
        scrollTablaRegistros = new JScrollPane(this, 
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    
    
    private void jTablaTamanoColumn() {
        TableColumn colum;
        for (int i = 0; i < getColumnCount(); i++) {
            colum = getColumnModel().getColumn(i);
        }
    }
    public String getDatoFilaPlaca() {
        //String dato = (String)getValueAt(getSelectedRow(),0);
        String dato;
        dato = (String) getValueAt(getSelectedRow(),0);
        String datoNuevo = dato;
        Object datoFila = IMEC_Jugador.obtenerRegistro(datoNuevo, controlPrincipal);
        //System.out.println(datoFila);
        return datoNuevo;
    }
    public Object getDatoFila() {
        //String dato = (String)getValueAt(getSelectedRow(),0);
        String dato;
        dato = (String) getValueAt(getSelectedRow(),0);
        Object datoFila = IMEC_Jugador.obtenerRegistro(dato, controlPrincipal);
        //System.out.println(datoFila);
        return datoFila;
    }
    public void actualizarTabla(){
        jTablaSetModel();
        jTablaTamanoColumn();
    }
    
    
    
    private Control controlPrincipal = null;
    private JScrollPane scrollTablaRegistros;
    
}
