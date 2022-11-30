package phoenix.Mymichef.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phoenix.Mymichef.data.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

//DB인데 spring이랑 mysql 연동해서 JPA로 관리 하려고 extends JpaRepository함.
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    ArrayList<UserEntity> findIdByName(String name);
    UserEntity findByNameAndUserIdAndEmail(String name, String userId,  String email);
}
