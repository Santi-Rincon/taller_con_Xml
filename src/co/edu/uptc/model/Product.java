package co.edu.uptc.model;
public class Product {
    private String productId;
    private String description;
    private String presentation;

    // Constructor
    public Product(String productId, String description, String presentation) {
        this.productId = productId;
        this.description = description;
        this.presentation = presentation;
    }

    // Default constructor
    public Product() {
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    // Optional: toString method for displaying product info
    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", description='" + description + '\'' +
                ", presentation='" + presentation + '\'' +
                '}';
    }
}
