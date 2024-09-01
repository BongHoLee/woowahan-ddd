package org.beaoh.food.persistence.cart;

import org.beaoh.base.jpa.BaseRepository;
import org.beaoh.food.domain.cart.Cart;
import org.beaoh.food.domain.cart.CartId;
import org.beaoh.food.domain.cart.CartRepository;
import org.beaoh.food.domain.user.UserId;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CartRepositoryImpl extends BaseRepository<Cart, CartId, CartJpaRepository> implements CartRepository {

    public CartRepositoryImpl(CartJpaRepository repository) {
        super(repository);
    }

    @Override
    @Transactional
    public Cart find(UserId userId) {
        return repository.findByUserId(userId);
    }
}
