package phoenix.Mymichef.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import phoenix.Mymichef.data.dto.IngredInterface;
import phoenix.Mymichef.data.entity.IngredEntity;
import phoenix.Mymichef.data.repository.IngredRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecommandService {
    private IngredRepository ingredRepository;

    public void RecoomandMenu(ArrayList<String> useringred) {
        List<IngredInterface> MenuList = ingredRepository.findTest(useringred);
        if(MenuList.isEmpty())
            System.out.printf("비었어요");
        else
            for(int i = 0 ; i< MenuList.size();i++){
                System.out.printf("%s %s\n", MenuList.get(i).getrecipe(), MenuList.get(i).getcnt());
            }


    }

}
