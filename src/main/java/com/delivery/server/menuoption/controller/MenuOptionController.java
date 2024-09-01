package com.delivery.server.menuoption.controller;

import com.delivery.server.menuoption.dto.MenuOptionDto;
import com.delivery.server.menuoption.service.MenuOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu-options")
public class MenuOptionController {
    private final MenuOptionService menuOptionService;

    @Autowired
    public MenuOptionController(MenuOptionService menuOptionService) {
        this.menuOptionService = menuOptionService;
    }

    @PostMapping
    public ResponseEntity<MenuOptionDto> createMenuOption(@RequestBody MenuOptionDto menuOptionDto) {
        MenuOptionDto createdOption = menuOptionService.createMenuOption(menuOptionDto);
        return ResponseEntity.ok(createdOption);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuOptionDto> updateMenuOption(@PathVariable Long id, @RequestBody MenuOptionDto menuOptionDto) {
        MenuOptionDto updatedOption = menuOptionService.updateMenuOption(id, menuOptionDto);
        return ResponseEntity.ok(updatedOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuOption(@PathVariable Long id) {
        menuOptionService.deleteMenuOption(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/menu/{menuId}")  // 용자가 메뉴를 선택하고 해당 메뉴의 옵션을 확인할 때 필요
    public ResponseEntity<List<MenuOptionDto>> getMenuOptionsByMenuId(@PathVariable Long menuId) {
        List<MenuOptionDto> options = menuOptionService.getMenuOptionsByMenuId(menuId);
        return ResponseEntity.ok(options);
    }
}