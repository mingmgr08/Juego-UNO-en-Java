
package servidor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Sergio
 */
public interface SerializableXML {
    public Element guardarXML(Document documentoXML);
    public void leerXML(Node nodo);

    
}
    