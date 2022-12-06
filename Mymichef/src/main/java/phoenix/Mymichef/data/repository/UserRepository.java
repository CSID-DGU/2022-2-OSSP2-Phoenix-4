package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phoenix.Mymichef.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    ArrayList<UserEntity> findIdByName(String name);

    UserEntity findByUserId(String userId);
    UserEntity findByNameAndUserIdAndEmail(String name, String userId,  String email);
}
