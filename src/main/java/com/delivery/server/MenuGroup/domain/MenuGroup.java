package com.delivery.server.MenuGroup.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_groups")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class MenuGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_group_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;
}
