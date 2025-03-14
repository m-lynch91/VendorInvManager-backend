package com.info5059.casestudy.product;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Product {
    @Id
    private String Id;
    private Integer vendorid;
    private String name;
    private BigDecimal purchaseprice;
    private BigDecimal msrp;
    
    /**
     * Inventory value to signal when to re-order item.
     */
    private int reorderpoint;

    /**
     * Order quantity that minimizes total holding costs / ordering costs in inventory management.
     */
    private int economicorderquantity;

    /**
     * Item quantity in inventory.
     */
    private int quantityonhand;
    
    /**
     * Item quantity ordered but not yet received.
     */
    private int quantityonorder;
    
    @Column(columnDefinition="varbinary(1000)")
    private byte[] qrcode;
    private String qrcodetext;
}
