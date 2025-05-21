package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OrderHandler extends DefaultHandler{

    private List<Order> orderList;
    private Order order;
    private List<String> productIds;
    private StringBuilder data;

    public List<Order> getOrderList() {
        return orderList;
    }

    boolean bOrderId = false;
    boolean bCreationDate = false;
    boolean bStatus = false;
    boolean bCustomerId = false;
    boolean bProductIds = false;
    boolean bProductId = false;

     @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("order")) {
            order = new Order();
            productIds = new ArrayList<>();

            if (orderList == null) {
                orderList = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("orderId")) {
            bOrderId = true;
        } else if (qName.equalsIgnoreCase("creationDate")) {
            bCreationDate = true;
        } else if (qName.equalsIgnoreCase("status")) {
            bCreationDate = true;
        } else if (qName.equalsIgnoreCase("customerId")) {
            bCustomerId = true;
        } else if (qName.equalsIgnoreCase("productIds")) {
            bProductIds = true;
        } else if (qName.equalsIgnoreCase("productId")) {
            bProductId = true;
        }            


        data = new StringBuilder();
    }

     @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "orderId":
                order.setOrderId(data.toString());
                break;
            case "creationDate":
                order.setCreationDate(data.toString());
                break;
            case "status":
                order.setStatus(data.toString());
                break;
            case "customerId":
                order.setCustomerId(data.toString());
                break;
            case "productIds":
                order.setProductIds(productIds);
                break;
            case "productId":
                productIds.add(data.toString());
                break;
            case "order":
                orderList.add(order);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

}
