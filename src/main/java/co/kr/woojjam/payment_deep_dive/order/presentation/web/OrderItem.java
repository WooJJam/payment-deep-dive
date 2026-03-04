package co.kr.woojjam.payment_deep_dive.order.presentation.web;

public record OrderItem(
	String name,
	long price,
	int amount) {
}
