package com.example.miniapp.model;

import java.io.Serializable;

public class Room implements Serializable {
    private String id;              // Mã phòng (P001, P002, ...)
    private String name;            // Tên phòng (Phòng A101, ...)
    private long price;             // Giá thuê (VND)
    private boolean isRented;       // Tình trạng phòng (false = Còntrống, true = Đã thuê)
    private String tenantName;      // Tên người thuê
    private String tenantPhone;     // SĐT người thuê

    // Constructor
    public Room(String id, String name, long price, boolean isRented, String tenantName, String tenantPhone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isRented = isRented;
        this.tenantName = tenantName;
        this.tenantPhone = tenantPhone;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public long getPrice() { return price; }
    public boolean isRented() { return isRented; }
    public String getTenantName() { return tenantName; }
    public String getTenantPhone() { return tenantPhone; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(long price) { this.price = price; }
    public void setRented(boolean rented) { isRented = rented; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }
    public void setTenantPhone(String tenantPhone) { this.tenantPhone = tenantPhone; }
}
