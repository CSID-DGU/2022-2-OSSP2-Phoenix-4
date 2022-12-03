package phoenix.Mymichef.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import phoenix.Mymichef.data.dto.CookingInfoDTO;
import phoenix.Mymichef.data.dto.IngredDTO;
import phoenix.Mymichef.service.ApiService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Controller
@Slf4j
@RequestMapping("/")
public class CookingInfoController {
    private final ApiService apiService;
    public CookingInfoController(ApiService apiService) {
        this.apiService = apiService;
    }
    @GetMapping("cookinginfoload")
    public void loadJsonFromApi(){
        String result = "";
        try{
            URL url = new URL("http://211.237.50.150:7080/openapi/c5e63e0e3954b0a121c42218411e5d26f585504daa3904f54fe0a7af1505c1cb/json/Grid_20150827000000000226_1/1/1000");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject COOKRCP01New = (JSONObject)jsonObject.get("Grid_20150827000000000226_1");

            String totalCount= String.valueOf(COOKRCP01New.get("totalCnt"));
            JSONObject subResult = (JSONObject)COOKRCP01New.get("result");
            JSONArray infoArr = (JSONArray)COOKRCP01New.get("row");

            for(int i = 0; i < infoArr.size(); i++){
                JSONObject object = (JSONObject) infoArr.get(i);

                String ROW_NUM = String.valueOf(object.get("ROW_NUM"));

                String RECIPE_ID = String.valueOf(object.get("RECIPE_ID")) ;

                String RECIPE_NM_KO = String.valueOf( object.get("RECIPE_NM_KO"));

                String SUMRY = String.valueOf(object.get("SUMRY"));

                String NATION_CODE =String.valueOf (object.get("NATION_CODE"));

                String NATION_NM = String.valueOf(object.get("NATION_NM"));

                String TY_CODE = String.valueOf(object.get("TY_CODE"));

                String TY_NM = String.valueOf(object.get("TY_NM"));

                String COOKING_TIME = String.valueOf(object.get("COOKING_TIME")) ;

                String CALORIE = String.valueOf( object.get("CALORIE"));

                String QNT = String.valueOf(object.get("QNT"));

                String LEVEL_NM =String.valueOf (object.get("LEVEL_NM"));

                String IRDNT_CODE = String.valueOf(object.get("IRDNT_CODE"));

                CookingInfoDTO cookingInfoDTO = new CookingInfoDTO(ROW_NUM,
                        RECIPE_ID,
                        RECIPE_NM_KO,
                        SUMRY,
                        NATION_CODE,
                        NATION_NM,
                        TY_CODE,
                        TY_NM,
                        COOKING_TIME,
                        CALORIE,
                        QNT,
                        LEVEL_NM,
                        IRDNT_CODE);
                apiService.saveCookingInfo(cookingInfoDTO);
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (org.json.simple.parser.ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
