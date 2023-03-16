package com.vista.accouting.aspect;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import com.vista.accouting.common.ErrorResponse;
import com.vista.accouting.common.ResponseCodes;
import com.vista.accouting.exceptions.ServiceExceptionType;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


//import javax.jms.MessageNotReadableException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

//    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable cause = ex.getCause();
        String fieldName = null;
        String value = null;
        if (cause instanceof JsonParseException) {
            JsonParseException jsonParseException = (JsonParseException) cause;
            JsonStreamContext parsingContext = jsonParseException.getProcessor().getParsingContext();
            fieldName = parsingContext.getCurrentName();
            if (Objects.nonNull(parsingContext.getCurrentValue())) {
                value = parsingContext.getCurrentValue().toString();
            } else {
                String key = "Unrecognized token '";
                String message = jsonParseException.getMessage();
                if (message.startsWith(key)) {
                    value = message.split("'", 3)[1];
                }
                //Unrecognized token 'test': was expecting 'null', 'true', 'false' or NaN
            }
        } else if (cause instanceof JsonMappingException) {
            fieldName = ((JsonMappingException) cause).getPath().get(0).getFieldName();
        } else if (cause instanceof InvocationTargetException || cause instanceof IllegalArgumentException) {
            Iterator<String> headerNames = request.getHeaderNames();
            fieldName = "";
            while (headerNames.hasNext()) {
                String parameterName = headerNames.next();
                fieldName = String.format("%s,%s", fieldName, parameterName);
            }
        }
        if (cause instanceof InvalidFormatException) {
            Object dataValue = ((InvalidFormatException) cause).getValue();
            if (Objects.nonNull(dataValue)) {
                value = dataValue.toString();
            }
        }
        return mapException(ResponseCodes.VALIDATOR, value, fieldName, ServiceExceptionType.Bad_Request);
    }

//    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return mapException(ResponseCodes.INVALID_ENDPOINT, null, null, ServiceExceptionType.Not_Found);
    }

//    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return mapException(ResponseCodes.UNSUPPORTED_MEDIA_TYPE, null, "Content-Type",
                ServiceExceptionType.Unsupported_Media_Type);
    }

//    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return mapException(ResponseCodes.NOT_ACCEPTABLE, null, "Accept", ServiceExceptionType.Not_Acceptable);
    }

    @ExceptionHandler({JsonMappingException.class, InvalidFormatException.class, InvocationTargetException.class,
            IllegalArgumentException.class,
//            MessageNotReadableException.class
    })
    public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
        Set<ErrorResponse.Error> errors = new HashSet<>();
        String fieldName = null;
        String value = null;
        String code = ResponseCodes.VALIDATOR;
        ServiceExceptionType serviceExceptionType = ServiceExceptionType.Bad_Request;
        if (exception instanceof JsonMappingException) {
            fieldName = ((JsonMappingException) exception).getPath().get(0).getFieldName();
        }
        if (exception instanceof InvalidFormatException) {
            value = ((InvalidFormatException) exception).getValue().toString();
        }
        if (exception instanceof InvocationTargetException || exception instanceof IllegalArgumentException) {
            Iterator<String> parameterNames = request.getParameterNames();
            fieldName = "";
            while (parameterNames.hasNext()) {
                fieldName = String.format("%s,%s", fieldName, parameterNames.next());
            }
        }
        return mapException(code, value, fieldName, serviceExceptionType);
    }

    private ResponseEntity<Object> mapException(String code,
            String value,
            String fieldName,
            ServiceExceptionType serviceExceptionType) {
        Set<ErrorResponse.Error> errors =
                Collections.singleton(new ErrorResponse.Error().setCode(code).setValue(value).setReference(fieldName));
//        ResponseUtil.mapResponseCodesToErrorMessage(errors, "fa");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        return new ResponseEntity<>(errorResponse, HttpStatus.resolve(serviceExceptionType.statusCode()));
    }
}
