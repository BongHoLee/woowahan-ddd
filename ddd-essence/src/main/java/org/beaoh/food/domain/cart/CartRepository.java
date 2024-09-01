package org.beaoh.food.domain.cart;

import org.beaoh.base.domain.Repository;
import org.beaoh.food.domain.user.UserId;

public interface CartRepository extends Repository<Cart, CartId> {
    Cart find(UserId userId);
}
