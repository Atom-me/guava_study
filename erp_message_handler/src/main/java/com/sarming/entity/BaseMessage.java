package com.sarming.service.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * base erp message
 *
 * @author Atom
 */
@Data
public class BaseMessage implements Serializable {

    private int operationType;
    private String crmWorkOrderNo;
    private String crmBizNo;
    /**
     * ERP business real create time
     */
    private Date dealTime;

    /**
     * mark this message unique
     */
    private String messageNo;
    private String messageContent;
    private int executeResult;
    private List<String> businessEntities;

}


