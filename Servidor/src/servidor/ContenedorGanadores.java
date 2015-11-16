
package servidor;

import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ContenedorGanadores implements SerializableXML{
    private ArrayList<JugadorGanador> ganadores;

    
    public static final String ETQ_JUGADORES_GANADORES = "JugadoresGanadores";


    public ArrayList<JugadorGanador> getGanadores() {
        return ganadores;
    }
    

    public ContenedorGanadores() {
        ganadores =  new ArrayList<>();
        
    }
    public void agregarGanador(String nickname, int tiempo){
        JugadorGanador newGanador = new JugadorGanador(tiempo, nickname);
        ganadores.add(newGanador);
    }
    public int obtenerNumeroGanadores(){
        return ganadores.size();
    }

    public JugadorGanador ObtenerGanadores(int index){
        return ganadores.get(index);
    }
    public void ordenarPortiempo(){
        
    }

    @Override
    public Element guardarXML(Document documentoXML) {
        Element elementoPersonas = documentoXML.createElement(ETQ_JUGADORES_GANADORES);
        for(JugadorGanador ganador : ganadores){
            elementoPersonas.appendChild(ganador.guardarXML(documentoXML));
        }
        return elementoPersonas;
    }

    @Override
    public void leerXML(Node nodo) {
//        NodeList arbolEtiquetas  = nodo.getChildNodes();
//        int numPersonas = arbolEtiquetas.getLength();
//        for (int i = 0; i < numPersonas; i++) {
//            Node etiquetaPersona = arbolEtiquetas.item(i);
//            Persona newPersona = new Persona();
//            newPersona.leerXML(etiquetaPersona);
//            personas.add(newPersona);
//        }
    }
}
