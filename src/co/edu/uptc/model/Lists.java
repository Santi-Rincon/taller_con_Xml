package co.edu.uptc.model;

import java.util.List;

public class Lists {

    // Arreglos de cada tipo de entidad
    private Customer[] customers;
    private Order[] orders;
    private Product[] products;
    private Shipping[] shippings;

    public Lists() {
        // Inicializamos los arreglos con tamaños arbitrarios como ejemplo (puedes cambiar los tamaños según necesidad)
        customers = new Customer[10];
        orders = new Order[10];
        products = new Product[10];
        shippings = new Shipping[10];

        // Ejemplo de inicialización de un Customer
        customers[0] = new Customer();
        customers[0].setId("C001");
        customers[0].setAddress("Cra 10 #20-30");
        customers[0].setPhone("3123456789");
        customers[0].setEmail("cliente1@correo.com");

        // Ejemplo de inicialización de un Product
        products[0] = new Product();
        products[0].setProductId("P001");
        products[0].setDescription("Pan");
        products[0].setPresentation("Paquete 500g");

        // Ejemplo de inicialización de un Order
        orders[0] = new Order();
        orders[0].setOrderId("O001");
        orders[0].setCreationDate("2025-05-21");
        orders[0].setStatus("Processing");
        orders[0].setCustomerId("C001");
        orders[0].setProductIds(List.of("P001")); // necesitas importar java.util.List

        // Ejemplo de inicialización de un Shipping
        shippings[0] = new Shipping();
        shippings[0].setId(1);
        shippings[0].setNameTransport("Servientrega");
        shippings[0].setStatus("En camino");
        shippings[0].setDate("2025-05-22");
    }

    // Getters para acceder a los arreglos desde otras clases si es necesario

    public Customer[] getCustomers() {
        return customers;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Product[] getProducts() {
        return products;
    }

    public Shipping[] getShippings() {
        return shippings;
    }
}