package org.beaoh.food.domain.cart;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.DomainEntity;
import org.beaoh.food.domain.cart.CartLineItemId.CartLineItemIdJavaType;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.OrderLineItem;
import org.beaoh.food.domain.shop.Menu;
import org.beaoh.food.domain.shop.MenuId;
import org.beaoh.food.domain.shop.MenuId.MenuIdJavaType;
import org.beaoh.food.domain.order.OrderOptionGroup;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class CartLineItem extends DomainEntity<CartLineItem, CartLineItemId> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(CartLineItemIdJavaType.class)
    private CartLineItemId id;

    @Column(name = "MENU_ID")
    @JavaType(MenuIdJavaType.class)
    private MenuId menuId;

    @Column(name = "MENU_NAME")
    private String menuName;

    @Column(name = "MENU_COUNT")
    private int menuCount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "CART_LINE_ITEM_ID")
    private List<CartOptionGroup> groups = new ArrayList<>();

    public CartLineItem(Menu menu, int count, CartOptionGroup ... groups) {
        this(null, menu.getId(), menu.getName(), count, Arrays.asList(groups));
    }

    public CartLineItem(MenuId menuId, String menuName, int count, CartOptionGroup ... groups) {
        this(null, menuId, menuName, count, Arrays.asList(groups));
    }

    @Builder
    public CartLineItem(CartLineItemId id, MenuId menuId, String menuName, int count, List<CartOptionGroup> groups) {
        this.id = id;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuCount = count;
        this.groups.addAll(groups);
    }

    CartLineItem() {
    }

    public void combine(CartLineItem other) {
        this.menuCount += other.getMenuCount();
    }

    public void addCartOptionGroup(CartOptionGroup cartOptionGroup) {
        groups.add(cartOptionGroup);
    }

    public boolean equalsNested(CartLineItem other) {
        if (!this.menuId.equals(other.getMenuId())) {
            return false;
        }

        for(CartOptionGroup thisGroup : groups) {
            if (!other.getGroups().stream().anyMatch(thatGroup -> thatGroup.equalsNested(thisGroup))) {
                return false;
            }
        }

        return true;
    }

    public Money getTotalPrice() {
        return groups.stream()
                .map(CartOptionGroup::getTotalPrice)
                .reduce(Money.ZERO, Money::plus)
                .times(menuCount);
    }

    OrderLineItem toOrderLineItem() {
        return new OrderLineItem(menuId, menuName, menuCount, toOrderOptionGroups());
    }

    private List<OrderOptionGroup> toOrderOptionGroups() {
        return groups.stream()
                .map(CartOptionGroup::toOrderOptionGroup)
                .collect(Collectors.toList());
    }
}
