package com.delivery.server.menuoption.service;

import com.delivery.server.menu.domain.Menu;
import com.delivery.server.menu.repository.MenuRepository;
import com.delivery.server.menuoption.domain.MenuOption;
import com.delivery.server.menuoption.dto.MenuOptionDto;
import com.delivery.server.menuoption.repository.MenuOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuOptionService {
    private final MenuOptionRepository menuOptionRepository;
    private final MenuRepository menuRepository;

    @Autowired
    public MenuOptionService(MenuOptionRepository menuOptionRepository, MenuRepository menuRepository) {
        this.menuOptionRepository = menuOptionRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional
    public MenuOptionDto createMenuOption(MenuOptionDto menuOptionDto) {
        Menu menu = menuRepository.findById(menuOptionDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu not found"));

        MenuOption menuOption = MenuOption.builder()
                .menu(menu)
                .option(menuOptionDto.getOption())
                .content(menuOptionDto.getContent())
                .price(menuOptionDto.getPrice())
                .build();

        MenuOption savedMenuOption = menuOptionRepository.save(menuOption);
        return convertToDto(savedMenuOption);
    }

    @Transactional
    public MenuOptionDto updateMenuOption(Long id, MenuOptionDto menuOptionDto) {
        MenuOption menuOption = menuOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuOption not found"));

        menuOption.setOption(menuOptionDto.getOption());
        menuOption.setContent(menuOptionDto.getContent());
        menuOption.setPrice(menuOptionDto.getPrice());

        MenuOption updatedMenuOption = menuOptionRepository.save(menuOption);
        return convertToDto(updatedMenuOption);
    }

    @Transactional
    public void deleteMenuOption(Long id) {
        MenuOption menuOption = menuOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MenuOption not found"));
        menuOption.setStatus("INACTIVE");
        menuOptionRepository.save(menuOption);
    }

    @Transactional(readOnly = true)
    public List<MenuOptionDto> getMenuOptionsByMenuId(Long menuId) {
        return menuOptionRepository.findByMenuMenuId(menuId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MenuOptionDto convertToDto(MenuOption menuOption) {
        return MenuOptionDto.builder()
                .menuOptionId(menuOption.getMenuOptionId())
                .menuId(menuOption.getMenu().getMenuId())
                .option(menuOption.getOption())
                .content(menuOption.getContent())
                .price(menuOption.getPrice())
                .status(menuOption.getStatus())
                .build();
    }
}