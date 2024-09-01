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

    @Column(name = "shop_id", nullable = false)
    private Long shopId;  // 아직 shop 엔티티를 참조하지 않아서 임시로

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    /*  shop 구현 후
    @ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "shop_id")
private Shop shop;
     */
}