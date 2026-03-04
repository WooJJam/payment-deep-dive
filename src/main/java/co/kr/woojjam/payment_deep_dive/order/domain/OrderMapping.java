package co.kr.woojjam.payment_deep_dive.order.domain;

import co.kr.woojjam.payment_deep_dive.order.infrastructure.OrderEntity;

public class OrderMapping {

	public static OrderEntity toEntity(final Order order) {
		return OrderEntity.builder()
			.orderId(order.orderId())
			.amount(order.amount())
			.build();
	}
}
