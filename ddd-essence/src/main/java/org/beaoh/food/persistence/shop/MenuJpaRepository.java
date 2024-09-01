package org.beaoh.food.persistence.shop;

import jakarta.persistence.LockModeType;
import java.util.List;
import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface MenuJpaRepository extends JpaRepository<Menu, MenuId> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    List<Menu> findByShopIdAndOpenIsTrue(ShopId shopId);

    List<Menu> findByShopId(ShopId shopId);
}
