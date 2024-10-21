package com.info5059.casestudy.purchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @PostMapping("/api/purchaseOrders")
    public ResponseEntity<PurchaseOrder> addOne(@RequestBody PurchaseOrder purchaseOrder) {
        return new ResponseEntity<PurchaseOrder>(purchaseOrderDAO.create(purchaseOrder), HttpStatus.OK);
    }

    
}
