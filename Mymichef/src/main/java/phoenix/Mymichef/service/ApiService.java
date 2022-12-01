package phoenix.Mymichef.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.ApiDTO;
import phoenix.Mymichef.data.entity.ApiEntity;
import phoenix.Mymichef.data.repository.ApiRespository;

import java.util.Optional;

@Service
public class ApiService {

    @Autowired
    private ApiRespository apiRespository;

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
}
