package com.info5059.casestudy.purchaseOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
@Data
@RequiredArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vendorid;  // FK
    private BigDecimal amount;

    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime purchaseOrderDate;

    @OneToMany(targetEntity = PurchaseOrderLineItem.class, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "purchaseorderid", referencedColumnName = "id")
    private List<PurchaseOrderLineItem> lineItems = new ArrayList<PurchaseOrderLineItem>();
}
