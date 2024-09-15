package com.delivery.server.menu.dto;


import com.delivery.server.menu.domain.Menu;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuDto {
    private Long menuId;
    private String name;
    private Long menuGroupId;
    private Long shopId;
    private String description;
    private Double price;
    private Menu.MenuStatus menuStatus;
    private boolean isMainMenu;
}
