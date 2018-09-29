package com.example.gymproject.util;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static final Map<String,Boolean> tokenMap=new HashMap();

    public static Map<String,Object> paramToMap(String param){
        if(StringUtils.isEmpty(param)){
            return null;
        }else {
            Map<String,Object> map=new HashMap<>();
           String keyAndValues[]= param.split("&");
           for(String keyAndValue:keyAndValues){
               String paramValue[]=keyAndValue.split("=");
               String key=paramValue[0]+"";
               String value="";
               if(paramValue.length>1)
                   value=paramValue[1]+"";
               map.put(key,value);
           }
           return map;
        }
    }
}
