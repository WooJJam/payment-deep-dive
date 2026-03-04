package co.kr.woojjam.payment_deep_dive.order.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

	// void saveAll(List<OrderItemEntity> items);

	List<OrderItemEntity> findByOrderId(String orderId);
}
