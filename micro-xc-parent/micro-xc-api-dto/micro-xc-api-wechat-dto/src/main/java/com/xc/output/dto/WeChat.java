package com.xc.output.dto;

import lombok.Data;

@Data
public class WeChat {
    String id;
    String name;

    public WeChat(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
