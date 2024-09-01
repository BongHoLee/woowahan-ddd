package org.beaoh.food.domain.generic.money;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.function.Function;
import org.beaoh.base.domain.ValueObject;

public class Money extends ValueObject<Money> {
    private final BigDecimal amount;
    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static final Money ZERO = wons(0);

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static <T> Money sum(Collection<T> bags, Function<T, Money> monetary) {
        return bags.stream().map(monetary).reduce(Money.ZERO, Money::plus);
    }

    public Money plus(Money amount) {
        return new Money(this.amount.add(amount.amount));
    }

    public Money minus(Money amount) {
        return new Money(this.amount.subtract(amount.amount));
    }

    public Money times(double percent) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(percent)));
    }

    public Money divide(double divisor) {
        return new Money(this.amount.divide(BigDecimal.valueOf(divisor)));
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo(other.amount) < 0;
    }

    public boolean isGreaterThanEqual(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Long longValue() {
        return amount.longValue();
    }

    public Double doubleValue() {
        return amount.doubleValue();
    }

    public String toString() {
        return amount.toString() + "원";
    }

    @Override
    protected Object[] getEqualityFields() {
        return new Object[] { amount.doubleValue() };
    }
}
