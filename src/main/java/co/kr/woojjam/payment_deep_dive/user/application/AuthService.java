package co.kr.woojjam.payment_deep_dive.user.application;

import java.time.LocalDateTime;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.woojjam.payment_deep_dive.global.config.JwtProvider;
import co.kr.woojjam.payment_deep_dive.global.exception.BusinessException;
import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.RefreshTokenDomainService;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.RefreshTokenEntity;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.UserDomainService;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.UserEntity;
import co.kr.woojjam.payment_deep_dive.user.presentation.request.LoginRequest;
import co.kr.woojjam.payment_deep_dive.user.presentation.request.SignupRequest;
import co.kr.woojjam.payment_deep_dive.user.presentation.response.TokenResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserDomainService userDomainService;
	private final RefreshTokenDomainService refreshTokenDomainService;
	private final JwtProvider jwtProvider;
	private final BCryptPasswordEncoder passwordEncoder;

	@Transactional
	public void signup(final SignupRequest request) {
		if (userDomainService.existsByEmail(request.email())) {
			throw new BusinessException(ErrorCode.EMAIL_DUPLICATED);
		}

		UserEntity user = UserEntity.builder()
			.email(request.email())
			.password(passwordEncoder.encode(request.password()))
			.name(request.name())
			.phoneNumber(request.phoneNumber())
			.build();

		userDomainService.save(user);
	}

	@Transactional
	public TokenResponse login(final LoginRequest request) {
		UserEntity user = userDomainService.findByEmail(request.email())
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

		if (!passwordEncoder.matches(request.password(), user.getPassword())) {
			throw new BusinessException(ErrorCode.INVALID_PASSWORD);
		}

		String accessToken = jwtProvider.generateAccessToken(user.getId());
		String refreshToken = jwtProvider.generateRefreshToken(user.getId());

		// 기존 Refresh Token 제거 후 새로 저장
		refreshTokenDomainService.deleteByUserId(user.getId());
		refreshTokenDomainService.save(RefreshTokenEntity.builder()
			.userId(user.getId())
			.token(refreshToken)
			.expiresAt(LocalDateTime.now().plusSeconds(jwtProvider.getRefreshTokenExpiry() / 1000))
			.build());

		return new TokenResponse(accessToken, refreshToken);
	}

	@Transactional
	public TokenResponse refresh(final String refreshToken) {
		if (!jwtProvider.isValid(refreshToken)) {
			throw new BusinessException(ErrorCode.INVALID_TOKEN);
		}

		refreshTokenDomainService.findByToken(refreshToken)
			.orElseThrow(() -> new BusinessException(ErrorCode.TOKEN_NOT_FOUND));

		Long userId = jwtProvider.getUserId(refreshToken);
		String newAccessToken = jwtProvider.generateAccessToken(userId);

		return new TokenResponse(newAccessToken, refreshToken);
	}

	@Transactional
	public void logout(final String refreshToken) {
		refreshTokenDomainService.deleteByToken(refreshToken);
	}
}
