package com.delivery.server.menu.service;
import com.delivery.server.menu.domain.Menu;
import java.util.List;

public interface MenuService {
    List<Menu> getAllMenus();
    List<Menu> getMenuByMenuId(Long menuId);  // 반환 타입을 List<Menu>로 변경
    List<Menu> getMenusByShopId(Long shopId);  // 새로 추가된 메서드
    Menu createMenu(Menu menu);
    Menu updateMenu(Long menuId, Menu menuDetails);
    void deleteMenu(Long menuId);
}
