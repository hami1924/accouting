package com.vista.accouting.exceptions;


import com.vista.accouting.common.ResponseCodes;
import com.vista.accouting.util.log.ResponseUtil;
import org.springframework.stereotype.Component;

@Component
public class GeneralExceptionTranslator extends ExceptionTranslatorBase {
    private final ResponseUtil responseUtil;

    public GeneralExceptionTranslator(ResponseUtil responseUtil, ResponseUtil responseUtil1) {
        super(responseUtil);
        this.responseUtil = responseUtil1;
    }

    @Override
    protected ServiceException internalMapException(String fieldReject, String errorMessage, String serviceIdentifier,
            ServiceExceptionType exceptionType) {
        info = errorMessage;
        switch (fieldReject) {
            case "nationalCode":
                code = ResponseCodes.INVALID_NATIONAL_CODE;
                exceptionType = ServiceExceptionType.Bad_Request;
                break;
            case "mobilePhone":
                code = ResponseCodes.INVALID_MOBILE_NUMBER;
                exceptionType = ServiceExceptionType.Bad_Request;
                break;
            case "mobile":
                code = ResponseCodes.INVALID_MOBILE_NUMBER;
                exceptionType = ServiceExceptionType.Bad_Request;
                break;
            case "birthDate":
                code = ResponseCodes.REQUIRED_DATE;
                exceptionType = ServiceExceptionType.Bad_Request;
                break;


            default:
                code = ResponseCodes.INVALID_PARAMETERS;
                exceptionType = ServiceExceptionType.Bad_Request;
                break;
        }
        return new ServiceException(code, exceptionType, null, info);
    }
}