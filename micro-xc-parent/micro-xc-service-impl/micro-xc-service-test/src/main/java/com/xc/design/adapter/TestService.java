package com.xc.design.adapter;

import java.util.Map;

public class TestService {

    /**
     * v1传的是map
     * v2传list
     * @param map
     */
    public static void  forMap(Map map){
        for(int i=0;i<map.size();i++){
            String value = (String) map.get(i);
            System.out.println(value);
        }
    }
}
