package co.kr.woojjam.payment_deep_dive.user.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
