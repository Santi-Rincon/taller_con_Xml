package co.edu.uptc.model;

import java.util.Scanner;

public class CrearPorTeclado {

    public CrearPorTeclado() {
        // Constructor vacío
    }

    public void iniciarMenu() {
        int opcion = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("===== Menú Crear =====");
            System.out.println("1. Crear productos");
            System.out.println("2. Crear envíos");
            System.out.println("3. Crear clientes");
            System.out.println("4. Crear pedidos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    ProductXMLUpdater productUpdater = new ProductXMLUpdater();
                    productUpdater.agregarProductosDesdeTeclado();
                    break;

                case 2:
                    System.out.println("Funcionalidad de envíos aún no implementada.");
                    break;

                case 3:
                    CustomerXMLUpdater customerUpdater = new CustomerXMLUpdater();
                    customerUpdater.agregarCustomersDesdeTeclado();
                    break;

                case 4:
                    System.out.println("Funcionalidad de pedidos aún no implementada.");
                    break;

                case 5:
                    System.out.println("Saliendo del Menú Crear...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        
    }
}
