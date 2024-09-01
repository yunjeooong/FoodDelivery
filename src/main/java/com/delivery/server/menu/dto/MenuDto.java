package com.delivery.server.menu.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuDto {
    private Long menuId;
    private String name;
    private Long shopId;
    private String description;
    private Double price;
}
