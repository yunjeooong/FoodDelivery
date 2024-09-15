package com.delivery.server.order.service;

import com.delivery.server.customer.repository.CustomerRepository;
import com.delivery.server.menu.repository.MenuRepository;
import com.delivery.server.order.domain.Order;
import com.delivery.server.order.domain.OrderItem;
import com.delivery.server.order.dto.OrderDto;
import com.delivery.server.order.dto.OrderItemDto;
import com.delivery.server.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .customer(customerRepository.findByLoginId(orderDto.getCustomerLoginId())
                        .orElseThrow(() -> new RuntimeException("Customer not found")))
                .usePoint(orderDto.getUsePoint())
                .paymentType(orderDto.getPaymentType())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .orderStatus(Order.OrderStatus.PAYMENT_COMPLETED)
                .build();

        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = OrderItem.builder()
                    .menu(menuRepository.findById(itemDto.getMenuId())
                            .orElseThrow(() -> new RuntimeException("Menu not found")))
                    .quantity(itemDto.getQuantity())
                    .totalPrice(itemDto.getTotalPrice())
                    .build();
            order.addOrderItem(orderItem);
        }

        // 주문 가격 계산 로직
        order.setOrderPrice(order.getOrderItems().stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum());
        order.setAfterDiscountTotalPrice(order.getOrderPrice() - order.getUsePoint());

        Order savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    public OrderDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDto(order);
    }

    public Page<OrderDto> getCustomerOrders(String loginId, LocalDate from, LocalDate to, Pageable pageable) {
        LocalDateTime fromDateTime = from.atStartOfDay();
        LocalDateTime toDateTime = to.atTime(LocalTime.MAX);
        return orderRepository.findByCustomerLoginIdAndCreatedAtBetween(loginId, fromDateTime, toDateTime, pageable)
                .map(this::convertToDto);
    }

    public OrderDto updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        return convertToDto(orderRepository.save(order));
    }

    private OrderDto convertToDto(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerLoginId(order.getCustomer().getLoginId())
                .usePoint(order.getUsePoint())
                .paymentType(order.getPaymentType())
                .deliveryAddress(order.getDeliveryAddress())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .orderPrice(order.getOrderPrice())
                .deliveryFee(order.getDeliveryFee())
                .afterDiscountTotalPrice(order.getAfterDiscountTotalPrice())
                .totalDiscountPrice(order.getTotalDiscountPrice())
                .orderItems(order.getOrderItems().stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()))
                .build();
    }

    private OrderItemDto convertToDto(OrderItem orderItem) {
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .menuId(orderItem.getMenu().getMenuId())
                .menuName(orderItem.getMenu().getName())
                .quantity(orderItem.getQuantity())
                .totalPrice(orderItem.getTotalPrice())
                .build();
    }
}