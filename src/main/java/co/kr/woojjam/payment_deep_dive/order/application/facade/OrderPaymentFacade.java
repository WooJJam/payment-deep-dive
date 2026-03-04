package co.kr.woojjam.payment_deep_dive.order.application.facade;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.kr.woojjam.payment_deep_dive.order.domain.OrderResponse;
import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;
import co.kr.woojjam.payment_deep_dive.order.presentation.request.CheckoutRequest;
import co.kr.woojjam.payment_deep_dive.order.application.service.OrderService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderPaymentFacade {

	private final OrderService orderService;

	@Transactional
	public CheckoutRequest prepareCheckout() {

		List<OrderItem> items = List.of(
			new OrderItem("티셔츠", 1_000, 1),
			new OrderItem("토스 머그컵", 500, 1),
			new OrderItem("볼펜", 300, 1)
		);

		OrderResponse info = orderService.createPendingOrder(items);
		return new CheckoutRequest(
			info.orderId(),
			info.orderName(),
			"이메일",
			"이름",
			"01012345678",
			items,
			info.totalAmount()
		);
	}

	public void validatePayment(String orderId, Long amount) {
		orderService.validateAndConsume(orderId, amount);
	}
}
