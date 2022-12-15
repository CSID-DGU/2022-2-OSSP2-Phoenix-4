package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.IngredDTO;
import phoenix.Mymichef.data.dto.UserIngredDto;
import phoenix.Mymichef.data.dto.UserShoppingDto;
import phoenix.Mymichef.data.entity.IngredEntity;
import phoenix.Mymichef.data.entity.UserDietEntity;
import phoenix.Mymichef.data.entity.UserIngredEntity;
import phoenix.Mymichef.data.entity.UserShoppingEntity;
import phoenix.Mymichef.data.repository.*;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserShoppingService {

    private UserIngredRepository userIngredRepository;
    private IngredRepository cookingIngredRepository;
    private UserShoppingRepository userShoppingRepository;
    private CookingInfoRepository cookingInfoRepository;
    private UserDietRepository userDietRepository;


    /**
     * 장바구니 불러오기 서비스
     */

    public ArrayList<String> CheckShoppingname(String userid) throws Exception {
        ArrayList<String> Ingredname = new ArrayList<>();
        ArrayList<UserShoppingEntity> find = userShoppingRepository.findByUserid(userid);
        if(find.isEmpty()){
            throw new Exception("등록된 장바구니가 없습니다.");
        }
        else {
            for (int i = 0; i < find.size() ; i ++){
                Ingredname.add(find.get(i).getIngred());
            }
            return Ingredname;
        }
    }

    public ArrayList<String> CheckShoppingamount(String userid) throws Exception {
        ArrayList<String> Ingredamount = new ArrayList<>();
        ArrayList<UserShoppingEntity> find = userShoppingRepository.findByUserid(userid);
        if(find.isEmpty()){
            throw new Exception("등록된 장바구니가 없습니다.");
        }
        else {
            for (int i = 0; i < find.size() ; i ++){
                Ingredamount.add(find.get(i).getAmount());
            }
            return Ingredamount;
        }
    }

    public ArrayList<String> CheckShoppingunit(String userid) throws Exception {
        ArrayList<String> Ingredunit = new ArrayList<>();
        ArrayList<UserShoppingEntity> find = userShoppingRepository.findByUserid(userid);
        if(find.isEmpty()){
            throw new Exception("등록된 장바구니가 없습니다.");
        }
        else {
            for (int i = 0; i < find.size() ; i ++){
                Ingredunit.add(find.get(i).getUnit());
            }
            return Ingredunit;
        }
    }

    /**
     * 식단에 등록된 레시피 받아오는 서비스
     */
    public ArrayList<String> ReturnRecipe(String userid){
        ArrayList<String> recipelist = new ArrayList<>();
        ArrayList<UserDietEntity> userdiet = userDietRepository.findByUserid(userid);
        for(int i = 0 ; i< userdiet.size(); i++){
            recipelist.add(userdiet.get(i).getRecipenm());
        }
        return recipelist;
    }


    /**
     * 장바구니 식재료에 담기 서비스
     */

    public void ShoppingToIngred(String userId, String ingredname) throws Exception {
        Optional<UserShoppingEntity> usershopping = userShoppingRepository.findByUseridAndIngred(userId, ingredname);
        Optional<UserIngredEntity> userIngred = Optional.ofNullable(userIngredRepository.findByUseridAndIngredname(userId, usershopping.get().getIngred()));
        if (userIngred.isEmpty()) {
            if (Float.valueOf(usershopping.get().getAmount()) >= 0) {
                UserIngredDto userIngredDto = new UserIngredDto();
                userIngredDto.setUserid(userId);
                userIngredDto.setIngredname(usershopping.get().getIngred());
                userIngredDto.setIngredamount(usershopping.get().getAmount());
                userIngredRepository.save(userIngredDto.toEntity());
                userShoppingRepository.delete(usershopping.get());
            } else {
                throw new Exception("장바구니 수량 오류");
            }
        } else {
            if (Float.valueOf(usershopping.get().getAmount()) >= 0) {
                UserIngredDto userIngredDto = userIngred.get().toDto();
                userIngredDto.setIngredamount(String.valueOf(Float.valueOf(usershopping.get().getAmount()) + Float.valueOf(userIngredDto.getIngredamount())));
                userIngredRepository.save(userIngredDto.toEntity());
                userShoppingRepository.delete(usershopping.get());
            } else {
                throw new Exception("장바구니 수량 오류");
            }
        }
    }

    /**
     * 장바구니 서비스
     */

    public void Shopping(String userid, String recipenm) {
        String recipeid = cookingInfoRepository.findByRecipenm(recipenm).getRecipeid();
        ArrayList<UserShoppingEntity> shoppinglist = userShoppingRepository.findByUserid(userid); // 유저 장바구니 리스트
        ArrayList<IngredEntity> needlist = cookingIngredRepository.findByRecipeid(recipeid); // 재료 리스트
        ArrayList<UserIngredEntity> havelist = userIngredRepository.findByUserid(userid);

        ArrayList<String> havelistname = new ArrayList<>();
        ArrayList<String> havelistamount = new ArrayList<>();
        if(havelist.isEmpty()) {
            if (shoppinglist.isEmpty()) {
                for(int i = 0; i < needlist.size(); i++) {
                    IngredDTO ingredDTO = needlist.get(i).toDto();
                    UserShoppingDto user = new UserShoppingDto();
                    user.setUserid(userid);
                    user.setIngred(ingredDTO.getIRDNT_NM());
                    user.setNeed(ingredDTO.getIRDNT_AM());
                    user.setHave("0");
                    user.setAmount(ingredDTO.getIRDNT_AM());
                    if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                        user.setUnit("양념");
                    else
                        user.setUnit(ingredDTO.getIRDNT_UN());
                    userShoppingRepository.save(user.toEntity());
                }
            }
            else{
                for(int i = 0; i < needlist.size(); i++) {
                    IngredDTO ingredDTO = needlist.get(i).toDto();
                    Optional<UserShoppingEntity> user = userShoppingRepository.findByUseridAndIngred(userid, ingredDTO.getIRDNT_NM());
                    if (user.isEmpty()) {
                        UserShoppingDto userShoppingDto = new UserShoppingDto();
                        userShoppingDto.setUserid(userid);
                        userShoppingDto.setIngred(ingredDTO.getIRDNT_NM());
                        userShoppingDto.setNeed(ingredDTO.getIRDNT_AM());
                        userShoppingDto.setHave("0");
                        userShoppingDto.setAmount(ingredDTO.getIRDNT_AM());
                        if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                            userShoppingDto.setUnit("양념");
                        else
                            userShoppingDto.setUnit(ingredDTO.getIRDNT_UN());
                        userShoppingRepository.save(userShoppingDto.toEntity());

                    } else {
                        UserShoppingDto userdto = user.get().toDto();
                        userdto.setNeed(String.valueOf(Float.valueOf(ingredDTO.getIRDNT_AM()) + Float.valueOf(user.get().getNeed())));
                        userdto.setHave("0");
                        userdto.setAmount(userdto.getNeed());
                        if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                            userdto.setUnit("양념");
                        else
                            userdto.setUnit(ingredDTO.getIRDNT_UN());
                        userShoppingRepository.save(userdto.toEntity());
                    }
                }
            }
        }
        else {
            for (int i = 0; i < havelist.size(); i++) {
                havelistname.add(havelist.get(i).getIngredname());
                havelistamount.add(havelist.get(i).getIngredamount());
            }


            if (shoppinglist.isEmpty()) {
                for (int i = 0; i < needlist.size(); i++) {
                    IngredDTO ingredDTO = needlist.get(i).toDto();
                    UserShoppingDto user = new UserShoppingDto();
                    user.setUserid(userid);
                    user.setIngred(ingredDTO.getIRDNT_NM());
                    user.setNeed(ingredDTO.getIRDNT_AM());

                    int count = 0;
                    for (int j = 0; j < havelist.size(); j++) {

                        if (ingredDTO.getIRDNT_NM().equals(havelistname.get(j))) {
                            user.setHave(havelistamount.get(j));
                            break;
                        } else
                            count++;

                        if (count == havelist.size()) {
                            user.setHave("0");
                        }
                    }
                    if (Float.valueOf(user.getNeed()) > Float.valueOf(user.getHave())) {
                        user.setAmount(String.valueOf(Float.valueOf(user.getNeed()) - Float.valueOf(user.getHave())));
                        if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                            user.setUnit("양념");
                        else
                            user.setUnit(ingredDTO.getIRDNT_UN());
                    } else {
                        user.setAmount("0");
                        if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                            user.setUnit("양념");
                        else
                            user.setUnit(ingredDTO.getIRDNT_UN());
                    }
                    userShoppingRepository.save(user.toEntity());
                }
            } else {
                for (int i = 0; i < needlist.size(); i++) {
                    IngredDTO ingredDTO = needlist.get(i).toDto();
                    Optional<UserShoppingEntity> user = userShoppingRepository.findByUseridAndIngred(userid, ingredDTO.getIRDNT_NM());
                    if (user.isEmpty()) {
                        UserShoppingDto userShoppingDto = new UserShoppingDto();
                        userShoppingDto.setUserid(userid);
                        userShoppingDto.setIngred(ingredDTO.getIRDNT_NM());
                        userShoppingDto.setNeed(ingredDTO.getIRDNT_AM());

                        int count = 0;
                        for (int j = 0; j < havelistname.size(); j++) {
                            if (ingredDTO.getIRDNT_NM().equals(havelistname.get(j))) {
                                userShoppingDto.setHave(havelistamount.get(j));
                                break;
                            }
                            else {
                                count++;
                            }
                        }

                        if (count == havelistname.size()) {
                            userShoppingDto.setHave("0");
                        }
                        if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                            userShoppingDto.setUnit("양념");
                        else
                            userShoppingDto.setUnit(ingredDTO.getIRDNT_UN());


                        if (Float.valueOf(userShoppingDto.getNeed()) > Float.valueOf(userShoppingDto.getHave())) {
                            userShoppingDto.setAmount(String.valueOf(Float.valueOf(userShoppingDto.getNeed()) - Float.valueOf(userShoppingDto.getHave())));
                            if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                                userShoppingDto.setUnit("양념");
                            else
                                userShoppingDto.setUnit(ingredDTO.getIRDNT_UN());
                        } else {
                            userShoppingDto.setAmount("0");
                            if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                                userShoppingDto.setUnit("양념");
                            else
                                userShoppingDto.setUnit(ingredDTO.getIRDNT_UN());
                        }
                        userShoppingRepository.save(userShoppingDto.toEntity());

                    } else {
                        UserShoppingDto userdto = user.get().toDto();
                        userdto.setNeed(String.valueOf(Float.valueOf(ingredDTO.getIRDNT_AM()) + Float.valueOf(user.get().getNeed())));

                        int count = 0;
                        for (int j = 0; j < havelist.size(); j++) {
                            if (ingredDTO.getIRDNT_NM().equals(havelistname.get(j))) {
                                userdto.setHave(havelistamount.get(j));
                                break;
                            } else
                                count++;
                        }

                        if (count == havelist.size()) {
                            userdto.setHave("0");
                        }


                        if (Float.valueOf(userdto.getNeed()) > Float.valueOf(userdto.getHave())) {
                            userdto.setAmount(String.valueOf(Float.valueOf(userdto.getNeed()) - Float.valueOf(userdto.getHave())));
                            if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                                userdto.setUnit("양념");
                            else
                                userdto.setUnit(ingredDTO.getIRDNT_UN());
                        } else {
                            userdto.setAmount("0");
                            if(ingredDTO.getIRDNT_TY_NM().equals("양념"))
                                userdto.setUnit("양념");
                            else
                                userdto.setUnit(ingredDTO.getIRDNT_UN());
                        }
                        userShoppingRepository.save(userdto.toEntity());
                    }
                }
            }
        }
    }
}

