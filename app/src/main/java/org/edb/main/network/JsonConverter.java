package org.edb.main.network;

import com.google.gson.*;
import org.edb.main.model.Time;
import org.edb.main.model.Object;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonConverter {

    JsonParser jsonParser;

    JsonArray configArray;

    JsonObject jsonObj;

    // RestAPIRequester에서 getter를 이용하여 사용가능.

    ArrayList<Object> objectList = new ArrayList<>();
    ArrayList<Time> timeList = new ArrayList<Time>();
    public Time time = new Time();

    String makeStringResult = "";

    public JsonConverter() {
        jsonParser = new JsonParser();
    }


    public void setConfiguration(String configuration) {

        System.out.print("\nconfiguration\n");
        System.out.print(configuration);
        //configuration에 key가 있기에 JsonParser 를 configuration으로 먼저 파싱한다.
        jsonObj = (JsonObject) jsonParser.parse(configuration);
        configArray = new JsonArray();
        configArray.add(jsonObj.getAsJsonObject("configuration"));
        jsonObj = (JsonObject) configArray.get(0);

        for (String key : jsonObj.keySet()) {
            convert(jsonObj, "config");
        }

    }


    private void convert(JsonObject jsonObject, String keyName) {
        Iterator<Map.Entry<String, JsonElement>> iterator
                = jsonObject.entrySet().iterator();
        Map.Entry<String, JsonElement> entry;

        int i = -1;

        while (iterator.hasNext()) {

            entry = iterator.next();
            JsonElement value = entry.getValue();
            String current = entry.getKey();
            ++i;      //time index 증가

//provides check for verifying if this element is a primitive or not.
            if (value.isJsonPrimitive()) {

                try {


                    //정규표현식 이용 {object1 : Chrome}, {object2:Game2} 구별
                    Pattern objectPattern = Pattern.compile("object*");
                    Matcher objectMatcher = objectPattern.matcher(current);
                    if (objectMatcher.find()) {
                        System.out.print("\nkeynametest4\n");
                        System.out.print(current.toString());
                        objectList.add(new Object(current.toString(), value.toString()));


                    } else if (keyName.equals("time")) {
                        System.out.print("\nkeynametest5\n");
                        System.out.print(value.toString());
                        time.setTime(value.toString());

                        //timeList에 중복시간 저장안되도록.
                        if (!timeList.contains(time)) {
                            timeList.add(i, time);
                        } else {
                            //중복
                            break;
                        }

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else if (value.isJsonObject()) {
                System.out.print("\nkeynametest2\n");
                System.out.print(current);//check

                convert(value.getAsJsonObject(), current);

            } else if (value.isJsonArray()) {

                JsonArray jsonArray = value.getAsJsonArray();
                JsonElement jsonElement;//check

                for (int z = 0; z < jsonArray.size(); z++) {
                    //break
                    System.out.print("\nlast test\n");
                    jsonElement = jsonArray.get(z);
                    convert(jsonElement.getAsJsonObject(), current);
                }


            } else {
//not  JsonArray
                System.out.print("\nkeynametest errror\n");
                System.out.print(current.toString());
            }


        }
    }


    public ArrayList<Object> getObjectList() {

        return objectList;
    }

    public ArrayList<Time> getTimeList() {

        return timeList;
    }



    public JsonObject ConfigjsonObject = new JsonObject();

    public void makeStringObject(JsonObject object) {

        JsonObject tmpJsonObject = new JsonObject();

        tmpJsonObject.add("object",object);

        ConfigjsonObject.add("configuration",tmpJsonObject);

        //json으로만든결과를 string으로
        makeStringResult = ConfigjsonObject.toString();

        System.out.print("\ntest makeString time 2\n");
        System.out.print(ConfigjsonObject.toString());
    }
    public String getString() {
        return makeStringResult;
    }

    public Time getTime(){
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}