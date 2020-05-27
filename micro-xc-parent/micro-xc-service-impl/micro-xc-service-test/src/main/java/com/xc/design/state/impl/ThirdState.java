package com.xc.design.state.impl;

import com.xc.design.state.StateDesign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ThirdState implements StateDesign {

    @Override
    public String StateService() {
        log.info("third....state....");
        return "third....state....";
    }

}
