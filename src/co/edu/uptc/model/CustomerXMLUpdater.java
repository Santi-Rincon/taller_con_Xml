package co.edu.uptc.model;

import java.io.File;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class CustomerXMLUpdater {
    
    private File xmlFile = new File("src/data/customer.xml");

    public void agregarCustomersDesdeTeclado() {

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
                root = doc.createElement("customers");
                doc.appendChild(root);
            }

            System.out.println("¿Cuántos clientes desea agregar?: ");
            int cantidad = scaner.nextInt();
            scaner.nextLine(); // limpiar buffer

            for (int i = 0; i < cantidad; i++) {
                System.out.println("\nCliente " + (i + 1) + ":");

                Element customer = doc.createElement("customer");

                System.out.print("ID del cliente: ");
                String id = scaner.nextLine();

                System.out.print("Dirección: ");
                String address = scaner.nextLine();

                System.out.print("Teléfono: ");
                String phone = scaner.nextLine();

                System.out.print("Email: ");
                String email = scaner.nextLine();

                customer.setAttribute("id", id);

                Element addrElem = doc.createElement("address");
                addrElem.appendChild(doc.createTextNode(address));

                Element phoneElem = doc.createElement("phone");
                phoneElem.appendChild(doc.createTextNode(phone));

                Element emailElem = doc.createElement("email");
                emailElem.appendChild(doc.createTextNode(email));

                customer.appendChild(addrElem);
                customer.appendChild(phoneElem);
                customer.appendChild(emailElem);

                root.appendChild(customer);
            }

            removeWhitespaceNodes(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);            

            System.out.println("Clientes agregados correctamente.");

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
