package com.delivery.server.cart.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartDto {
    private Long id;
    private String customerId;
    private Long menuId;
    private Long menuOptionId;
    private Integer quantity;
    private Integer totalPrice;
}
