package org.beaoh.food.domain.order;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderId extends LongTypeIdentifier {
    public OrderId(Long id) {
        super(id);
    }

    public static class OrderIdJavaType extends LongTypeIdentifierJavaType<OrderId> {
        public OrderIdJavaType() {
            super(OrderId.class);
        }
    }
}
