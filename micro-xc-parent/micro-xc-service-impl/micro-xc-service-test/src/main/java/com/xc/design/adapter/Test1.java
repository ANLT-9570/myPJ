package com.xc.design.adapter;

import java.util.ArrayList;

public class Test1 {
    public static void main(String[] args) {

        ArrayList arrayList = new ArrayList();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");

        LisAdapter lisAdapter = new LisAdapter(arrayList);

        TestService.forMap(lisAdapter);

    }

}
