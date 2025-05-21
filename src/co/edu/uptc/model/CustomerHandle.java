package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class CustomerHandle extends DefaultHandler {

    private Customer customer = null;
    private List<Customer> customerList = null;
    private StringBuilder data = null;

    // Get lista de productos
    public List<Customer> getCustomerList() {
        return customerList;
    }

    boolean bid = false;
    boolean baddress = false;
    boolean bphone = false;
    boolean bemail = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        data = new StringBuilder();

        if (qName.equalsIgnoreCase("customer")) {
            customer = new Customer();
        } else if (qName.equalsIgnoreCase("id")) {
            bid = true;
        } else if (qName.equalsIgnoreCase("address")) {
            baddress = true;
        } else if (qName.equalsIgnoreCase("phone")) {
            bphone = true;
        } else if (qName.equalsIgnoreCase("email")) {
            bemail = true;
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(bid){
            customer.setId(data.toString());
            bid = false;
        }else if(baddress){
            customer.setAddress(data.toString());
            baddress = false;
        }else if(bphone){
            customer.setPhone(data.toString());
            bphone = false;
        }else if(bemail){
            customer.setEmail(data.toString());
            bemail = false;
        }
        
        if (qName.equalsIgnoreCase("customer")) {
            customerList.add(customer);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }
    
}
