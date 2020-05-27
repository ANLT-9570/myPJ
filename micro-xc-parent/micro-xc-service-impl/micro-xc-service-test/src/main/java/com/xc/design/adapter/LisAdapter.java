package com.xc.design.adapter;

import java.util.HashMap;
import java.util.List;

public class LisAdapter extends HashMap {

    List list;

    LisAdapter(List list){
        this.list = list;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public Object get(Object key) {
        return this.list.get(Integer.valueOf(key.toString()));
    }
}
