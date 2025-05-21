package co.edu.uptc.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class XMLParserSAXOrder {

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            OrderHandler handler = new OrderHandler();
            
            saxParser.parse(new File("src\\data\\orders.xml"), handler);

            // Obtener lista de productos
            List<Order> orderList = handler.getOrderList();

            // Imprimir información
            for (Order order : orderList) {
                System.out.println(order.getOrderId() + " - " + 
                                   order.getCreationDate() + " - " +
                                   order.getStatus() + " - " +
                                   order.getCustomerId() + " - " +
                                   order.getProductIds());                                  
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
