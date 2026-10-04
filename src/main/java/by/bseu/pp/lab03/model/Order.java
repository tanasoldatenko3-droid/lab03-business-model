package by.bseu.pp.lab03.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an order and protects the allowed order state transitions.
 */
public class Order {
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private Payment payment;

    public Order(Customer customer) {
        // TODO: validate customer
        if (customer==null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
        this.payment = null;


    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public int getItemsCount() {
        return items.size();
    }

    public Payment getPayment() {
        return payment;
    }

    public void addItem(Product product, int quantity) {
        // TODO: items may be added only while the order is CREATED
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Добавлять позиции можно только в заказ со статусом CREATED");
        }
        OrderItem newItem = new OrderItem(product, quantity);
        this.items.add(newItem);
    }

    public double total() {
        // TODO: sum amounts of all order items
        if (items == null || items.isEmpty()) {
            return 0.0;
        }
        double totalAmount = 0.0;
        for (OrderItem item : items) {
            if (item!=null) {
                totalAmount +=item.getAmount();
            }
        }
        return totalAmount;
    }

    public void confirm() {
        // TODO: confirm a non-empty CREATED order for an active customer within the credit limit
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Заказ должен быть в статусе CREATED");
        }
        if (this.items == null || this.items.isEmpty()) {
            throw new IllegalStateException("В заказе должна быть хотя бы одна позиция");
        }
        if (this.customer == null || !this.customer.isActive()) {
            throw new IllegalStateException("Клиент должен быть активен");
        }
        if (this.total() > this.customer.getCreditLimit()) {
            throw new IllegalStateException("Общая сумма заказа превышает кредитный лимит клиента");
        }
        this.status = OrderStatus.CONFIRMED;
    }

    public void cancel() {
        // TODO: allow cancellation only before payment
        if (this.status != OrderStatus.CREATED && this.status != OrderStatus.CONFIRMED){
            throw new IllegalStateException("Отменить можно только заказ в состоянии CREATED или CONFIRMED");
        }
        this.status = OrderStatus.CANCELLED;
    }

    public void pay(Payment payment) {
        // TODO: accept only a successful payment for the exact total of a CONFIRMED order
        if (payment==null) {
            throw new IllegalArgumentException("Платеж не может быть null");
        }
        if (this.status != OrderStatus.CONFIRMED) {
            throw new IllegalStateException("Оплатить можно только подтвержденный заказ");
        }
        if (payment.getStatus() != PaymentStatus.SUCCESSFUL) {
            throw new IllegalStateException("Платёж должен иметь статус SUCCESSFU");
        }
        if (Math.abs(payment.getAmount() - this.total()) > 0.001) {
            throw new IllegalArgumentException("Сумма платежа не соответствует сумме заказа");
        }
        this.payment = payment;
        this.status = OrderStatus.PAID;

    }

    public boolean isPaid() {
        // TODO: return true only for PAID order
        return this.status == OrderStatus.PAID;
    }
}
