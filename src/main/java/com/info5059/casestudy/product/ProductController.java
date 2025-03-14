package com.info5059.casestudy.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@CrossOrigin // enable CORS
@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;

    @GetMapping("/api/products")
    public ResponseEntity<Iterable<Product>> findAll() {
        Iterable<Product> expenses = productRepository.findAll();
        return new ResponseEntity<Iterable<Product>>(expenses, HttpStatus.OK);
    }

    @GetMapping("/api/products/{vendorid}")
    public ResponseEntity<Iterable<Product>> findByVendor(@PathVariable Long vendorid) {
        Iterable<Product> products = productRepository.findByVendorid(vendorid);
        return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
    }

    @PutMapping("/api/products")
    public ResponseEntity<Product> updateOne(@RequestBody Product product) {
        product.setQrcode(qrCodeGenerator.generateQRCode(product.getQrcodetext()));
        Product updatedExpense = productRepository.save(product);
        return new ResponseEntity<Product>(updatedExpense, HttpStatus.OK);
    }

    @PostMapping("/api/products")
    public ResponseEntity<Product> addOne(@RequestBody Product product) {
        product.setQrcode(qrCodeGenerator.generateQRCode(product.getQrcodetext()));
        Product newExpense = productRepository.save(product);
        return new ResponseEntity<Product>(newExpense, HttpStatus.OK);
    }

    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<Integer> deleteOne(@PathVariable String id) {
        return new ResponseEntity<Integer>(productRepository.deleteOne(id), HttpStatus.OK);
    }

    @GetMapping("/api/qrcode/{txt}")
    public ResponseEntity<byte[]> getQRCode(@PathVariable String txt) {
        byte[] qrcodebin = qrCodeGenerator.generateQRCode(txt);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<byte[]>(qrcodebin, headers, HttpStatus.CREATED);
    }
}
