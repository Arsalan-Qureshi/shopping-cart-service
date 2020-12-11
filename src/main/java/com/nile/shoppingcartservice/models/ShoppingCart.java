package com.nile.shoppingcartservice.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.UUID;

@Document
public class ShoppingCart {
    @Id
    private UUID id;
    @Field
    private String product_id;
    @Field
    private String buyer_id;
    @Field
    private int quantity;
    
    public ShoppingCart() {
    }

    public ShoppingCart(UUID id, String product_id, String buyer_id, int quantity) {
        this.id = id;
        this.product_id = product_id;
        this.buyer_id = buyer_id;
        this.quantity = quantity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
