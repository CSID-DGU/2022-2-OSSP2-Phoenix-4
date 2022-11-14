package phoenix.Mymichef.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phoenix.Mymichef.domain.entitiy.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByEmail(String userEmail);
}
