package by.bseu.pp.lab03.model;

/**
 * Represents a customer of the enterprise.
 *
 * <p>Complete all TODO parts without changing the public API of the class.</p>
 */
public class Customer {
    private final String code;
    private String name;
    private boolean active;
    private double creditLimit;

    public Customer(String code, String name, double creditLimit) {
        // TODO: validate constructor arguments
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Код клиента не может быть пустым или состоять только из пробелов");
        }
        if (name==null || name.isBlank()) {
            throw new IllegalArgumentException("Имя клиента не может быть пустым или состоять только из пробелов");
        }
        if (creditLimit<0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным");
        }

        this.code = code;
        this.name = name;
        this.creditLimit = creditLimit;
        this.active = true;


    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void rename(String newName) {
        // TODO: validate and change the customer name
        if (newName==null || newName.isBlank()) {
            throw new IllegalArgumentException("Новое имя не может быть пустым");
        }
        this.name = newName;
    }

    public void changeCreditLimit(double newCreditLimit) {
        // TODO: validate and change the credit limit
        if (newCreditLimit < 0) {
            throw new IllegalArgumentException("Кредитный лимит не может быть отрицательным");
        }
        this.creditLimit = newCreditLimit;
    }

    public void activate() {
        // TODO: activate the customer
        this.active = true;

    }

    public void deactivate() {
        // TODO: deactivate the customer
        this.active = false;
    }

    public boolean canPlaceOrder(double orderTotal) {
        // TODO: return true only for an active customer whose order fits the credit limit
        if (orderTotal < 0) {
            throw new IllegalArgumentException("Сумма заказа не может быть отрицательной");
        }

        return this.active && orderTotal <= this.creditLimit;
    }
}
