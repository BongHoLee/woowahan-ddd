package org.beaoh.food.persistence.order;

import org.beaoh.base.jpa.BaseRepository;
import org.beaoh.food.domain.order.Order;
import org.beaoh.food.domain.order.OrderId;
import org.beaoh.food.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class DefaultOrderRepository extends BaseRepository<Order, OrderId, OrderJpaRepository> implements
        OrderRepository {

    public DefaultOrderRepository(OrderJpaRepository repository) {
        super(repository);
    }
}
