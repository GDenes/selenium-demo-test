package sauce_demo.models;

import java.util.Objects;

/**
 * The Product class represents a product in the Sauce Demo application.
 * It contains information about the title, details, and price of a product.
 */
public class Product {

    private final String title;
    private final String details;
    private final String price;

    /**
     * Constructs a Product object with the specified title, details, and price.
     *
     * @param title   The title of the product.
     * @param details The details or description of the product.
     * @param price   The price of the product.
     */
    public Product(String title, String details, String price) {
        this.title = title;
        this.details = details;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(title, product.title) && Objects.equals(details, product.details) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, details, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public String getPrice() {
        return price;
    }
}
