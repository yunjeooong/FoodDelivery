package com.delivery.server.menu.controller;

import com.delivery.server.menu.dto.MenuDto;
import com.delivery.server.menu.service.MenuService;
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
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        List<MenuDto> menus = menuService.getAllMenus();
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<List<MenuDto>> getMenuByMenuId(@PathVariable Long menuId) {
        List<MenuDto> menus = menuService.getMenuByMenuId(menuId);
        if (!menus.isEmpty()) {
            return ResponseEntity.ok(menus);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<MenuDto>> getMenusByShopId(@PathVariable Long shopId) {
        List<MenuDto> menus = menuService.getMenusByShopId(shopId);
        return ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        MenuDto createdMenu = menuService.createMenu(menuDto);
        return ResponseEntity.ok(createdMenu);
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long menuId, @RequestBody MenuDto menuDto) {
        MenuDto updatedMenu = menuService.updateMenu(menuId, menuDto);
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