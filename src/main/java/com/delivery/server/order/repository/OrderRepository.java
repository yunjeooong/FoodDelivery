package com.delivery.server.order.repository;

import com.delivery.server.order.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByCustomerLoginId(String loginId, Pageable pageable);
    Page<Order> findByCustomerLoginIdAndCreatedAtBetween(String loginId, LocalDateTime from, LocalDateTime to, Pageable pageable);
    // 가게 관련 메서드는 Restaurant 엔티티 구현 후 추가
}