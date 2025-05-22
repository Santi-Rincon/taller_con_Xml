package co.edu.uptc.runner;


import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import co.edu.uptc.model.ProductXMLUpdater;
import co.edu.uptc.model.XMLParserSAX;

import java.util.Scanner;

public class Run {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        XMLParserSAX xmlParserSAX = null;
        ProductXMLUpdater productUpdater = new ProductXMLUpdater();

        try {
            xmlParserSAX = new XMLParserSAX();
        } catch (ParserConfigurationException | SAXException e) {
            System.out.println("Error inicializando XMLParserSAX");
            e.printStackTrace();
            System.exit(1);
        }

        int opcion = 0;
        do {
            System.out.println();
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Leer datos");
            System.out.println("2. Crear productos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    xmlParserSAX.mostrarMenuLeer();
                    break;

                case 2:
                    productUpdater.agregarProductosDesdeTeclado();
                    break;

                case 3:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 3);

        scanner.close();
    }
}

