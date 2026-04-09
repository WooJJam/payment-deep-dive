package co.kr.woojjam.payment_deep_dive.user.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.kr.woojjam.payment_deep_dive.user.infrastructure.RefreshTokenEntity;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenDomainService {

	private final RefreshTokenRepository refreshTokenRepository;

	public RefreshTokenEntity save(final RefreshTokenEntity entity) {
		return refreshTokenRepository.save(entity);
	}

	public Optional<RefreshTokenEntity> findByToken(final String token) {
		return refreshTokenRepository.findByToken(token);
	}

	@Transactional
	public void deleteByUserId(final Long userId) {
		refreshTokenRepository.deleteByUserId(userId);
	}

	@Transactional
	public void deleteByToken(final String token) {
		refreshTokenRepository.findByToken(token)
			.ifPresent(refreshTokenRepository::delete);
	}
}
