package co.kr.woojjam.payment_deep_dive.user.infrastructure;

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

	public Optional<UserEntity> findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}

	public boolean existsByEmail(final String email) {
		return userRepository.existsByEmail(email);
	}

	public UserEntity save(final UserEntity userEntity) {
		return userRepository.save(userEntity);
	}
}
