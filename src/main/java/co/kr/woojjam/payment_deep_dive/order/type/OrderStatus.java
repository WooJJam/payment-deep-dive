package co.kr.woojjam.payment_deep_dive.order.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderStatus {

	PENDING("결제 진행중"),
	PAID("결제 완료"),
	FAILED("결제 실패"),
	CANCELED("결제 취소");

	private final String name;
}
