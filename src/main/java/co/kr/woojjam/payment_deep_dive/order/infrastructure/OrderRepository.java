package co.kr.woojjam.payment_deep_dive.order.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

	OrderEntity save(final OrderEntity order);

	Optional<OrderEntity> findByOrderId(final String orderId);

	void deleteByOrderId(final String orderId);
}
