package co.kr.woojjam.payment_deep_dive.order.domain;

import java.util.List;

import co.kr.woojjam.payment_deep_dive.order.infrastructure.OrderEntity;
import co.kr.woojjam.payment_deep_dive.order.infrastructure.OrderItemEntity;
import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;

public class OrderMapping {

	public static OrderEntity toEntity(final Order order) {
		return OrderEntity.builder()
			.orderId(order.orderId())
			.amount(order.amount())
			.status(order.status())
			.build();
	}

	public static List<OrderItemEntity> toItemEntities(final Order order) {
		return order.items().stream()
			.map(item -> OrderItemEntity.builder()
				.orderId(order.orderId())
				.name(item.name())
				.price(item.price())
				.quantity(item.amount())
				.build())
			.toList();
	}
}
