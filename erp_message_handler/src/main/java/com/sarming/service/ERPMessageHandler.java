package com.sarming.service;

import com.sarming.service.entity.BaseMessage;

public interface ERPMessageHandler {

    boolean accept(BaseMessage message);

    void exceute();
}
