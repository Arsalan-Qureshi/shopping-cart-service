package com.nile.shoppingcartservice;

import com.nile.shoppingcartservice.models.ShoppingCart;
import com.nile.shoppingcartservice.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @RequestMapping("/shopping-carts")
    public Iterable<ShoppingCart> getAllShoppingCarts(@RequestHeader("Authorization") String token) {
        return shoppingCartService.getAllCarts(token);
    }

    @RequestMapping("/shopping-carts/{id}")
    public Optional<ShoppingCart> getCart(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        return shoppingCartService.getCart(token, UUID.fromString(id));
    }

    @RequestMapping("/shopping-carts/user/{id}")
    public Optional<ShoppingCart> getCartForUser(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        return shoppingCartService.getCartForUser(token, UUID.fromString(id));
    }

    @PostMapping("/shopping-carts")
    public void addShoppingCart(@RequestHeader("Authorization") String token, @RequestBody ShoppingCart shoppingCart) {
        shoppingCartService.addToCart(token, shoppingCart);
    }

    @PutMapping("/shopping-carts")
    public void updateShoppingCart(@RequestHeader("Authorization") String token, @RequestBody ShoppingCart shoppingCarts) {
        shoppingCartService.updateCart(token, shoppingCarts);
    }

    @DeleteMapping("/shopping-carts/{id}")
    public void deleteShoppingCart(@RequestHeader("Authorization") String token, @PathVariable("id") String id) {
        shoppingCartService.deleteCart(token, UUID.fromString(id));
    }
}
