package servidor;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Sergio
 */
public class Serializable {

    public void guardarXMLFile(Node nodo, File file) {
        TransformerFactory transformadores = null;
        Transformer transformador;

        
        try {
            transformadores = TransformerFactory.newInstance();
            transformador = transformadores.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.setOutputProperty(OutputKeys.METHOD, "xml");
            DOMSource fileDom = new DOMSource(nodo);
            StreamResult resultado = new StreamResult(file);
            transformador.transform(fileDom, resultado);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void leerXMLFile(){
        
    }
    public Document crearXMLFile(File file){
        DocumentBuilderFactory constructores = null;
        DocumentBuilder constructor =  null;
        Document fileXML = null;
        
        
        try {
            constructores = DocumentBuilderFactory.newInstance();
            constructor = constructores.newDocumentBuilder();
            if(file == null){
                fileXML = constructor.newDocument();
                
            }else{
                fileXML = constructor.parse(file);
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serializable.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return fileXML;
    }
    public Document crearXMLFile(){
        return crearXMLFile(null);
    }
}
