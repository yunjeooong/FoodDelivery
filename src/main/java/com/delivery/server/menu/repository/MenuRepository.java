package com.delivery.server.menu.repository;

import com.delivery.server.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuId(Long menuId);
    List<Menu> findByShopId(Long shopId);
}