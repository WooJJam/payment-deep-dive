package co.kr.woojjam.payment_deep_dive.order.infrastructure;

import java.time.LocalDateTime;

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
public class OrderEntity extends BaseEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String orderId;
	// @Column(nullable = false)
	private String paymentId;
	// @Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long amount;
	private String status;
	private String method;
	private LocalDateTime approvedAt;
	private String failCode;
	private String failMessage;

	@Builder
	public OrderEntity(
		final long amount,
		final LocalDateTime approvedAt, final String failCode,
		final String failMessage, final Long id, final String method,
		final String name, final String orderId, final String paymentId, final String status) {
		this.amount = amount;
		this.approvedAt = approvedAt;
		this.failCode = failCode;
		this.failMessage = failMessage;
		this.method = method;
		this.name = name;
		this.orderId = orderId;
		this.paymentId = paymentId;
		this.status = status;
	}
}
