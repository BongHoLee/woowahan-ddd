package org.beaoh.food.service.shop;

import java.util.List;
import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuRepository;
import org.beaoh.food.domain.shop.Shop;
import org.beaoh.food.domain.shop.ShopId;
import org.beaoh.food.domain.shop.ShopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopService {
    private ShopRepository shopRepository;
    private MenuRepository menuRepository;

    public ShopService(ShopRepository shopRepository, MenuRepository menuRepository) {
        this.shopRepository = shopRepository;
        this.menuRepository = menuRepository;
    }

    @Transactional(readOnly = true)
    public MenuBoard getMenuBoard(ShopId shopId) {
        Shop shop = shopRepository.find(shopId);
        List<Menu> menus = menuRepository.find(shopId);

        return new MenuBoard(shop, menus);
    }
}
