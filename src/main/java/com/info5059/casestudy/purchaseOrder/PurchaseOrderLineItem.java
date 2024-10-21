package com.info5059.casestudy.purchaseOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@Data
@Entity
@RequiredArgsConstructor
public class PurchaseOrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long purchaseorderid;  // FK
    private String productid;  // FK
    private Integer quantity;
    private BigDecimal price;
}
