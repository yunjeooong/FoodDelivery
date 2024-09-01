package com.delivery.server.menu.service;

import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.dto.MenuDto;
import com.delivery.server.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuService {
    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public List<MenuDto> getAllMenus() {
        return menuRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MenuDto> getMenuByMenuId(Long menuId) {
        return menuRepository.findByMenuId(menuId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<MenuDto> getMenusByShopId(Long shopId) {
        return menuRepository.findByShopId(shopId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MenuDto createMenu(MenuDto menuDto) {
        Menu menu = convertToEntity(menuDto);
        Menu savedMenu = menuRepository.save(menu);
        return convertToDto(savedMenu);
    }

    public MenuDto updateMenu(Long menuId, MenuDto menuDto) {
        return menuRepository.findById(menuId)
                .map(existingMenu -> {
                    existingMenu.setName(menuDto.getName());
                    existingMenu.setDescription(menuDto.getDescription());
                    existingMenu.setPrice(menuDto.getPrice());
                    return convertToDto(menuRepository.save(existingMenu));
                })
                .orElseThrow(() -> new RuntimeException("Menu not found with id: " + menuId));
    }

    public void deleteMenu(Long menuId) {
        menuRepository.deleteById(menuId);
    }

    private MenuDto convertToDto(Menu menu) {
        return MenuDto.builder()
                .menuId(menu.getMenuId())
                .name(menu.getName())
                .shopId(menu.getShopId())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .build();
    }

    private Menu convertToEntity(MenuDto dto) {
        return Menu.builder()
                .menuId(dto.getMenuId())
                .name(dto.getName())
                .shopId(dto.getShopId())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }
}
