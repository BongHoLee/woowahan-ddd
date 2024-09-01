package org.beaoh.food.domain.order;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderLineItemId extends LongTypeIdentifier {
    public OrderLineItemId(Long id) {
        super(id);
    }

    public static class OrderLineItemIdJavaType extends LongTypeIdentifierJavaType<OrderLineItemId> {
        public OrderLineItemIdJavaType() {
            super(OrderLineItemId.class);
        }
    }
}
