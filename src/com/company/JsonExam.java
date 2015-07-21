package com.company;


import net.sf.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lin on 2015/7/21.
 */
public class JsonExam {
    public static void main(String[] args) throws JSONException {
        try {
            Map map = new HashMap();
            map.put("name", "Jacky");
            map.put("age", 30);
            map.put("gender", true);
//            jsonObjSend.put("elementi", new JSONArray(new Object[] { "value1", "value2", "value3"} ));
           /* JSONArray arr = new JSONArray();
            arr.put("value1");
            arr.put("value2");
            jsonObjSend.put("elementi", arr);*/
            JSONObject jsonObjectJacky = new JSONObject(map);
            System.out.println("2: " + jsonObjectJacky);
            jsonObjectJacky.put("height", 180);
            System.out.println("3: " + jsonObjectJacky);
            JsonParse_1();
            JsonParse_2();
            JsonParse_3();
            JsonParse_4();
        } catch (Exception E) {

        }
    }
//    若我們要擷取到Phone的陣列中的一筆：
    public static void JsonParse_4() {
        JSONObject j;
        try {
            String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

            j = new JSONObject(tmp);

            Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone").get(0);

            System.out.println(jsonOb);

        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
//3、若我們要擷取到Phone的陣列：
    public static void JsonParse_3() {
        JSONObject j;
        try {
            String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

            j = new JSONObject(tmp);

            Object jsonOb = j.getJSONObject("Data").getJSONArray("Phone");

            System.out.println(jsonOb);

        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
//2、若我們要擷取到Name：

public static void JsonParse_2()
    {

        JSONObject j;
        try {
            String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

            j = new JSONObject(tmp);

            Object jsonOb = j.getJSONObject("Data").get("Name");

            System.out.println(jsonOb);

        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
//若我們是要Data全部：
    public static void JsonParse_1()
    {
        JSONObject j;
        try {
            String tmp = "{\"Data\":{\"Name\":\"MichaelChan\",\"Email\":\"XXXX@XXX.com\",\"Phone\":[1234567,0911123456]}}";

            j = new JSONObject(tmp);

            Object jsonOb = j.getJSONObject("Data");

            System.out.println(jsonOb);

        }catch(Exception e){
            System.err.println("Error: " + e.getMessage());
        }
    }
}
