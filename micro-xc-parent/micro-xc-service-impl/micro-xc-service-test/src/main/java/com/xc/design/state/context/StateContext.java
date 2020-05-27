package com.xc.design.state.context;

import com.xc.design.state.StateDesign;
import org.springframework.beans.factory.annotation.Autowired;

public class StateContext {

    @Autowired
    StateDesign stateDesign;
    public StateContext(StateDesign stateDesign){
        this.stateDesign = stateDesign;
    }
    public String exec(){
        Object stateService = stateDesign.StateService();
        return (String)stateService;
    }

}
