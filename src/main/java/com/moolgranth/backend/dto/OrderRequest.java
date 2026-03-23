package com.moolgranth.backend.dto;

public class OrderRequest {
    private String customerName;
    private String customerPhone;
    private String address;
    private Double totalAmount;
    private String cartItems; // We will store the cart items as a JSON string to keep it simple!

    // --- Getters and Setters ---
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(Double totalAmount) { this.totalAmount = totalAmount; }

    public String getCartItems() { return cartItems; }
    public void setCartItems(String cartItems) { this.cartItems = cartItems; }
}