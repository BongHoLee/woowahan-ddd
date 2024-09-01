package org.beaoh.food.persistence.cart;

import jakarta.persistence.LockModeType;
import org.beaoh.food.domain.cart.Cart;
import org.beaoh.food.domain.cart.CartId;
import org.beaoh.food.domain.user.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface CartJpaRepository extends JpaRepository<Cart, CartId> {

    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Cart findByUserId(UserId userId);
}
