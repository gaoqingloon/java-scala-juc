package com.itedu.lesson_05;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 组装json
 */
public class JsonTest2 {

    public static void main(String[] args) {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        JSONObject bean1 = new JSONObject();
        bean1.put("name", "科比");
        bean1.put("url", "http://kebi.com");
        jsonArray.add(bean1);

        JSONObject bean2 = new JSONObject();
        bean2.put("name", "科比1");
        bean2.put("url", "http://kebi1.com");
        jsonArray.add(bean2);

        jsonObject.put("sites", jsonArray);

        System.out.println(jsonObject.toJSONString());

        /*
{"sites":[{"name":"科比","url":"http://kebi.com"},{"name":"科比1","url":"http://kebi1.com"}]}
         */
    }
}
