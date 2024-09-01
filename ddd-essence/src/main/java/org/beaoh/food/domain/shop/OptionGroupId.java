package org.beaoh.food.domain.shop;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class OptionGroupId extends LongTypeIdentifier {
    public OptionGroupId(Long id) {
        super(id);
    }

    public static class OptionGroupIdJavaType extends LongTypeIdentifierJavaType<OptionGroupId> {
        public OptionGroupIdJavaType() {
            super(OptionGroupId.class);
        }
    }
}
