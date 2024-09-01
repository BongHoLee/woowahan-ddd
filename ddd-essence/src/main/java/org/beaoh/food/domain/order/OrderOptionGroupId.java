package org.beaoh.food.domain.order;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OrderOptionGroupId extends LongTypeIdentifier {
    public OrderOptionGroupId(Long id) {
        super(id);
    }

    public static class OrderOptionGroupIdJavaType extends LongTypeIdentifierJavaType<OrderOptionGroupId> {
        public OrderOptionGroupIdJavaType() {
            super(OrderOptionGroupId.class);
        }
    }
}
