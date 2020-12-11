package com.nile.shoppingcartservice.models;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends CouchbaseRepository<ShoppingCart, UUID> {
    @Query("#{#n1ql.selectEntity} WHERE buyer_id = $1")
    Optional<ShoppingCart> findByBuyerId(String buyer_id);
}
