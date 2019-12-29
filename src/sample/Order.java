package sample;

import java.time.LocalDate;

public class Order {
    private String OrderID;
    private String CustomerName;
    private String OrderType;
    private String DeliveryAddress;
    private int DeliveryPrice;
    private LocalDate OrderDate;
    private LocalDate DeliveryDate;
    private String OrderStatus;
    private int Payment;
    private int Discount;

    public Order(String orderID, String customerName, String orderType, String deliveryAddress, int deliveryPrice, LocalDate orderDate, LocalDate deliveryDate, String orderStatus, int payment, int discount) {
        OrderID = orderID;
        CustomerName = customerName;
        OrderType = orderType;
        DeliveryAddress = deliveryAddress;
        DeliveryPrice = deliveryPrice;
        OrderDate = orderDate;
        DeliveryDate = deliveryDate;
        OrderStatus = orderStatus;
        Payment = payment;
        Discount = discount;
    }

    // Constructor for TableView
    public Order(String orderID, String customerName, String orderType, LocalDate orderDate, LocalDate deliveryDate, String orderStatus, int payment) {
        OrderID = orderID;
        CustomerName = customerName;
        OrderType = orderType;
        OrderDate = orderDate;
        DeliveryDate = deliveryDate;
        OrderStatus = orderStatus;
        Payment = payment;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getDeliveryAddress() {
        return DeliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAdress) {
        DeliveryAddress = deliveryAdress;
    }

    public int getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        DeliveryPrice = deliveryPrice;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        OrderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        DeliveryDate = deliveryDate;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public int getPayment() {
        return Payment;
    }

    public void setPayment(int payment) {
        Payment = payment;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }
}
