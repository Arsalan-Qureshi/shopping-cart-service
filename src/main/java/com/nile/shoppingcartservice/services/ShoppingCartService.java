package com.nile.shoppingcartservice.services;

import com.nile.shoppingcartservice.models.ShoppingCart;
import com.nile.shoppingcartservice.models.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoppingCartService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getAllCarts(String token) {
        if (authenticateAdmin(token).matches("OK")) {
            List<ShoppingCart> carts = shoppingCartRepository.findAll();
            return carts;
        } else {
            return null;
        }
    }

    public Optional<ShoppingCart> getCart(String token, UUID id) {
        if (authenticateAdmin(token).matches("OK")) {
            return shoppingCartRepository.findById(id);
        } else {
            return null;
        }
    }

    public Optional<ShoppingCart> getCartForUser(String token, UUID id) {
        if (authenticateBuyer(token).matches("OK")) {
            return shoppingCartRepository.findByBuyerId(id.toString());
        } else {
            return null;
        }
    }

    public void addToCart(String token, ShoppingCart cart) {
        if (authenticateBuyer(token).matches("OK")) {
            shoppingCartRepository.save(cart);
        }
    }

    public void updateCart(String token, ShoppingCart cart) {
        if (authenticateBuyer(token).matches("OK")) {
            shoppingCartRepository.save(cart);
        }
    }

    public void deleteCart(String token, UUID id) {
        if (authenticateBuyer(token).matches("OK")) {
            shoppingCartRepository.deleteById(id);
        }
    }

    public String authenticateBuyer(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://buyer-authentication-service/buyer/authenticate", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }

    public String authenticateAdmin(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://admin-authentication-service/admin/authenticate", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}