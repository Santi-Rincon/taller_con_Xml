package co.edu.uptc.model;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

public class ProductXMLUpdater {

    private File xmlFile = new File("src/data/products.xml");

    // Método para agregar productos desde teclado
    public void agregarProductosDesdeTeclado() {
        Scanner scanner = new Scanner(System.in);

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
                root = doc.createElement("products");
                doc.appendChild(root);
            }

            System.out.print("¿Cuántos productos desea agregar?: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            for (int i = 0; i < cantidad; i++) {
                System.out.println("\nProducto " + (i + 1) + ":");

                Element product = doc.createElement("product");

                System.out.print("ID del producto: ");
                String id = scanner.nextLine();

                System.out.print("Descripción: ");
                String description = scanner.nextLine();

                System.out.print("Presentación: ");
                String presentation = scanner.nextLine();

                product.setAttribute("productId", id);

                Element descElem = doc.createElement("description");
                descElem.appendChild(doc.createTextNode(description));

                Element presElem = doc.createElement("presentation");
                presElem.appendChild(doc.createTextNode(presentation));

                product.appendChild(descElem);
                product.appendChild(presElem);

                root.appendChild(product);
            }

            removeWhitespaceNodes(root);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(xmlFile);
            transformer.transform(source, result);

            System.out.println("Productos agregados correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Función para eliminar nodos de texto vacíos (sólo espacios y saltos)
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
