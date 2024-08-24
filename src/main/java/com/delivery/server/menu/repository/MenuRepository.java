package com.delivery.server.menu.repository;

import java.util.List;
import com.delivery.server.menu.domain.Menu;
public interface MenuRepository {
    List<Menu> findAll();
    List<Menu> findByMenuId(Long menuId);
    List<Menu> findByShopId(Long shopId);
    Menu save(Menu menu);
    void deleteByMenuId(Long menuId);

}
