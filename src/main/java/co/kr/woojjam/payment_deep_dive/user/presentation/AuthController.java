package co.kr.woojjam.payment_deep_dive.user.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.kr.woojjam.payment_deep_dive.global.auth.Auth;
import co.kr.woojjam.payment_deep_dive.global.common.response.ApiResponse;
import co.kr.woojjam.payment_deep_dive.user.application.AuthService;
import co.kr.woojjam.payment_deep_dive.user.presentation.request.LoginRequest;
import co.kr.woojjam.payment_deep_dive.user.presentation.request.RefreshTokenRequest;
import co.kr.woojjam.payment_deep_dive.user.presentation.request.SignupRequest;
import co.kr.woojjam.payment_deep_dive.user.presentation.response.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	@ResponseStatus(HttpStatus.CREATED)
	public ApiResponse<Void> signup(@RequestBody @Valid SignupRequest request) {
		authService.signup(request);
		return ApiResponse.ok();
	}

	@PostMapping("/login")
	public ApiResponse<TokenResponse> login(@RequestBody @Valid LoginRequest request) {
		return ApiResponse.ok(authService.login(request));
	}

	@PostMapping("/refresh")
	@Auth
	public ApiResponse<TokenResponse> refresh(@RequestBody @Valid RefreshTokenRequest request) {
		return ApiResponse.ok(authService.refresh(request.refreshToken()));
	}

	@PostMapping("/logout")
	@Auth
	public ApiResponse<Void> logout(@RequestBody @Valid RefreshTokenRequest request) {
		authService.logout(request.refreshToken());
		return ApiResponse.ok();
	}
}
