package com.itedu.lesson_05;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * fastJson 进行解析
 */
public class JsonTest {

    public static void main(String[] args) {

        String jsonStr = "{\"sites\":" +
                "[{\"name\":\"蚂蚁课堂\",\"url\":\"www.itmayiedu.com\"}," +
                "{\"name\":\"每特教育\",\"url\":\"http://meiteedu.com/\"}]}";

//        // 1.将json字符串转为json Object
//        JSONObject jsonObject = new JSONObject();
//        // json 对象
//        JSONObject parseObject = jsonObject.parseObject(JsonStr);

        JSONObject parseObject = JSON.parseObject(jsonStr);

        // 获取json 数组对象
        JSONArray sitesArray = parseObject.getJSONArray("sites");
        for (Object object : sitesArray) {
            JSONObject jsonObject = (JSONObject) object;
            String name = jsonObject.getString("name");
            String url = jsonObject.getString("url");
            System.out.println(name + "---" + url);
        }
    }
}
