package com.delivery.server.menu.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menus")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long menuId;

    @Column(nullable = false)
    private String name;

    @Column(name = "menu_group_id", nullable = false,length = 50)
    private Long menuGroupId;  // menuGroupId 추가

    @Column(name = "shop_id", nullable = false)
    private Long shopId;  // 아직 shop 엔티티를 참조하지 않아서 임시로

    @Column(length=1000)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MenuStatus menuStatus;

    @Column(nullable = false)
    private boolean isMainMenu;

    public enum MenuStatus {
        ACTIVE,SALE, SOLD_OUT, HIDDEN
    }

    /*  shop 구현 후
    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "shop_id")
private Shop shop;
     */
}