package com.sarming.service.impl;

import com.sarming.service.ERPMessageHandler;
import com.sarming.service.entity.BaseMessage;

public class DistributeHandler implements ERPMessageHandler {
    @Override
    public boolean accept(BaseMessage message) {
        return message.getOperationType() == 1;
    }

    @Override
    public void exceute() {

        System.out.println("派发业务");

    }
}
