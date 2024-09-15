package com.delivery.server.MenuGroup.repository;

import com.delivery.server.MenuGroup.domain.MenuGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MenuGroupRepository extends JpaRepository<MenuGroup, Long>{
    List<MenuGroup> findByRestaurantId(Long restaurantId);
}
