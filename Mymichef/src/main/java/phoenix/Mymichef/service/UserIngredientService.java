package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.data.entity.UserIngredEntity;
import phoenix.Mymichef.data.repository.UserIngredRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserIngredientService {


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

    /**
     *  식재료 확인 서비스
     */
    public ArrayList<String> CheckIngredname(String userid) throws Exception{
        ArrayList<String> Ingredname = new ArrayList<>();
        ArrayList<UserIngredEntity> find = userIngredRepository.findByUserid(userid);
        if(find.isEmpty()){
            throw new Exception("등록된 재료가 없습니다.");
        }
        else {
            for (int i = 0; i < find.size() ; i ++){
                Ingredname.add(find.get(i).getIngredname());
            }
            return Ingredname;
        }
    }

    public ArrayList<String> CheckIngredamount(String userid) throws Exception{
        ArrayList<String> Ingredamount = new ArrayList<>();
        ArrayList<UserIngredEntity> find = userIngredRepository.findByUserid(userid);
        if(find.isEmpty()){
            throw new Exception("등록된 재료가 없습니다.");
        }
        else {
            for (int i = 0; i < find.size() ; i ++){
                Ingredamount.add(find.get(i).getIngredamount());
            }
            return Ingredamount;
        }
    }
}
