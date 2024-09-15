package com.delivery.server.cart.repository;
import com.delivery.server.cart.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
    List<Cart> findByCustomerLoginId(String loginId);
}
