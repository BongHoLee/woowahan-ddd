package org.beaoh.food.domain.order;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.DomainEntity;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.OrderLineItemId.OrderLineItemIdJavaType;
import org.beaoh.food.domain.shop.MenuId;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class OrderLineItem extends DomainEntity<OrderLineItem, OrderLineItemId> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JavaType(OrderLineItemIdJavaType.class)
    private OrderLineItemId id;

    @Column(name = "MENU_ID")
    @JavaType(MenuId.MenuIdJavaType.class)
    private MenuId menuId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "COUNT")
    private int count;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "ORDER_LINE_ITEM_ID")
    private List<OrderOptionGroup> groups = new ArrayList<>();

    public OrderLineItem(MenuId menuId, String menuName, int count, List<OrderOptionGroup> groups) {
        this(null, menuId, menuName, count, groups);
    }

    @Builder
    public OrderLineItem(OrderLineItemId id, MenuId menuId, String menuName, int count, List<OrderOptionGroup> groups) {
        this.id = id;
        this.menuId = menuId;
        this.menuName = menuName;
        this.count = count;
        this.groups.addAll(groups);
    }

    protected OrderLineItem() {
    }

    public Money getPrice() {
        return groups.stream().map(group -> group.getPrice()).reduce(Money.ZERO, Money::plus);
    }
}
