package org.beaoh.food.domain.shop;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class MenuId extends LongTypeIdentifier {
    public MenuId(Long id) {
        super(id);
    }

    public static class MenuIdJavaType extends LongTypeIdentifierJavaType<MenuId> {
        public MenuIdJavaType() {
            super(MenuId.class);
        }
    }
}
