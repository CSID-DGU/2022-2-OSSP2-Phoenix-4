package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.data.entity.IngredListEntity;
import phoenix.Mymichef.data.entity.UserIngredEntity;
import phoenix.Mymichef.data.repository.IngredListRepository;
import phoenix.Mymichef.data.repository.UserIngredRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserIngredientService {

    private UserIngredRepository userIngredRepository;
    private IngredListRepository ingredListRepository;


    /**
     *  식재료 저장 서비스
     */
    public void saveUserIngred(UserIngredDto userIngredDto, String userId) {
        Optional<UserIngredEntity> find = Optional.ofNullable(userIngredRepository.findByUseridAndIngredname(userId, userIngredDto.getIngredname()));
        if (find.isEmpty()) {
            userIngredDto.setUserid(userId);
            UserIngredEntity userIngredEntity = userIngredDto.toEntity();
            userIngredRepository.save(userIngredEntity);
        } else {
            if (Float.valueOf(userIngredDto.getIngredamount()) > 0) {
                UserIngredDto finddto = find.get().toDto();
                finddto.setIngredamount(String.valueOf(Float.valueOf(finddto.getIngredamount()) + Float.valueOf(userIngredDto.getIngredamount())));
                userIngredRepository.save(finddto.toEntity());
            }
            else  {
                UserIngredDto finddto = find.get().toDto();
                if (Float.valueOf(finddto.getIngredamount()) + Float.valueOf(userIngredDto.getIngredamount()) > 0) {
                    finddto.setIngredamount(String.valueOf(Float.valueOf(finddto.getIngredamount()) + Float.valueOf(userIngredDto.getIngredamount())));
                    userIngredRepository.save(finddto.toEntity());
                }
                else {
                    userIngredRepository.delete(finddto.toEntity());
                }

            }
        }
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

    /**
     *  식재료 차감 서비스
     */

    public void UseIngred(String userid, String ingredname, String ingredamount){
        UserIngredEntity useringred = userIngredRepository.findByUseridAndIngredname(userid, ingredname);
        Float amount = Float.valueOf(useringred.getIngredamount()) - Float.valueOf(ingredamount);
        if(amount > 0) {
            useringred.setIngredamount(String.valueOf(amount));
            userIngredRepository.save(useringred);
        }
        else
            userIngredRepository.delete(useringred);
    }

    /**
     *  식재료 수정 서비스
     */

    public void ModifyIngred(String userid, String ingredname, String ingredamount){
        UserIngredEntity useringred = userIngredRepository.findByUseridAndIngredname(userid, ingredname);
        if(Float.valueOf(useringred.getIngredamount()) > 0){
            useringred.setIngredamount(ingredamount);
            userIngredRepository.save(useringred);
        }
        else
            userIngredRepository.delete(useringred);
    }

    /**
     *  저장된 식재료 확인 서비스
     */

    public ArrayList<String> IngredientNameList(){
        ArrayList<String> SaveIngredient = new ArrayList<>();
        ArrayList<IngredListEntity> Ingredient1 = ingredListRepository.findByType("주재료");
        ArrayList<IngredListEntity> Ingredient2 = ingredListRepository.findByType("부재료");
        for(int i = 0 ; i < Ingredient1.size(); i++){
            SaveIngredient.add(Ingredient1.get(i).getIngred());
        }
        for(int i = 0 ; i< Ingredient2.size(); i++){
            SaveIngredient.add(Ingredient2.get(i).getIngred());
        }
        return SaveIngredient;
    }

    public ArrayList<String> IngredientUnitList(){
        ArrayList<String> SaveIngredient = new ArrayList<>();
        ArrayList<IngredListEntity> Ingredient1 = ingredListRepository.findByType("주재료");
        ArrayList<IngredListEntity> Ingredient2 = ingredListRepository.findByType("부재료");
        for(int i = 0 ; i < Ingredient1.size(); i++){
            SaveIngredient.add(Ingredient1.get(i).getUnit());
        }
        for(int i = 0 ; i< Ingredient2.size(); i++){
            SaveIngredient.add(Ingredient2.get(i).getUnit());
        }
        return SaveIngredient;
    }

    public ArrayList<String> SeasoningNameList(){
        ArrayList<String> SaveSeasoning = new ArrayList<>();
        ArrayList<IngredListEntity> Seasoning = ingredListRepository.findByType("양념");
        for(int i = 0 ; i < Seasoning.size(); i++){
            SaveSeasoning.add(Seasoning.get(i).getIngred());
        }

        return SaveSeasoning;
    }

    public ArrayList<String> SeasoningUnitList(){
        ArrayList<String> SaveSeasoning = new ArrayList<>();
        ArrayList<IngredListEntity> Seasoning = ingredListRepository.findByType("양념");
        for(int i = 0 ; i < Seasoning.size(); i++){
            SaveSeasoning.add(Seasoning.get(i).getUnit());
        }

        return SaveSeasoning;
    }

    /**
     *  식재료 삭제 서비스
     */


    public void Deleteingred(String ingred, String userId){
        userIngredRepository.delete(userIngredRepository.findByUseridAndIngredname(userId, ingred));
    }


}
