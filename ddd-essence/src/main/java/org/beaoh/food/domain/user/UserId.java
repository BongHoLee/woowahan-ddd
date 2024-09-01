package org.beaoh.food.domain.user;

import org.beaoh.base.domain.LongTypeIdentifier;
import org.beaoh.base.jpa.hibernate.LongTypeIdentifierJavaType;

public class UserId extends LongTypeIdentifier {
    public UserId(Long id) {
        super(id);
    }

    public static class UserIdJavaType extends LongTypeIdentifierJavaType<UserId> {
        public UserIdJavaType() {
            super(UserId.class);
        }
    }
}
