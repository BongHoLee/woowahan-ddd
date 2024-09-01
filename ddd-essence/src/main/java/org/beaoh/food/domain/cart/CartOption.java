package org.beaoh.food.domain.cart;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.ValueObject;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.OrderOption;

@Embeddable
@Getter
public class CartOption extends ValueObject<CartOption> {

    @Column(name="NAME")
    private String name;

    @Column(name="PRICE")
    private Money price;

    @Builder
    public CartOption(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    CartOption() {
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[] { name, price };
    }

    OrderOption toOrderOption() {
        return new OrderOption(name, price);
    }
}
