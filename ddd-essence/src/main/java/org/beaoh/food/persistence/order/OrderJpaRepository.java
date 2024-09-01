package org.beaoh.food.persistence.order;

import org.beaoh.food.domain.order.Order;
import org.beaoh.food.domain.order.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderJpaRepository extends JpaRepository<Order, OrderId> {
}
