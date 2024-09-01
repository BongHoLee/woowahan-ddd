package org.beaoh.food.domain.cart;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class CartId extends LongTypeIdentifier {
    public static CartId of(Long id) {
        return new CartId(id);
    }

    public CartId(Long id) {
        super(id);
    }

    public static class CartIdJavaType extends LongTypeIdentifierJavaType<CartId> {
        public CartIdJavaType() {
            super(CartId.class);
        }
    }
}