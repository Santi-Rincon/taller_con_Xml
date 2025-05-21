package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class ShippingHandler extends DefaultHandler {

    private List<Shipping> shippingList ;
    private Shipping shipping ;
    private StringBuilder data ;

    public List<Shipping> getShippingList() {
        return shippingList;
    }

    boolean bId = false;
    boolean bNameTransport = false;
    boolean bStatus = false;
    boolean bDate = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    
        if (shippingList == null) {
            shippingList = new ArrayList<>();
        }
     else if (qName.equalsIgnoreCase("id")) {
        bId = true;
    } else if (qName.equalsIgnoreCase("nameTransport")) {
        bNameTransport = true;
    } else if (qName.equalsIgnoreCase("status")) {
        bStatus = true;
        } else if (qName.equalsIgnoreCase("date")) {
            bDate = true;
        }

        data = new StringBuilder();
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (bId) {
            shipping.setId(Integer.parseInt(data.toString()));
            bId = false;
        } else if (bNameTransport) {
            shipping.setNameTransport(data.toString());
            bNameTransport = false;
        } else if (bStatus) {
            shipping.setStatus(data.toString());
            bStatus = false;
        } else if (bDate) {
            shipping.setDate(data.toString());
            bDate = false;
        }  

        if (qName.equalsIgnoreCase("shipping")) {
            shippingList.add(shipping);
        }
    }

    
}
