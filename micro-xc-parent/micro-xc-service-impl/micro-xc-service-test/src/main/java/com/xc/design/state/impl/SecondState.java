package com.xc.design.state.impl;

import com.xc.design.state.StateDesign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SecondState implements StateDesign {
    @Override
    public Object StateService() {
        log.info("second.....state.....");
        return "second.....state.....";
    }
}
