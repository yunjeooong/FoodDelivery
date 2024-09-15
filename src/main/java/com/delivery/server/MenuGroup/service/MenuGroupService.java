package com.delivery.server.MenuGroup.service;


import com.delivery.server.MenuGroup.domain.MenuGroup;
import com.delivery.server.MenuGroup.dto.MenuGroupDto;
import com.delivery.server.MenuGroup.repository.MenuGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    @Autowired
    public MenuGroupService(MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    public MenuGroupDto createMenuGroup(Long restaurantId, MenuGroupDto menuGroupDto) {
        MenuGroup menuGroup = convertToEntity(menuGroupDto);
        menuGroup.setRestaurantId(restaurantId);
        MenuGroup savedMenuGroup = menuGroupRepository.save(menuGroup);
        return convertToDto(savedMenuGroup);
    }

    public MenuGroupDto getMenuGroup(Long menuGroupId) {
        return menuGroupRepository.findById(menuGroupId)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Menu group not found with id: " + menuGroupId));
    }

    public List<MenuGroupDto> getMenuGroupsByRestaurant(Long restaurantId) {
        return menuGroupRepository.findByRestaurantId(restaurantId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MenuGroupDto updateMenuGroup(Long menuGroupId, MenuGroupDto menuGroupDto) {
        return menuGroupRepository.findById(menuGroupId)
                .map(existingMenuGroup -> {
                    existingMenuGroup.setTitle(menuGroupDto.getTitle());
                    existingMenuGroup.setDescription(menuGroupDto.getDescription());
                    return convertToDto(menuGroupRepository.save(existingMenuGroup));
                })
                .orElseThrow(() -> new RuntimeException("Menu group not found with id: " + menuGroupId));
    }

    public void deleteMenuGroup(Long menuGroupId) {
        menuGroupRepository.deleteById(menuGroupId);
    }

    private MenuGroupDto convertToDto(MenuGroup menuGroup) {
        return MenuGroupDto.builder()
                .id(menuGroup.getId())
                .title(menuGroup.getTitle())
                .description(menuGroup.getDescription())
                .restaurantId(menuGroup.getRestaurantId())
                .build();
    }

    private MenuGroup convertToEntity(MenuGroupDto dto) {
        return MenuGroup.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .restaurantId(dto.getRestaurantId())
                .build();
    }
}
