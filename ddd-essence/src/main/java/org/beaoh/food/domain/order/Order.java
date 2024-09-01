package org.beaoh.food.domain.order;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import org.beaoh.base.domain.AggregateRoot;
import org.beaoh.food.domain.generic.money.Money;
import org.beaoh.food.domain.order.OrderId.OrderIdJavaType;
import org.beaoh.food.domain.shop.ShopId;
import org.beaoh.food.domain.shop.ShopId.ShopIdJavaType;
import org.beaoh.food.domain.user.UserId;
import org.beaoh.food.domain.user.UserId.UserIdJavaType;
import org.hibernate.annotations.JavaType;

@Entity
@Table(name = "ORDERS")
@Getter
public class Order extends AggregateRoot<Order, OrderId> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JavaType(OrderIdJavaType.class)
    private OrderId id;

    @Column(name = "USER_ID")
    @JavaType(UserIdJavaType.class)
    private UserId userId;

    @Column(name = "SHOP_ID")
    @JavaType(ShopIdJavaType.class)
    private ShopId shopId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name = "ORDERED_TIME")
    private LocalDateTime orderedTime;

    public Order(UserId userId, ShopId shopId, List<OrderLineItem> items) {
        this(null, userId, shopId, items, LocalDateTime.now());
    }

    @Builder
    public Order(OrderId id, UserId userId, ShopId shopId, List<OrderLineItem> items, LocalDateTime orderedTime) {
        this.id = id;
        this.userId = userId;
        this.shopId = shopId;
        this.orderedTime = orderedTime;
        this.orderLineItems = items;
    }

    protected Order() {
    }

    public Money getPrice() {
        return orderLineItems.stream().map(orderLineItem -> orderLineItem.getPrice()).reduce(Money.ZERO, Money::plus);
    }
}
