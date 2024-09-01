package org.beaoh.food.persistence.shop;

import java.util.List;
import org.beaoh.base.jpa.BaseRepository;
import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.MenuRepository;
import org.beaoh.food.domain.shop.ShopId;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultMenuRepository extends BaseRepository<Menu, MenuId, MenuJpaRepository> implements MenuRepository {
    public DefaultMenuRepository(MenuJpaRepository repository) {
        super(repository);
    }

    @Override
    public List<Menu> findOpenMenusIn(ShopId shopId) {
        return repository.findByShopIdAndOpenIsTrue(shopId);
    }

    @Override
    public List<Menu> find(ShopId shopId) {
        return repository.findByShopId(shopId);
    }
}
