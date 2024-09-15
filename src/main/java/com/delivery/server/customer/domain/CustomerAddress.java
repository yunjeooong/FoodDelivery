package com.delivery.server.customer.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer_addresses")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomerAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private String defaultAddress;

    @Column(nullable = false)
    private String detailAddress;

    @Column(nullable = false)
    private String nickname;

}
