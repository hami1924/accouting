package com.vista.accouting.common;

import java.util.Date;

public class BaseResponse implements IBaseResponse {
    private Date operationTime;
    private String refId;

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }
}
