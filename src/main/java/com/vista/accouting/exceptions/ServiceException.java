package com.vista.accouting.exceptions;

public class ServiceException extends Exception {
    private String code;
    private String info;
    private ServiceExceptionType serviceExceptionType;
    private String reference;
    private String value;

    public ServiceException(String yCode, ServiceExceptionType yServiceExceptionType) {
        this.code = yCode;
        this.serviceExceptionType = yServiceExceptionType;
    }

    public ServiceException(String code, ServiceExceptionType yServiceExceptionType, String message) {
        super(message);
        this.code = code;
        this.serviceExceptionType = yServiceExceptionType;
    }

    public ServiceException(String yCode, ServiceExceptionType yServiceExceptionType, String message, String info) {
        super(message);
        this.code = yCode;
        this.info = info;
        this.serviceExceptionType = yServiceExceptionType;
    }

    public String getCode() {
        return this.code;
    }

    public ServiceException setCode(String code) {
        this.code = code;
        return this;
    }

    public String getInfo() {
        return this.info;
    }

    public ServiceException setInfo(String info) {
        this.info = info;
        return this;
    }

    public ServiceExceptionType getServiceExceptionType() {
        return this.serviceExceptionType;
    }

    public ServiceException setServiceExceptionType(ServiceExceptionType serviceExceptionType) {
        this.serviceExceptionType = serviceExceptionType;
        return this;
    }

    public String getReference() {
        return this.reference;
    }

    public ServiceException setReference(String reference) {
        this.reference = reference;
        return this;
    }

    public String getValue() {
        return this.value;
    }

    public ServiceException setValue(String value) {
        this.value = value;
        return this;
    }
}