package com.delivery.server.cart.service;

import com.delivery.server.cart.domain.Cart;
import com.delivery.server.cart.dto.CartDto;
import com.delivery.server.cart.repository.CartRepository;
import com.delivery.server.customer.domain.Customer;
import com.delivery.server.customer.repository.CustomerRepository;
import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.repository.MenuRepository;
import com.delivery.server.menuoption.domain.MenuOption;
import com.delivery.server.menuoption.repository.MenuOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CustomerRepository customerRepository,
                       MenuRepository menuRepository, MenuOptionRepository menuOptionRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.menuOptionRepository = menuOptionRepository;
    }

    public CartDto addToCart(CartDto cartDto) {
        Customer customer = customerRepository.findByLoginId(cartDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Menu menu = menuRepository.findById(cartDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        MenuOption menuOption = menuOptionRepository.findById(cartDto.getMenuOptionId())
                .orElseThrow(() -> new RuntimeException("Menu option not found"));

        Cart cart = Cart.builder()
                .customer(customer)
                .menu(menu)
                .menuOption(menuOption)
                .quantity(cartDto.getQuantity())
                .totalPrice(calculateTotalPrice(menu, menuOption, cartDto.getQuantity()))
                .build();

        Cart savedCart = cartRepository.save(cart);
        return convertToDto(savedCart);
    }

    public List<CartDto> getCustomerCart(String loginId) {
        return cartRepository.findByCustomerLoginId(loginId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public void clearCart(String loginId) {
        List<Cart> customerCart = cartRepository.findByCustomerLoginId(loginId);
        cartRepository.deleteAll(customerCart);
    }

    private CartDto convertToDto(Cart cart) {
        return CartDto.builder()
                .id(cart.getId())
                .customerId(cart.getCustomer().getLoginId())
                .menuId(cart.getMenu().getMenuId())
                .menuOptionId(cart.getMenuOption().getMenuOptionId())
                .quantity(cart.getQuantity())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private Integer calculateTotalPrice(Menu menu, MenuOption menuOption, Integer quantity) {
        return (int) ((menu.getPrice() + menuOption.getPrice()) * quantity);
    }
}