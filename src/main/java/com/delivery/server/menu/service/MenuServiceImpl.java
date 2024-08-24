package com.delivery.server.menu.service;


import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService{
    private final MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public List<Menu> getMenuByMenuId(Long menuId) {
        return menuRepository.findByMenuId(menuId);
    }

    @Override
    public List<Menu> getMenusByShopId(Long shopId) {
        return menuRepository.findByShopId(shopId);
    }

    @Override
    public Menu createMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Long menuId, Menu menuDetails) {
        List<Menu> existingMenus = menuRepository.findByMenuId(menuId);
        if (!existingMenus.isEmpty()) {
            Menu existingMenu = existingMenus.get(0);
            existingMenu.setName(menuDetails.getName());
            existingMenu.setDescription(menuDetails.getDescription());
            existingMenu.setPrice(menuDetails.getPrice());
            return menuRepository.save(existingMenu);
        }
        return null;
    }

    @Override
    public void deleteMenu(Long menuId) {
        menuRepository.deleteByMenuId(menuId);
    }
}
