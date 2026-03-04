package co.kr.woojjam.payment_deep_dive.order.exception;

public class AmountMismatchException extends RuntimeException {

	public AmountMismatchException() {
		super("결제 금액이 주문 금액과 일치하지 않습니다.");
	}
}
