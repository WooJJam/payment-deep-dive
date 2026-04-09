package co.kr.woojjam.payment_deep_dive.user.presentation.response;

public record TokenResponse(
	String accessToken,
	String refreshToken
) {
}
