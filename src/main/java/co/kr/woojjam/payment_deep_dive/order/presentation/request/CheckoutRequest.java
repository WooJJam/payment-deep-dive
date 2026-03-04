package co.kr.woojjam.payment_deep_dive.order.presentation.request;

import java.util.List;

import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;

public record CheckoutRequest(
	String orderId,
	String orderName,
	String customerEmail,
	String customerName,
	String customerMobilePhone,
	List<OrderItem> items,
	long totalAmount
) {
}
