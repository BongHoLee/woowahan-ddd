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
import jakarta.persistence.Version;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.AggregateRoot;
import org.beaoh.food.domain.cart.CartId.CartIdJavaType;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.Order;
import org.beaoh.food.domain.order.OrderLineItem;
import org.beaoh.food.domain.shop.Shop;
import org.beaoh.food.domain.shop.ShopId;
import org.beaoh.food.domain.user.UserId;
import org.beaoh.food.domain.user.UserId.UserIdJavaType;
import org.hibernate.annotations.JavaType;

@Entity
@Getter
public class Cart extends AggregateRoot<Cart, CartId> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(CartIdJavaType.class)
    private CartId id;

    @Version
    private Long version;

    @Column(name = "USER_ID")
    @JavaType(UserIdJavaType.class)
    private UserId userId;

    @Column(name = "SHOP_ID")
    private ShopId shopId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="CART_ID")
    private List<CartLineItem> items = new ArrayList<>();

    public Cart(UserId userId, ShopId shopId, List<CartLineItem> items) {
        this(null, userId, shopId, items);
    }

    @Builder
    public Cart(CartId id, UserId userId, ShopId shopId, List<CartLineItem> items) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.items = items;
    }

    public Cart() {}

    public Cart(UserId userId) {
        this.userId = userId;
    }

    public void addItem(Shop shop, CartLineItem cartLineItem) {
        if (shop == null) {
            throw new IllegalArgumentException("가게는 null이 아니어야 합니다.");
        }

        if (cartLineItem == null) {
            throw new IllegalArgumentException("카트 아이템은 null이 아니어야 합니다.");
        }

        if (!shop.isOpen()) {
            throw new IllegalStateException("가게가 영업중이어야 합니다");
        }

        if (items.isEmpty()) {
            this.shopId = shop.getId();
        }

        if (!isContains(shop.getId())) {
            start(shop);
        }

        if (find(cartLineItem).isEmpty()) {
            items.add(cartLineItem);
        } else {
            find(cartLineItem).get().combine(cartLineItem);
        }
    }

    private Optional<CartLineItem> find(CartLineItem cartLineItem) {
        return items.stream().filter(item -> item.equalsNested(cartLineItem)).findFirst();
    }

    public Money getTotalPrice() {
        return items.stream().map(CartLineItem::getTotalPrice).reduce(Money.ZERO, Money::plus);
    }

    public Order placeOrder() {
        return new Order(userId, shopId, toOrderLineItems());
    }

    private List<OrderLineItem> toOrderLineItems() {
        return items.stream().map(CartLineItem::toOrderLineItem).collect(Collectors.toList());
    }

    private boolean isContains(ShopId shopId) {
        return this.shopId != null && this.shopId.equals(shopId);
    }

    private void start(Shop shop) {
        this.shopId = shop.getId();
        this.items.clear();
    }
}
