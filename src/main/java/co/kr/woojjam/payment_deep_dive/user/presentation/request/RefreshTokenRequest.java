package co.kr.woojjam.payment_deep_dive.user.presentation.request;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequest(
	@NotBlank String refreshToken
) {
}
