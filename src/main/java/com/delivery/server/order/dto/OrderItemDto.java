package com.delivery.server.order.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderItemDto {
    private Long id;
    private Long menuId;
    private String menuName;
    private Integer quantity;
    private Integer totalPrice;
}