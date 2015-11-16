package servidor;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author Sergio Villegas Rodriguez
 */
public class JugadorGanador implements SerializableXML {

    private int tiempo; //segundos
    private String nickName;
    private static String ETQ_JUGADOR_GANADOR = "JugadorGanador";
    private static String ETQ_NICKNAME = "nickname";
    private static String ETQ_TIEMPO = "tiempo";

    public JugadorGanador(int tiempo, String nickName) {
        this.tiempo = tiempo;
        this.nickName = nickName;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "\nJugadorGanador{" + "tiempo=" + tiempo
                + "\n, nickName=" + nickName + '}';
    }

    @Override
    public Element guardarXML(Document documentoXML) {
        System.out.println("Guardanto el ganadror" + nickName);
        Element elementoPersona = documentoXML.createElement(ETQ_JUGADOR_GANADOR);
        elementoPersona.setAttribute(ETQ_NICKNAME, nickName);
        elementoPersona.setAttribute(ETQ_TIEMPO, Integer.toString(tiempo));
        return elementoPersona;
    }

    @Override
    public void leerXML(Node nodo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
