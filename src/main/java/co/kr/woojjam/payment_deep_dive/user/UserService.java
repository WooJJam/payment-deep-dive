package co.kr.woojjam.payment_deep_dive.user;

import org.springframework.stereotype.Service;

import ch.qos.logback.classic.spi.IThrowableProxy;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDomainService userDomainService;

	public UserEntity readUser(final Long id) {
		return userDomainService.readById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저"));
	}
}
