package com.delivery.server.customer.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(name = "login_id")
    private String loginId;

    @Column(nullable = false)
    private String loginPassword;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private Integer orderPerMonth;

    @Column(nullable = false)
    private Integer point;

    @Column(nullable = false)
    private Boolean isIssued;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_grade_id")
    private CustomerGrade customerGrade;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerAddress> addresses = new ArrayList<>();
}
