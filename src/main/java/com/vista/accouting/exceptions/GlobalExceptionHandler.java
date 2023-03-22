package com.vista.accouting.exceptions;


import com.vista.accouting.common.ErrorResponse;
import jakarta.xml.ws.Response;
import lombok.SneakyThrows;
import org.ehcache.Status;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    public GlobalExceptionHandler(GeneralExceptionTranslator generalExceptionTranslator) {
        this.generalExceptionTranslator = generalExceptionTranslator;
    }

    final private GeneralExceptionTranslator generalExceptionTranslator;




    @ExceptionHandler(NotFoundUserException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity NotFoundUserExceptionHandler(NotFoundUserException e) throws ServiceException {
        AtomicReference<String> errorMessages = new AtomicReference(0);
        AtomicReference<String> codeMessage = new AtomicReference(10);
        ServiceException serviceException = generalExceptionTranslator
                .mapException(codeMessage.toString(), errorMessages.toString(), ServiceExceptionType.Bad_Request);
        return ConvertErrorToReponseEroor(serviceException);
    }

    @ExceptionHandler(UserFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity FoundUserExceptionHandler(UserFoundException e) throws ServiceException {
        AtomicReference<String> errorMessages = new AtomicReference(0);
        AtomicReference<String> codeMessage = new AtomicReference(11);
        ServiceException serviceException = generalExceptionTranslator
                .mapException(codeMessage.toString(), errorMessages.toString(), ServiceExceptionType.Bad_Request);
        return ConvertErrorToReponseEroor(serviceException);
    }
    @SneakyThrows
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity validationExceptionHandler(MethodArgumentNotValidException e) throws ServiceException {
        AtomicReference<String> errorMessages = new AtomicReference(0);
        AtomicReference<String> codeMessage = new AtomicReference(0);
        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            codeMessage.set(fieldError.getField() != null ? fieldError.getField()
                    : (String) fieldError.getRejectedValue());
            errorMessages.set(fieldError.getField() + "  " + fieldError.getDefaultMessage());
        });
        ServiceException serviceException = generalExceptionTranslator
                .mapException(codeMessage.toString(), errorMessages.toString(), ServiceExceptionType.Bad_Request);
        return ConvertErrorToReponseEroor(serviceException);
    }

    private ResponseEntity ConvertErrorToReponseEroor(ServiceException serviceException) {
        ErrorResponse.Error error = new ErrorResponse.Error();
        error.setCode(serviceException.getCode());
        error.setMessage(serviceException.getMessage());
        error.setInfo(serviceException.getInfo());
        Set<ErrorResponse.Error> lst = new HashSet<ErrorResponse.Error>();
        lst.add(error);
//        Set<ErrorResponse.Error> errorSet = ResponseUtil.mapResponseCodesToErrorMessage(lst, "fa");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setOperationTime(new Date());
//        errorResponse.setErrors(errorSet);
        ResponseEntity responseEntity =
                new ResponseEntity(errorResponse, HttpStatus.resolve(HttpStatus.BAD_REQUEST.value()));
        return responseEntity;
    }
}
