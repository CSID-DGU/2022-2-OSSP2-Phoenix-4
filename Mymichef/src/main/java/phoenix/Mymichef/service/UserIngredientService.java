package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.data.entity.UserIngredEntity;
import phoenix.Mymichef.data.repository.UserIngredRepository;

import java.util.Optional;

public class UserIngredientService {

    @Autowired
    private UserIngredRepository userIngredRepository;


    /**
     *  식재료 저장 서비스
     */
    public void saveUserIngred(UserIngredDto userIngredDto, String userId) throws Exception{
        userIngredDto.setUserid(userId);
        UserIngredEntity userIngredEntity = userIngredDto.toEntity();
        validateDuplicateUserIngred(userIngredEntity);
    }

    private void validateDuplicateUserIngred(UserIngredEntity userIngredEntity) throws Exception {
        Optional<UserIngredEntity> find = Optional.ofNullable(userIngredRepository.findByUseridAndIngredname(userIngredEntity.getUserid(), userIngredEntity.getIngredname()));
        find.ifPresent(m -> {
            throw new IllegalStateException("이미 등록 되어있는 재료입니다.");
        });
        userIngredRepository.save(userIngredEntity);
        throw new Exception("재료 등록 성공");
    }
}
