package co.kr.woojjam.payment_deep_dive.order.domain;

public record OrderResponse(String orderId, String orderName, long totalAmount) {
}
