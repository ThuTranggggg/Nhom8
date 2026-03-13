package com.example.minipro.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;              // Mã phòng (P001, P002, ...)
    private String name;            // Tên phòng
    private double price;           // Giá thuê/tháng
    private boolean isRented;       // true = Đã thuê, false = Còn trống
    private String tenantName;      // Tên người thuê
    private String tenantPhone;     // Số điện thoại người thuê

    // Constructor
    public Room(String id, String name, double price, boolean isRented,
                String tenantName, String tenantPhone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isRented() { return isRented; }
    public void setRented(boolean rented) { isRented = rented; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getTenantPhone() { return tenantPhone; }
    public void setTenantPhone(String tenantPhone) { this.tenantPhone = tenantPhone; }
}
