package co.kr.woojjam.payment_deep_dive.user.infrastructure;

import java.time.LocalDateTime;

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
public class RefreshTokenEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long userId;

	@Column(nullable = false, unique = true, length = 512)
	private String token;

	private LocalDateTime expiresAt;

	@Builder
	public RefreshTokenEntity(final Long userId, final String token, final LocalDateTime expiresAt) {
		this.userId = userId;
		this.token = token;
		this.expiresAt = expiresAt;
	}
}
