package co.kr.woojjam.payment_deep_dive.order.infrastructure;

import co.kr.woojjam.payment_deep_dive.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String orderId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private long price;

	@Column(nullable = false)
	private int quantity;

	@Builder
	public OrderItemEntity(final String orderId, final String name, final long price, final int quantity) {
		this.orderId = orderId;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}
