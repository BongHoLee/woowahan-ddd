package org.beaoh.food.domain.shop;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.Version;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.AggregateRoot;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.generic.time.TimePeriod;
import org.beaoh.food.domain.shop.ShopId.ShopIdJavaType;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class Shop extends AggregateRoot<Shop, ShopId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(ShopIdJavaType.class)
    private ShopId id;

    @Version
    private Long version;

    @Column(name="NAME")
    private String name;

    @Column(name="MIN_ORDER_AMOUNT")
    private Money minOrderPrice;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "OPERATION_HOURS", joinColumns = @JoinColumn(name = "SHOP_ID"))
    @MapKeyColumn(name = "DAY_OF_WEEK")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayOfWeek, TimePeriod> operatingHours;

    public boolean isOpen() {
        return isOpen(LocalDateTime.now());
    }

    public Shop(String name, boolean open, Money minOrderPrice, Map<DayOfWeek, TimePeriod> operatingHours) {
        this(null, name, open, minOrderPrice, operatingHours);
    }

    @Builder
    public Shop(ShopId id, String name, boolean open, Money minOrderPrice, Map<DayOfWeek, TimePeriod> operatingHours) {
        this.id = id;
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.operatingHours = operatingHours;
    }

    protected Shop() {
    }

    public boolean isOpen(LocalDateTime time) {
        if (!operatingHours.containsKey(time.getDayOfWeek())) {
            return false;
        }

        return operatingHours.get((time.getDayOfWeek())).contains(time.toLocalTime());
    }

    public void putOffOneHourOn(DayOfWeek dayOfWeek) {
        if (!operatingHours.containsKey(dayOfWeek)) {
            return;
        }

        TimePeriod period = operatingHours.get(dayOfWeek);
        operatingHours.put(dayOfWeek, period.putOffHours(1));
    }
}
