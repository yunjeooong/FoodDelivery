package com.delivery.server.customer.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer_grades")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CustomerGrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer orderCount;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer discountPrice;

    @Column(nullable = false)
    private Integer voucherCount;
}
