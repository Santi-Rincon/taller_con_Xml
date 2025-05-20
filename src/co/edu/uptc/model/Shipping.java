package co.edu.uptc.model;

public class Shipping {

    private int id;
    private String nameTransport;
    private String status;
    private String date;
    
    @Override
    public String toString() {
        return "Shipping [id=" + id + ", nameTransport=" + nameTransport + ", status=" + status + ", date=" + date
                + "]";
    }

    public Shipping() {
    }

    //hola
    
    public Shipping(int id, String nameTransport, String status, String date) {
        this.id = id;
        this.nameTransport = nameTransport;
        this.status = status;
        this.date = date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNameTransport() {
        return nameTransport;
    }
    public void setNameTransport(String nameTransport) {
        this.nameTransport = nameTransport;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


}
