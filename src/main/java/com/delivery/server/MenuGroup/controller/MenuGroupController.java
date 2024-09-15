package com.delivery.server.MenuGroup.controller;


import com.delivery.server.MenuGroup.dto.MenuGroupDto;
import com.delivery.server.MenuGroup.service.MenuGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;


@RestController
@RequestMapping("/api/v1")
public class MenuGroupController {

    private final MenuGroupService menuGroupService;

    @Autowired
    public MenuGroupController(MenuGroupService menuGroupService) {
        this.menuGroupService = menuGroupService;
    }

    @PostMapping("/restaurant/{restaurantId}/menu-groups")
    public ResponseEntity<MenuGroupDto> createMenuGroup(@PathVariable Long restaurantId, @RequestBody MenuGroupDto menuGroupDto) {
        MenuGroupDto createdMenuGroup = menuGroupService.createMenuGroup(restaurantId, menuGroupDto);
        return ResponseEntity.created(URI.create("/api/v1/menu-groups/" + createdMenuGroup.getId())).body(createdMenuGroup);
    }

    @GetMapping("/menu-groups/{menuGroupId}")
    public ResponseEntity<MenuGroupDto> getMenuGroup(@PathVariable Long menuGroupId) {
        MenuGroupDto menuGroup = menuGroupService.getMenuGroup(menuGroupId);
        return ResponseEntity.ok(menuGroup);
    }

    @GetMapping("/restaurant/{restaurantId}/menu-groups")
    public ResponseEntity<List<MenuGroupDto>> getMenuGroupsByRestaurant(@PathVariable Long restaurantId) {
        List<MenuGroupDto> menuGroups = menuGroupService.getMenuGroupsByRestaurant(restaurantId);
        return ResponseEntity.ok(menuGroups);
    }

    @PatchMapping("/menu-groups/{menuGroupId}")
    public ResponseEntity<MenuGroupDto> updateMenuGroup(@PathVariable Long menuGroupId, @RequestBody MenuGroupDto menuGroupDto) {
        MenuGroupDto updatedMenuGroup = menuGroupService.updateMenuGroup(menuGroupId, menuGroupDto);
        return ResponseEntity.ok(updatedMenuGroup);
    }

    @DeleteMapping("/menu-groups/{menuGroupId}")
    public ResponseEntity<Void> deleteMenuGroup(@PathVariable Long menuGroupId) {
        menuGroupService.deleteMenuGroup(menuGroupId);
        return ResponseEntity.ok().build();
    }

}
