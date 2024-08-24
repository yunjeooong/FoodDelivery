package com.delivery.server.menu.controller;

import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.service.MenuService;
import com.delivery.server.menu.service.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/menus")
public class MenuController {
    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<List<Menu>> getMenuByMenuId(@PathVariable Long menuId) {
        List<Menu> menus = menuService.getMenuByMenuId(menuId);
        if (!menus.isEmpty()) {
            return ResponseEntity.ok(menus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shop/{shopId}")
    public List<Menu> getMenusByShopId(@PathVariable Long shopId) {
        return menuService.getMenusByShopId(shopId);
    }

    @PostMapping
    public Menu createMenu(@RequestBody Menu menu) {
        return menuService.createMenu(menu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<Menu> updateMenu(@PathVariable Long menuId, @RequestBody Menu menuDetails) {
        Menu updatedMenu = menuService.updateMenu(menuId, menuDetails);
        if (updatedMenu != null) {
            return ResponseEntity.ok(updatedMenu);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        menuService.deleteMenu(menuId);
        return ResponseEntity.ok().build();
    }


}
