package com.delivery.server.menu.controller;

import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.dto.MenuDto;
import com.delivery.server.menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
        return menus.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(menus);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<List<MenuDto>> getMenuByMenuId(@PathVariable Long menuId) {
        List<MenuDto> menus = menuService.getMenuByMenuId(menuId);
        return menus.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(menus);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<MenuDto>> getMenusByShopId(@PathVariable Long shopId) {
        List<MenuDto> menus = menuService.getMenusByShopId(shopId);
        return menus.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(menus);
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
        try {
            MenuDto createdMenu = menuService.createMenu(menuDto);
            return ResponseEntity.ok(createdMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{menuId}")
    public ResponseEntity<MenuDto> updateMenu(@PathVariable Long menuId, @RequestBody MenuDto menuDto) {
        try {
            MenuDto updatedMenu = menuService.updateMenu(menuId, menuDto);
            return ResponseEntity.ok(updatedMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> deleteMenu(@PathVariable Long menuId) {
        try {
            menuService.deleteMenu(menuId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{menuId}/menu-status")
    public ResponseEntity<MenuDto> updateMenuStatus(@PathVariable Long menuId, @RequestBody Map<String, String> statusMap) {
        String statusString = statusMap.get("menuStatus");
        if (statusString == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            Menu.MenuStatus menuStatus = Menu.MenuStatus.valueOf(statusString.toUpperCase());
            MenuDto updatedMenu = menuService.updateMenuStatus(menuId, menuStatus);
            return ResponseEntity.ok(updatedMenu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{menuId}/main-menu")
    public ResponseEntity<MenuDto> updateMainMenuStatus(@PathVariable Long menuId, @RequestBody Map<String, Boolean> isMainMenu) {
        if (!isMainMenu.containsKey("isMainMenu")) {
            return ResponseEntity.badRequest().build();
        }
        try {
            MenuDto updatedMenu = menuService.updateMainMenuStatus(menuId, isMainMenu.get("isMainMenu"));
            return ResponseEntity.ok(updatedMenu);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}