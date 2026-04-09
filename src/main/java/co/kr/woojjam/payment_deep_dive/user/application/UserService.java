package co.kr.woojjam.payment_deep_dive.user.application;

import org.springframework.stereotype.Service;

import co.kr.woojjam.payment_deep_dive.global.exception.BusinessException;
import co.kr.woojjam.payment_deep_dive.global.exception.ErrorCode;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.UserDomainService;
import co.kr.woojjam.payment_deep_dive.user.infrastructure.UserEntity;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserDomainService userDomainService;

	public UserEntity readUser(final Long id) {
		return userDomainService.readById(id)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
	}
}
