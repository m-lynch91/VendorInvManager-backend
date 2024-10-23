package com.info5059.casestudy.purchaseOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.info5059.casestudy.product.ProductRepository;
import com.info5059.casestudy.product.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.math.BigDecimal;


@Component
public class PurchaseOrderDAO {

    /**
	 * <p>
	 * The {@link EntityManager} instance responsible for facilitating CRUD
	 * operations on entity instances.
	 * This object will find entities by their primary key. It also allows clients
	 * to query over entities.
	 * </p>
	 */ 
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public PurchaseOrder create(PurchaseOrder orderFromClient) {
        PurchaseOrder realOrder = new PurchaseOrder();
        realOrder.setVendorid(orderFromClient.getVendorid());
        realOrder.setAmount(orderFromClient.getAmount());
        realOrder.setPurchaseOrderDate(LocalDateTime.now());
        entityManager.persist(realOrder);
        
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (PurchaseOrderLineItem lineItem : orderFromClient.getLineItems()) {

            PurchaseOrderLineItem realLineItem = new PurchaseOrderLineItem();
            Product product = productRepository.getReferenceById(lineItem.getProductid());
            BigDecimal price = product.getPurchaseprice();

            realLineItem.setPurchaseorderid(realOrder.getId());
            realLineItem.setProductid(lineItem.getProductid());
            realLineItem.setQuantity(lineItem.getQuantity());
            realLineItem.setPrice(lineItem.getPrice());
            entityManager.persist(realLineItem);
            
            // calculate total purchase order amount
            totalAmount = totalAmount.add(price.multiply(new BigDecimal(lineItem.getQuantity())));

            // update product inventory
            product.setQuantityonorder(product.getQuantityonorder() + lineItem.getQuantity());
            productRepository.saveAndFlush(product);
            

        }

        totalAmount = totalAmount.multiply(new BigDecimal(1.13));
        realOrder.setAmount(totalAmount);

		// sequence to commit changes to db
        entityManager.flush();
        entityManager.refresh(realOrder);
        return realOrder;
    }

}
