package com.info5059.casestudy.purchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.core.io.InputStreamResource; 
import org.springframework.http.HttpHeaders; 
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import java.io.ByteArrayInputStream;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.vendor.VendorRepository;
import org.springframework.web.bind.annotation.RequestParam;



@CrossOrigin
@RestController
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping("/api/purchase-orders")
    public ResponseEntity<Iterable<PurchaseOrder>> findAll() {
     Iterable<PurchaseOrder> pos = purchaseOrderRepository.findAll();
     return new ResponseEntity<Iterable<PurchaseOrder>>(pos, HttpStatus.OK);
    }
    
    @GetMapping("/api/purchase-orders/{vendorid}")
    public ResponseEntity<Iterable<PurchaseOrder>> findByVendor(@PathVariable Long vendorid) {
        return new ResponseEntity<Iterable<PurchaseOrder>>(purchaseOrderRepository.findByVendorid(vendorid), HttpStatus.OK);
    }
    

    @PostMapping("/api/purchase-orders")
    public ResponseEntity<PurchaseOrder> addOne(@RequestBody PurchaseOrder purchaseOrder) {
        return new ResponseEntity<PurchaseOrder>(purchaseOrderDAO.create(purchaseOrder), HttpStatus.OK);
    }

    @GetMapping(value = "/api/purchase-orders/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getPDF(HttpServletRequest request) throws IOException {  

        String orderid = request.getParameter("purchaseorderid");
        ByteArrayInputStream bis = PDFGenerator.generateReportPdf(
            orderid,
            purchaseOrderRepository,
            productRepository,
            vendorRepository
        ); 

        HttpHeaders headers = new HttpHeaders(); 
        headers.add("Content-Disposition", "inline; filename=po-" + orderid + ".pdf"); 
        
        // dump stream to browser 
        return ResponseEntity 
            .ok() 
            .headers(headers) 
            .contentType(MediaType.APPLICATION_PDF) 
            .body(new InputStreamResource(bis)); 
    } 

    
}
