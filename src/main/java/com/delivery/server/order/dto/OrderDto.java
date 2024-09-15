package com.delivery.server.order.dto;

import com.delivery.server.order.domain.Order;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderDto {
    private Long id;
    private String customerLoginId;
    private Long restaurantId;
    private Long voucherId;
    private Integer usePoint;
    private Order.PaymentType paymentType;
    private String deliveryAddress;
    private Order.OrderStatus orderStatus;
    private LocalDateTime createdAt;
    private Integer orderPrice;
    private Integer deliveryFee;
    private Integer afterDiscountTotalPrice;
    private Integer totalDiscountPrice;
    private List<OrderItemDto> orderItems;
}