package org.beaoh.food.domain.cart;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class CartLineItemId extends LongTypeIdentifier {
    public CartLineItemId(Long id) {
        super(id);
    }

    public static class CartLineItemIdJavaType extends LongTypeIdentifierJavaType<CartLineItemId> {
        public CartLineItemIdJavaType() {
            super(CartLineItemId.class);
        }
    }
}
