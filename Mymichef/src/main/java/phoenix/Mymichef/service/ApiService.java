//package phoenix.Mymichef.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import phoenix.Mymichef.data.dto.ApiDTO;
//
//import phoenix.Mymichef.data.dto.CookingProcessDTO;
//import phoenix.Mymichef.data.entity.ApiEntity;
//import phoenix.Mymichef.data.entity.CookingProcessEntity;
//import phoenix.Mymichef.data.repository.ApiRespository;
//import phoenix.Mymichef.data.repository.CookingProcessRepository;
//
//import phoenix.Mymichef.data.dto.CookingInfoDTO;
//import phoenix.Mymichef.data.dto.IngredDTO;
//import phoenix.Mymichef.data.entity.CookingInfoEntity;
//import phoenix.Mymichef.data.entity.IngredEntity;
//import phoenix.Mymichef.data.repository.CookingInfoRepository;
//import phoenix.Mymichef.data.repository.IngredRepository;
//
//
//import java.util.Optional;
//
//@Service
//public class ApiService {
//
//    @Autowired
//    private ApiRespository apiRespository;
//    @Autowired
//    private CookingProcessRepository cookingProcessRepository;
//    @Autowired
//    private IngredRepository ingredRespository;
//
//    @Autowired
//    private CookingInfoRepository cookingInfoRepository;
//
//    /**
//     *  api 저장
//     */
//    public void saveApi(ApiDTO apiDTO){
//        ApiEntity apiEntity = apiDTO.toEntity();
//        validateDuplicateApi(apiEntity);
//    }
//
//    public void validateDuplicateApi(ApiEntity apiEntity){
//        Optional<ApiEntity> find = apiRespository.findById(apiEntity.getRCP_SEQ());
//        find.ifPresent(m -> {
//            throw new IllegalStateException("이미 저장된 정보입니다.");
//        });
//        apiRespository.save(apiEntity);
//    }
//
//    public void saveIngred(IngredDTO ingredDTO){
//        IngredEntity ingredEntity = ingredDTO.toEntity();
//        validateDuplicateIngred(ingredEntity);
//    }
//
//    public void validateDuplicateIngred(IngredEntity ingredEntity){
//        Optional<IngredEntity> find = ingredRespository.findById(ingredEntity.getROW_NUM());
//        find.ifPresent(m -> {
//            throw new IllegalStateException("이미 저장된 정보입니다.");
//        });
//        ingredRespository.save(ingredEntity);
//    }
//
//    public void saveCookingInfo(CookingInfoDTO cookingInfoDTO){
//        CookingInfoEntity cookingInfoEntity = cookingInfoDTO.toEntity();
//        validateDuplicateCookingInfo(cookingInfoEntity);
//    }
//
//    public void validateDuplicateCookingInfo(CookingInfoEntity cookingInfoEntity){
//        Optional<CookingInfoEntity> find = cookingInfoRepository.findById(cookingInfoEntity.getROW_NUM());
//        find.ifPresent(m -> {
//            throw new IllegalStateException("이미 저장된 정보입니다.");
//        });
//        cookingInfoRepository.save(cookingInfoEntity);
//    }
//    public void saveProcessApi(CookingProcessDTO cookingProcessDTO){
//        CookingProcessEntity cookingProcessEntity = cookingProcessDTO.toEntity();
//        validateDuplicateProcessApi(cookingProcessEntity);
//    }
//
//    public void validateDuplicateProcessApi(CookingProcessEntity cookingProcessEntity){
//        Optional<CookingProcessEntity> find = cookingProcessRepository.findById(cookingProcessEntity.getROW_NUM());
//        find.ifPresent(m -> {
//            throw new IllegalStateException("이미 저장된 정보입니다.");
//        });
//        cookingProcessRepository.save(cookingProcessEntity);
//    }
//}
