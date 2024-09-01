package org.beaoh.food.domain.order;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.DomainEntity;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.OrderOptionGroupId.OrderOptionGroupIdJavaType;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class OrderOptionGroup extends DomainEntity<OrderOptionGroup, OrderOptionGroupId> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(OrderOptionGroupIdJavaType.class)
    private OrderOptionGroupId id;

    @Column(name = "NAME")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "ORDER_OPTION", joinColumns = @JoinColumn(name = "ORDER_OPTION_GROUP_ID"))
    private List<OrderOption> options;

    public OrderOptionGroup(String name, List<OrderOption> options) {
        this(null, name, options);
    }

    @Builder
    OrderOptionGroup(OrderOptionGroupId id, String name, List<OrderOption> options) {
        this.id = id;
        this.name = name;
        this.options = options;
    }

    protected OrderOptionGroup() {
    }

    public Money getPrice() {
        return options.stream().map(OrderOption::getPrice).reduce(Money.ZERO, Money::plus);
    }
}
