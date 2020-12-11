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
    private ShoppingCartRepository shoppingCartRepository;

    public List<ShoppingCart> getAllCarts() {
        // Add admin authentication here.
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        return carts;
    }

    public Optional<ShoppingCart> getCart(UUID id) {
        // Add admin authentication here.
        return shoppingCartRepository.findById(id);
    }

    public Optional<ShoppingCart> getCartForUser(String token, UUID id) {
        if (authenticate(token).matches("OK")) {
            return shoppingCartRepository.findByBuyerId(id.toString());
        } else {
            return null;
        }
    }

    public void addToCart(String token, ShoppingCart cart) {
        if (authenticate(token).matches("OK")) {
            shoppingCartRepository.save(cart);
        }
    }

    public void updateCart(String token, ShoppingCart cart) {
        if (authenticate(token).matches("OK")) {
            shoppingCartRepository.save(cart);
        }
    }

    public void deleteCart(String token, UUID id) {
        if (authenticate(token).matches("OK")) {
            shoppingCartRepository.deleteById(id);
        }
    }

    public String authenticate(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8081/", HttpMethod.GET, entity, String.class);
        return responseEntity.getBody();
    }
}
