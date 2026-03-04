package co.kr.woojjam.payment_deep_dive.order.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.woojjam.payment_deep_dive.order.domain.Order;
import co.kr.woojjam.payment_deep_dive.order.domain.OrderMapping;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderDomainService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Transactional
	public OrderEntity save(final Order order) {
		OrderEntity entity = OrderMapping.toEntity(order);
		return orderRepository.save(entity);
	}

	@Transactional
	public void saveItems(final Order order) {
		List<OrderItemEntity> items = OrderMapping.toItemEntities(order);
		orderItemRepository.saveAll(items);
	}

	@Transactional
	public Optional<OrderEntity> findByOrderId(final String orderId) {
		return orderRepository.findByOrderId(orderId);
	}

	@Transactional
	public void deleteByOderId(final String orderId) {
		orderRepository.deleteByOrderId(orderId);
	}
}
