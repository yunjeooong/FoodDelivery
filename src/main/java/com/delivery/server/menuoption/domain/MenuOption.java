package com.delivery.server.menuoption.domain;

import com.delivery.server.menu.domain.Menu;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "menu_option")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuOptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private String option;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime modifiedDate;

    @Column(nullable = false)
    private String status;


    @PrePersist // 고민이 더 필요
    protected void onCreate() {
       /* LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        modifiedDate = now;*/
        status = "ACTIVE";
    }

    @PreUpdate
    protected void onUpdate() {
        //modifiedDate = LocalDateTime.now();
    }
}