package com.example.gymproject;

import java.util.HashMap;
import java.util.Map;

public class ttt {
    public static void main(String[] args) {
        Map<String,Boolean>map=new HashMap<>();
        map.put("a",true);
        if(map.get("b")!=null){

            System.out.println(map.get("b"));
        }
    }
}
