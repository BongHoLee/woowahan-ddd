package org.beaoh.food.domain.shop;

import java.util.List;
import org.beaoh.base.domain.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MenuRepository extends Repository<Menu, MenuId> {
    List<Menu> findOpenMenusIn(ShopId shopId);
    List<Menu> find(ShopId shopId);
}
