package com.delivery.server.cart.controller;
import com.delivery.server.cart.dto.CartDto;
import com.delivery.server.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartDto> addToCart(@RequestBody CartDto cartDto) {
        CartDto addedCart = cartService.addToCart(cartDto);
        return ResponseEntity.ok(addedCart);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<CartDto>> getCustomerCart(@PathVariable String customerId) {
        List<CartDto> cart = cartService.getCustomerCart(customerId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<Void> clearCart(@PathVariable String customerId) {
        cartService.clearCart(customerId);
        return ResponseEntity.ok().build();
    }

}
