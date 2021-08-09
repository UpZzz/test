package com.test_springboot_mybatis.tttt.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

public class GetJsonObjectUtils {

    public static JSONObject getParam(HttpServletRequest request){
        return JSONObject.parseObject(getStringFromStream(request));
    }

    private static String getStringFromStream(HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            String str = new String(bytes, 0, nTotalRead, "utf-8");
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
