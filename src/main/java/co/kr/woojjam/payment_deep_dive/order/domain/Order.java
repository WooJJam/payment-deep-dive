package co.kr.woojjam.payment_deep_dive.order.domain;

public record Order(
	String orderId,
	long amount)
{
	public static Order of(final String orderId, final long amount) {
		return new Order(orderId, amount);
	}
}
