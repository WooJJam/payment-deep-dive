package co.kr.woojjam.payment_deep_dive.order.exception;

public class InvalidOrderException extends RuntimeException {

	public InvalidOrderException() {
		super("유효하지 않거나 만료된 주문번호입니다.");
	}
}
