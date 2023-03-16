package com.vista.accouting.exceptions;


import com.vista.accouting.common.ResponseCodes;
import com.vista.accouting.util.log.ResponseUtil;

public abstract class ExceptionTranslatorBase {
    protected String code = ResponseCodes.ERROR_SERVICE_CALL;
    protected String info = "";
    protected final ResponseUtil responseUtil;

    public ExceptionTranslatorBase(ResponseUtil responseUtil) {
        this.responseUtil = responseUtil;
    }

    public ServiceException mapException(Exception exception, ServiceExceptionType exceptionType) {
        return mapException(exception, null, exceptionType);
    }

    public ServiceException mapException(String errorCode, String errorMessage, ServiceExceptionType exceptionType) {
        return mapException(errorCode, errorMessage, null, exceptionType);
    }

    public ServiceException mapException(Exception exception, String serviceIdentifier,
            ServiceExceptionType exceptionType) {
        String errorMessage = responseUtil.getCause(exception);
        return internalMapException("", errorMessage, serviceIdentifier, exceptionType);
    }

    public ServiceException mapException(String errorCode, String errorMessage, String serviceIdentifier,
            ServiceExceptionType exceptionType) {
//        errorCode = GeneralUtil.normalizeString(errorCode);
//        errorMessage = GeneralUtil.normalizeString(errorMessage);
        return internalMapException(errorCode, errorMessage, serviceIdentifier, exceptionType);
    }

    protected abstract ServiceException internalMapException(String errorCode, String errorMessage,
            String serviceIdentifier, ServiceExceptionType exceptionType);
}