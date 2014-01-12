/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package databank;

import java.util.ArrayList;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import speltest.Speler;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author wouter
 */
public class XMLDatabank implements Databank {

    @Override
    public ArrayList<String> leesScores() {
        ArrayList<String> lijst = new ArrayList<String>();
        try {

            File stocks = new File("players.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(stocks);
            doc.getDocumentElement().normalize();

            NodeList nodes = doc.getElementsByTagName("player");

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    lijst.add(getValue("name", element) + ": " + getValue("score", element));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lijst;
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }

    @Override
    public void schrijfScoreWeg(Speler p) {
        
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("players");
            doc.appendChild(rootElement);

            Element staff = doc.createElement("player");
            rootElement.appendChild(staff);

            Element firstname = doc.createElement("name");
            firstname.appendChild(doc.createTextNode(p.getNaam()));
            staff.appendChild(firstname);

            Element lastname = doc.createElement("score");
            lastname.appendChild(doc.createTextNode("" + p.getScore()));
            staff.appendChild(lastname);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("players.xml"));
            transformer.transform(source, result);

        } catch (ParserConfigurationException ex) {
        } catch (TransformerConfigurationException ex) {
        } catch (TransformerException ex) {
        }


    }
}
