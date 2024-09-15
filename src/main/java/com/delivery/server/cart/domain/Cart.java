package com.delivery.server.cart.domain;

import com.delivery.server.customer.domain.Customer;
import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menuoption.domain.MenuOption;
import jakarta.persistence.*;
import lombok.*;

    @Entity
    @Table(name = "carts")
    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor
    @Builder
    public class Cart {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "customer_login_id")
        private Customer customer;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "menu_id")
        private Menu menu;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "menu_option_id")
        private MenuOption menuOption;

        private Integer quantity;
        private Integer totalPrice;
    }

