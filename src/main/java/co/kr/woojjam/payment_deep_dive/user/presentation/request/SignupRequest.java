package co.kr.woojjam.payment_deep_dive.user.presentation.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
	@NotBlank @Email String email,
	@NotBlank @Size(min = 8) String password,
	@NotBlank String name,
	@NotBlank String phoneNumber
) {
}
