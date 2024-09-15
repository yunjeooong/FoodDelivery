package com.delivery.server.order.domain;


import com.delivery.server.customer.domain.Customer;
import com.delivery.server.order.domain.OrderItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_login_id")
    private Customer customer;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;*/

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;*/

    private Integer usePoint;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private LocalDateTime createdAt;

    private Integer orderPrice;
    private Integer deliveryFee;
    private Integer afterDiscountTotalPrice;
    private Integer totalDiscountPrice;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public enum PaymentType {
        CREDIT_CARD, CASH
    }

    public enum OrderStatus {
        PAYMENT_COMPLETED, ACCEPTED, CANCEL
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    //주문 항목 추가
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    //주문 항목 삭제
    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setOrder(null);
    }
}
