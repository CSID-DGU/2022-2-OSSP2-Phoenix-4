package phoenix.Mymichef.controller.openapi;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import phoenix.Mymichef.data.dto.ApiDTO;
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
public class ApiController {

    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("apiload")
    public void loadJsonFromApi(){
        String result = "";
        try{
            URL url = new URL("http://openapi.foodsafetykorea.go.kr/api/e0f268dd7a0f4c21be54/COOKRCP01/json/1000/2000");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-type", "application/json");

            BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            result = bf.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
            JSONObject COOKRCP01New = (JSONObject)jsonObject.get("COOKRCP01");

            String totalCount= (String) COOKRCP01New.get("total_count");
            JSONObject subResult = (JSONObject)COOKRCP01New.get("RESULT");
            JSONArray infoArr = (JSONArray) COOKRCP01New.get("row");

            for(int i = 0; i < infoArr.size(); i++){
                JSONObject object = (JSONObject) infoArr.get(i);

                String RCP_SEQ = (String) object.get("RCP_SEQ");

                String RCP_NM = (String) object.get("RCP_NM");

                String RCP_WAY2 = (String) object.get("RCP_WAY2");

                String RCP_PAT2 = (String) object.get("RCP_PAT2");

                String INFO_WGT = (String) object.get("INFO_WGT");

                String INFO_ENG = (String) object.get("INFO_ENG");

                String INFO_CAR = (String) object.get("INFO_CAR");

                String INFO_PRO = (String) object.get("INFO_PRO");

                String INFO_FAT = (String) object.get("INFO_FAT");

                String INFO_NA = (String) object.get("INFO_NA");

                String HASH_TAG = (String) object.get("HASH_TAG");

                String ATT_FILE_NO_MAIN = (String) object.get("ATT_FILE_NO_MAIN");

                String ATT_FILE_NO_MK = (String) object.get("ATT_FILE_NO_MK");

                String RCP_PARTS_DTLS = (String) object.get("RCP_PARTS_DTLS");

                String MANUAL01 = (String) object.get("MANUAL01");

                String MANUAL_IMG01 = (String) object.get("MANUAL_IMG01");

                String MANUAL02 = (String) object.get("MANUAL02");

                String MANUAL_IMG02 = (String) object.get("MANUAL_IMG02");

                String MANUAL03 = (String) object.get("MANUAL03");

                String MANUAL_IMG03 = (String) object.get("MANUAL_IMG03");

                String MANUAL04 = (String) object.get("MANUAL04");

                String MANUAL_IMG04 = (String) object.get("MANUAL_IMG04");

                String MANUAL05 = (String) object.get("MANUAL05");

                String MANUAL_IMG05 = (String) object.get("MANUAL_IMG05");

                String MANUAL06 = (String) object.get("MANUAL06");

                String MANUAL_IMG06 = (String) object.get("MANUAL_IMG06");

                String MANUAL07 = (String) object.get("MANUAL07");

                String MANUAL_IMG07 = (String) object.get("MANUAL_IMG07");

                String MANUAL08 = (String) object.get("MANUAL08");

                String MANUAL_IMG08 = (String) object.get("MANUAL_IMG08");

                String MANUAL09 = (String) object.get("MANUAL09");

                String MANUAL_IMG09 = (String) object.get("MANUAL_IMG09");

                String MANUAL10 = (String) object.get("MANUAL10");

                String MANUAL_IMG10 = (String) object.get("MANUAL_IMG10");

                String MANUAL11 = (String) object.get("MANUAL11");

                String MANUAL_IMG11 = (String) object.get("MANUAL_IMG11");

                String MANUAL12 = (String) object.get("MANUAL12");

                String MANUAL_IMG12 = (String) object.get("MANUAL_IMG12");

                String MANUAL13 = (String) object.get("MANUAL13");

                String MANUAL_IMG13 = (String) object.get("MANUAL_IMG13");

                String MANUAL14 = (String) object.get("MANUAL14");

                String MANUAL_IMG14 = (String) object.get("MANUAL_IMG14");

                String MANUAL15 = (String) object.get("MANUAL15");

                String MANUAL_IMG15 = (String) object.get("MANUAL_IMG15");

                String MANUAL16 = (String) object.get("MANUAL16");

                String MANUAL_IMG16 = (String) object.get("MANUAL_IMG16");

                String MANUAL17 = (String) object.get("MANUAL17");

                String MANUAL_IMG17 = (String) object.get("MANUAL_IMG17");

                String MANUAL18 = (String) object.get("MANUAL18");

                String MANUAL_IMG18 = (String) object.get("MANUAL_IMG18");

                String MANUAL19 = (String) object.get("MANUAL19");

                String MANUAL_IMG19 = (String) object.get("MANUAL_IMG19");

                String MANUAL20 = (String) object.get("MANUAL20");

                String MANUAL_IMG20 = (String) object.get("MANUAL_IMG20");

                ApiDTO apiDTO = new ApiDTO(RCP_SEQ,
                        RCP_NM,
                        RCP_WAY2,
                        RCP_PAT2,
                        INFO_WGT,
                        INFO_ENG,
                        INFO_CAR,
                        INFO_PRO,
                        INFO_FAT,
                        INFO_NA,
                        HASH_TAG,
                        ATT_FILE_NO_MAIN,
                        ATT_FILE_NO_MK,
                        RCP_PARTS_DTLS,
                        MANUAL01,
                        MANUAL_IMG01,
                        MANUAL02,
                        MANUAL_IMG02,
                        MANUAL03,
                        MANUAL_IMG03,
                        MANUAL04,
                        MANUAL_IMG04,
                        MANUAL05,
                        MANUAL_IMG05,
                        MANUAL06,
                        MANUAL_IMG06,
                        MANUAL07,
                        MANUAL_IMG07,
                        MANUAL08,
                        MANUAL_IMG08,
                        MANUAL09,
                        MANUAL_IMG09,
                        MANUAL10,
                        MANUAL_IMG10,
                        MANUAL11,
                        MANUAL_IMG11,
                        MANUAL12,
                        MANUAL_IMG12,
                        MANUAL13,
                        MANUAL_IMG13,
                        MANUAL14,
                        MANUAL_IMG14,
                        MANUAL15,
                        MANUAL_IMG15,
                        MANUAL16,
                        MANUAL_IMG16,
                        MANUAL17,
                        MANUAL_IMG17,
                        MANUAL18,
                        MANUAL_IMG18,
                        MANUAL19,
                        MANUAL_IMG19,
                        MANUAL20,
                        MANUAL_IMG20);
                apiService.saveApi(apiDTO);
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
