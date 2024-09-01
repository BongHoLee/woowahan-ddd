package org.beaoh.food.domain.cart;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class CartOptionGroupId extends LongTypeIdentifier {
    public CartOptionGroupId(Long id) {
        super(id);
    }

    public CartOptionGroupId next() {
        return new CartOptionGroupId(nextValue());
    }

    public static class CartOptionGroupIdJavaType extends LongTypeIdentifierJavaType<CartOptionGroupId> {
        public CartOptionGroupIdJavaType() {
            super(CartOptionGroupId.class);
        }
    }
}
