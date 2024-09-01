package org.beaoh.food.persistence.shop;

import org.beaoh.food.domain.shop.Shop;
import org.beaoh.food.domain.shop.ShopId;
import org.springframework.data.jpa.repository.JpaRepository;

interface ShopJpaRepository extends JpaRepository<Shop, ShopId> {}
