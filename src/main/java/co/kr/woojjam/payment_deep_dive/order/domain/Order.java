package co.kr.woojjam.payment_deep_dive.order.domain;

import java.util.List;

import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;

public record Order(
	String orderId,
	long amount,
	List<OrderItem> items
) {
	public static Order of(final String orderId, final long amount, final List<OrderItem> items) {
		return new Order(orderId, amount, items);
	}
}
