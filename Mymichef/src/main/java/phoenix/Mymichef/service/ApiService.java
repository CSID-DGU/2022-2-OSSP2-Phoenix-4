package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.ApiDTO;
import phoenix.Mymichef.data.dto.CookingProcessDTO;
import phoenix.Mymichef.data.entity.ApiEntity;
import phoenix.Mymichef.data.entity.CookingProcessEntity;
import phoenix.Mymichef.data.repository.ApiRespository;
import phoenix.Mymichef.data.repository.CookingProcessRepository;

import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRespository apiRespository;
    @Autowired
    private CookingProcessRepository cookingProcessRepository;

    /**
     *  api 저장
     */
    public void saveApi(ApiDTO apiDTO){
        ApiEntity apiEntity = apiDTO.toEntity();
        validateDuplicateApi(apiEntity);
    }

    public void validateDuplicateApi(ApiEntity apiEntity){
        Optional<ApiEntity> find = apiRespository.findById(apiEntity.getRCP_SEQ());
        find.ifPresent(m -> {
            throw new IllegalStateException("이미 저장된 정보입니다.");
        });
        apiRespository.save(apiEntity);
    }


    public void saveProcessApi(CookingProcessDTO cookingProcessDTO){
        CookingProcessEntity cookingProcessEntity = cookingProcessDTO.toEntity();
        validateDuplicateProcessApi(cookingProcessEntity);
    }

    public void validateDuplicateProcessApi(CookingProcessEntity cookingProcessEntity){
        Optional<CookingProcessEntity> find = cookingProcessRepository.findById(cookingProcessEntity.getROW_NUM());
        find.ifPresent(m -> {
            throw new IllegalStateException("이미 저장된 정보입니다.");
        });
        cookingProcessRepository.save(cookingProcessEntity);
    }
}
