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

    private SAXParser saxParser;

    public XMLParserSAX() throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        this.saxParser = saxParserFactory.newSAXParser();
    }

    public void mostrarMenuLeer() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        try {
            do {
                System.out.println();
                System.out.println("===== MENÚ LEER =====");
                System.out.println("1. Ver productos");
                System.out.println("2. Ver envíos");
                System.out.println("3. Ver clientes");
                System.out.println("4. Ver pedidos");
                System.out.println("5. Volver");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        ProductHandler productHandler = new ProductHandler();
                        saxParser.parse(new File("src/data/products.xml"), productHandler);
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
                        saxParser.parse(new File("src/data/shippings.xml"), shippingHandler);
                        List<Shipping> shippingList = shippingHandler.getShippingList();

                        System.out.println("\n--- Lista de Envíos ---");
                        for (Shipping shipping : shippingList) {
                            System.out.println(shipping.getId() + " - " +
                                    shipping.getNameTransport() + " - " +
                                    shipping.getStatus() + " - " +
                                    shipping.getDate());
                        }
                        break;

                    case 3:
                        CustomerHandle customerHandler = new CustomerHandle();
                        saxParser.parse(new File("src/data/customer.xml"), customerHandler);
                        List<Customer> customerList = customerHandler.getCustomerList();

                        System.out.println("\n--- Lista de Clientes ---");
                        for (Customer customer : customerList) {
                            System.out.println(customer.getId() + " - " +
                                    customer.getAddress() + " - " +
                                    customer.getPhone() + " - " +
                                    customer.getEmail());
                        }
                        break;

                    case 4:
                        OrderHandler orderHandler = new OrderHandler();
                        saxParser.parse(new File("src/data/orders.xml"), orderHandler);
                        List<Order> orderList = orderHandler.getOrderList();

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
                        System.out.println("Volviendo al menú principal...");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            } while (opcion != 5);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}
