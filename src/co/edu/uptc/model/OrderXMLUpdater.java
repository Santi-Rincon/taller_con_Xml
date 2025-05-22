package co.edu.uptc.model;

import java.io.File;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class OrderXMLUpdater {

    File xmlFile = new File("src/data/orders.xml");

    public void agregarOrdersDesdeTeclado() {

        Scanner scaner = new Scanner(System.in);

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            Element root;

            // Leer o crear documento
            if (xmlFile.exists()) {
                doc = builder.parse(xmlFile);
                root = doc.getDocumentElement(); // <orders>
            } else {
                doc = builder.newDocument();
                root = doc.createElement("orders");
                doc.appendChild(root);
            }

            System.out.println("¿Cuántos pedidos desea agregar?: ");
            int cantidad = scaner.nextInt();            
            scaner.nextLine(); // limpiar buffer

            for (int i = 0; i < cantidad; i++) {
                System.out.println("\nPedido " + (i + 1) + ":");

                Element order = doc.createElement("order");

                System.out.print("ID del pedido: ");
                String id = scaner.nextLine();

                System.out.print("Fecha de creación: ");
                String creationDate = scaner.nextLine();

                System.out.print("Estado del pedido: ");
                String status = scaner.nextLine();

                System.out.print("ID del cliente: ");
                String customerId = scaner.nextLine();

                System.out.print("Productos (separados por coma): ");
                String productIds = scaner.nextLine();

                // Crear elementos XML
                Element idElem = doc.createElement("orderId");
                idElem.appendChild(doc.createTextNode(id));

                Element creationDateElem = doc.createElement("creationDate");
                creationDateElem.appendChild(doc.createTextNode(creationDate));

                Element statusElem = doc.createElement("status");
                statusElem.appendChild(doc.createTextNode(status));

                Element customerIdElem = doc.createElement("customerId");
                customerIdElem.appendChild(doc.createTextNode(customerId));

                Element productIdsElem = doc.createElement("productIds");

                String[] productos = productIds.split(",");
                for (String pid : productos) {
                    Element productIdElem = doc.createElement("productId");
                    productIdElem.appendChild(doc.createTextNode(pid.trim()));
                    productIdsElem.appendChild(productIdElem);
                }

                // Construir el nodo <order>
                order.appendChild(idElem);
                order.appendChild(creationDateElem);
                order.appendChild(statusElem);
                order.appendChild(customerIdElem);
                order.appendChild(productIdsElem);

                // Añadir el pedido al documento
                root.appendChild(order);
            }

            removeWhitespaceNodes(root);

            // Guardar los cambios en el archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);            

            System.out.println("Pedidos agregados correctamente.");

        } catch (Exception e) {
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
