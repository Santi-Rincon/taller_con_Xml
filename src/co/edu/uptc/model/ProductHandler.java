package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ProductHandler extends DefaultHandler {

    private List<Product> productList = null;
    private Product product = null;
    private StringBuilder data = null;

    // Get lista de productos
    public List<Product> getProductList() {
        return productList;
    }

    boolean bDescription = false;
    boolean bPresentation = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("product")) {
            String id = attributes.getValue("productId");
            product = new Product();
            product.setProductId(id);

            if (productList == null) {
                productList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("description")) {
            bDescription = true;
        } else if (qName.equalsIgnoreCase("presentation")) {
            bPresentation = true;
        }

        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (bDescription) {
            product.setDescription(data.toString());
            bDescription = false;
        } else if (bPresentation) {
            product.setPresentation(data.toString());
            bPresentation = false;
        }

        if (qName.equalsIgnoreCase("Product")) {
            productList.add(product);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
}