package com.delivery.server.menu.repository;

import com.delivery.server.menu.domain.Menu;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MenuRepositoryImpl implements MenuRepository {
    private final Map<Long, List<Menu>> shopMenuMap = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @Override
    public List<Menu> findAll() {
        List<Menu> allMenus = new ArrayList<>();
        for (List<Menu> menus : shopMenuMap.values()) {
            allMenus.addAll(menus);
        }
        return allMenus;
    }

    @Override
    public List<Menu> findByMenuId(Long menuId) {
        List<Menu> foundMenus = new ArrayList<>();
        for (List<Menu> menus : shopMenuMap.values()) {
            for (Menu menu : menus) {
                if (menu.getMenuId().equals(menuId)) {
                    foundMenus.add(menu);
                }
            }
        }
        return foundMenus;
    }

    @Override
    public List<Menu> findByShopId(Long shopId) {
        return shopMenuMap.getOrDefault(shopId, new ArrayList<>());
    }

    @Override
    public Menu save(Menu menu) {
        if (menu.getMenuId() == null) {
            menu.setMenuId(idCounter.incrementAndGet());
        }
        List<Menu> shopMenus = shopMenuMap.get(menu.getShopId());
        if (shopMenus == null) {
            shopMenus = new ArrayList<>();
            shopMenuMap.put(menu.getShopId(), shopMenus);
        }
        shopMenus.add(menu);
        return menu;
    }

    @Override
    public void deleteByMenuId(Long menuId) {
        for (List<Menu> menus : shopMenuMap.values()) {
            menus.removeIf(menu -> menu.getMenuId().equals(menuId));
        }
    }

}
