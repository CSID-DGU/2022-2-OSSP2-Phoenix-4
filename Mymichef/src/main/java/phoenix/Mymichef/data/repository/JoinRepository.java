package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phoenix.Mymichef.data.entity.JoinEntity;

@Repository
public interface JoinRepository extends JpaRepository<JoinEntity, String> {

}
