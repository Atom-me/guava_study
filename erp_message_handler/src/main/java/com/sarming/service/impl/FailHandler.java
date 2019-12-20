package com.sarming.service.impl;

import com.sarming.service.ERPMessageHandler;
import com.sarming.service.entity.BaseMessage;

public class FailHandler implements ERPMessageHandler {
    @Override
    public boolean accept(BaseMessage message) {
        return message.getOperationType() == 2;
    }

    @Override
    public void exceute() {

        System.out.println("销售战败工单业务。。。");
    }
}
