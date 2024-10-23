package com.info5059.casestudy.purchaseOrder;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
@RepositoryRestResource(collectionResourceRel = "purchase-orders", path = "purchase-orders")
public interface PurchaseOrderRepository extends CrudRepository<PurchaseOrder, Long> {
    @Modifying
    @Transactional
    @Query("delete from PurchaseOrder where id = ?1")
    int deleteOne(String id);

    List<PurchaseOrder> findByVendorid(Long vendorid);

}