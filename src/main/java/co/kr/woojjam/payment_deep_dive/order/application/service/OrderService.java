package co.kr.woojjam.payment_deep_dive.order.application.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.woojjam.payment_deep_dive.order.domain.Order;
import co.kr.woojjam.payment_deep_dive.order.domain.OrderResponse;
import co.kr.woojjam.payment_deep_dive.order.presentation.web.OrderItem;
import co.kr.woojjam.payment_deep_dive.order.exception.AmountMismatchException;
import co.kr.woojjam.payment_deep_dive.order.exception.InvalidOrderException;
import co.kr.woojjam.payment_deep_dive.order.infrastructure.OrderDomainService;
import co.kr.woojjam.payment_deep_dive.order.infrastructure.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderDomainService orderDomainService;

	@Transactional
	public OrderResponse createPendingOrder(List<OrderItem> items) {
		String orderId = generateOrderId();
		String orderName = buildOrderName(items);
		long totalAmount = calculateTotalAmount(items);

		Order order = Order.of(orderId, totalAmount);
		OrderEntity entity = orderDomainService.save(order);
		log.info("주문 정보가 저장되었습니다. orderId = {}", entity.getOrderId());

		return new OrderResponse(orderId, orderName, totalAmount);
	}

	public void validateAndConsume(final String orderId, final Long amount) {
		OrderEntity orderEntity = orderDomainService.findByOrderId(orderId)
			.orElseThrow(InvalidOrderException::new);

		orderDomainService.deleteByOderId(orderId);

		validateAmount(orderEntity.getAmount(), amount);
	}

	private String generateOrderId() {
		return "ORDER_" + UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	private String buildOrderName(List<OrderItem> items) {
		return items.getFirst().name()
			+ (items.size() > 1 ? " 외 " + (items.size() - 1) + "건" : "");
	}

	private long calculateTotalAmount(List<OrderItem> items) {
		return items.stream().mapToLong(OrderItem::price).sum();
	}

	private void validateAmount(long expected, Long actual) {
		if (expected != actual) {
			throw new AmountMismatchException();
		}
	}
}
