package org.beaoh.food.domain.shop;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class ShopId extends LongTypeIdentifier {
    public ShopId(Long id) {
        super(id);
    }

    public static class ShopIdJavaType extends LongTypeIdentifierJavaType<ShopId> {
        public ShopIdJavaType() {
            super(ShopId.class);
        }
    }
}
