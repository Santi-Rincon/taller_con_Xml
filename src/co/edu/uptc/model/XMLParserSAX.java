package co.edu.uptc.model;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class XMLParserSAX {

    public static void main(String[] args) {
      
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            int opcion = 0;

            do { 
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("===== MENÚ =====");
                System.out.println("1. Ver productos");
                System.out.println("2. Ver envíos");
                System.out.println("3. Ver clientes");
                System.out.println("4. Ver pedidos");
                
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();

                switch (opcion) {
                case 1:
                    ProductHandler productHandler = new ProductHandler();
                    saxParser.parse(new File("src\\data\\products.xml"), productHandler);
                    List<Product> productList = productHandler.getProductList();

                    System.out.println("\n--- Lista de Productos ---");
                    for (Product product : productList) {
                        System.out.println(product.getProductId() + " - " +
                                           product.getDescription() + " - " +
                                           product.getPresentation());
                    }
                    break;

                case 2:
                    ShippingHandler shippingHandler = new ShippingHandler();
                    saxParser.parse(new File("src\\data\\shippings.xml"), shippingHandler);
                    List<Shipping> shippingList = shippingHandler.getShippingList();

                    System.out.println("\n--- Lista de Envíos ---");
                    for (Shipping shipping : shippingList) {
                        System.out.println(shipping.getId() + " - " +
                                           shipping.getNameTransport() + " - " +
                                           shipping.getStatus() + " - " +
                                           shipping.getDate());
                    }
                    break;


                case 4:
                    OrderHandler handler = new OrderHandler();
                    saxParser.parse(new File("src\\data\\orders.xml"), handler);
                    List<Order> orderList = handler.getOrderList();

                    System.out.println("\n--- Lista de Pedidos ---");
                    for (Order order : orderList) {
                        System.out.println(order.getOrderId() + " - " + 
                                        order.getCreationDate() + " - " +
                                        order.getStatus() + " - " +
                                        order.getCustomerId() + " - " +
                                        order.getProductIds());                                  
                    }
                    break;
                
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                    
            
                default:
                    System.out.println("Opción inválida.");
                }
            } while (opcion != 5);
            

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }
}