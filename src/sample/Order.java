package sample;

import java.time.LocalDate;

public class Order {
    private String OrderID;
    private String CustomerID;
    private String OrderType;
    private String DeliveryAdress;
    private int DeliveryPrice;
    private LocalDate OrderDateTime;
    private LocalDate DeliveryDateTime;
    private String OrderStatus;
    private int Payment;
    private int Discount;

    public Order(String orderID, String customerID, String orderType, String deliveryAdress, int deliveryPrice, LocalDate orderDateTime, LocalDate deliveryDateTime, String orderStatus, int payment, int discount) {
        OrderID = orderID;
        CustomerID = customerID;
        OrderType = orderType;
        DeliveryAdress = deliveryAdress;
        DeliveryPrice = deliveryPrice;
        OrderDateTime = orderDateTime;
        DeliveryDateTime = deliveryDateTime;
        OrderStatus = orderStatus;
        Payment = payment;
        Discount = discount;
    }

    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String orderID) {
        OrderID = orderID;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String orderType) {
        OrderType = orderType;
    }

    public String getDeliveryAdress() {
        return DeliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        DeliveryAdress = deliveryAdress;
    }

    public int getDeliveryPrice() {
        return DeliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        DeliveryPrice = deliveryPrice;
    }

    public LocalDate getOrderDateTime() {
        return OrderDateTime;
    }

    public void setOrderDateTime(LocalDate orderDateTime) {
        OrderDateTime = orderDateTime;
    }

    public LocalDate getDeliveryDateTime() {
        return DeliveryDateTime;
    }

    public void setDeliveryDateTime(LocalDate deliveryDateTime) {
        DeliveryDateTime = deliveryDateTime;
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
