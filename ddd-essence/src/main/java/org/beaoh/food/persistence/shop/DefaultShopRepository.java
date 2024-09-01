package org.beaoh.food.persistence.shop;

import org.beaoh.base.jpa.BaseRepository;
import org.beaoh.food.domain.shop.Shop;
import org.beaoh.food.domain.shop.ShopId;
import org.beaoh.food.domain.shop.ShopRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultShopRepository extends BaseRepository<Shop, ShopId, ShopJpaRepository> implements ShopRepository {
    public DefaultShopRepository(ShopJpaRepository repository) {
        super(repository);
    }
}
