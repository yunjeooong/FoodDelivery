package com.delivery.server.menu.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Menu {
    private Long menuId;//메뉴에 있는 음식들 고유번호
    private String name;
    private Long shopId;
    private String description;//음식 설명
    private Double price;


}
