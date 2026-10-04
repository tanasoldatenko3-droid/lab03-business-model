package by.bseu.pp.lab03.model;

/**
 * Represents a product sold by the enterprise.
 */
public class Product {
    private final String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        // TODO: validate constructor arguments
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Код товара не может быть пустым");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        if (price <0) {
            throw new IllegalArgumentException("Цена товара не может быть отрицательной");
        }
        this.code = code;
        this.name = name;
        this.price = price;


    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void rename(String newName) {
        // TODO: validate and change the product name
        if (newName==null || newName.isBlank()) {
            throw new IllegalArgumentException("Название товара не может быть пустым");
        }
        this.name = newName;
    }

    public void changePrice(double newPrice) {
        // TODO: validate and change the product price
        if (newPrice < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной");
        }
        this.price = newPrice;
    }

    public double calculateAmount(int quantity) {
        // TODO: calculate price * quantity
        if (quantity<=0) {
            throw new IllegalArgumentException("Количество должно быть больше нуля");
        }
        return price*quantity;
    }

}
