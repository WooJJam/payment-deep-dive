package co.kr.woojjam.payment_deep_dive.user;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDomainService {

	private final UserRepository userRepository;

	public Optional<UserEntity> readById(final Long id) {
		return userRepository.findById(id);
	}
}
