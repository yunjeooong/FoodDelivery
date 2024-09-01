package com.delivery.server.menuoption.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MenuOptionDto {
    private Long menuOptionId;
    private Long menuId;
    private String option;
    private String content;
    private int price;
    private String status;
}