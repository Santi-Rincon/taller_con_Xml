package co.edu.uptc.model;

import java.io.File;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class ShippingXMLupdater {
    
    File xmlFile = new File("src/data/shippings.xml");

    public void agregarShippingsDesdeTeclado() {

        Scanner scaner = new Scanner(System.in);

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element root;

            // Leer o crear documento
            if (xmlFile.exists()) {
                doc = builder.parse(xmlFile);
                root = doc.getDocumentElement(); // <products>
            } else {
                doc = builder.newDocument();
                root = doc.createElement("shippings");
                doc.appendChild(root);
            }

            System.out.println("¿Cuántos envíos desea agregar?: ");
            int cantidad = scaner.nextInt();            
            scaner.nextLine(); // limpiar buffer

            for (int i = 0; i < cantidad; i++) {
                System.out.println("\nEnvío " + (i + 1) + ":");

                Element shipping = doc.createElement("shipping");

                System.out.print("ID del envío: ");
                String id = scaner.nextLine();

                System.out.print("Nombre del transportista: ");
                String nameTransport = scaner.nextLine();

                System.out.print("Estado del envío: ");
                String status = scaner.nextLine();

                System.out.print("Fecha del envío: ");
                String date = scaner.nextLine();

                Element idElem = doc.createElement("id");
                idElem.appendChild(doc.createTextNode(id));

                Element nameTransportElem = doc.createElement("nameTransport");
                nameTransportElem.appendChild(doc.createTextNode(nameTransport));

                Element statusElem = doc.createElement("status");
                statusElem.appendChild(doc.createTextNode(status));

                Element dateElem = doc.createElement("date");
                dateElem.appendChild(doc.createTextNode(date));

                shipping.appendChild(idElem);
                shipping.appendChild(nameTransportElem);
                shipping.appendChild(statusElem);
                shipping.appendChild(dateElem);

                root.appendChild(shipping);
            }

            removeWhitespaceNodes(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);            

            System.out.println("Envíos agregados correctamente.");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void removeWhitespaceNodes(Element e) {
        NodeList children = e.getChildNodes();
        for (int i = children.getLength() - 1; i >= 0; i--) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE && child.getTextContent().trim().isEmpty()) {
                e.removeChild(child);
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                removeWhitespaceNodes((Element) child);
            }
        }
    }


}
