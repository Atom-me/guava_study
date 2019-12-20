package com.samring.erp_message_consumer.listener;

import com.sarming.service.ERPMessageHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @author atom
 */
public class HandlerInvoker {

    private static List<ERPMessageHandler> list;

    static {
        list = new ArrayList();
        ServiceLoader<ERPMessageHandler> serviceLoader = ServiceLoader.load(ERPMessageHandler.class);
        for (ERPMessageHandler erpMessageHandler : serviceLoader) {
            list.add(erpMessageHandler);
        }
    }

    public static void process(com.sarming.service.entity.BaseMessage message) {
        for (ERPMessageHandler handler : list) {
            if (handler.accept(message)) {
                handler.exceute();
            }
        }
    }
}
