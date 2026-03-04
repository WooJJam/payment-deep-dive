package co.kr.woojjam.payment_deep_dive.order.domain;

import java.util.List;

import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;
import co.kr.woojjam.payment_deep_dive.order.type.OrderStatus;

public record Order(
	String orderId,
	long amount,
	OrderStatus status,
	List<OrderItem> items
) {
	public static Order of(final String orderId, final long amount, final OrderStatus status, final List<OrderItem> items) {
		return new Order(orderId, amount, status, items);
	}
}
