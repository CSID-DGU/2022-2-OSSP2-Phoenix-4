package phoenix.Mymichef.controller;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import phoenix.Mymichef.data.dto.CookingProcessDTO;
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
public class ProcessApiController {

    private final ApiService apiService;

    public ProcessApiController(ApiService apiService){
        this.apiService = apiService;
    }

    @GetMapping("processapiload")
    public void loadJsonFromProcessApi(){
        String result = "";
        try{
            URL url = new URL("http://211.237.50.150:7080/openapi/92aaf6a9a8913d68cb992758b141a50b31b9361fb03df2ab619443dbb460cec7/json/Grid_20150827000000000228_1/3001/3100");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject COOKRCP01New = (JSONObject)jsonObject.get("Grid_20150827000000000228_1");

            String totalCount= String.valueOf(COOKRCP01New.get("totalCnt"));
            JSONObject subResult = (JSONObject)COOKRCP01New.get("result");
            JSONArray infoArr = (JSONArray) COOKRCP01New.get("row");

            for(int i = 0; i < infoArr.size(); i++){
                JSONObject object = (JSONObject) infoArr.get(i);

                String ROW_NUM = String.valueOf(object.get("ROW_NUM"));

                String RECIPE_ID = String.valueOf(object.get("RECIPE_ID"));

                String COOKING_NO = String.valueOf(object.get("COOKING_NO"));

                String COOKING_DC = String.valueOf(object.get("COOKING_DC"));

                String STEP_TIP = String.valueOf(object.get("STEP_TIP"));

                CookingProcessDTO cookingProcessDTO = new CookingProcessDTO(ROW_NUM, RECIPE_ID, COOKING_NO, COOKING_DC, STEP_TIP);
                apiService.saveProcessApi(cookingProcessDTO);
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
