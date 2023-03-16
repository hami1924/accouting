package com.vista.accouting.util.log;


import com.vista.accouting.common.ErrorResponse;
import com.vista.accouting.common.GeneralResponse;
import com.vista.accouting.common.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Component
public class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    @Autowired
    public ResponseUtil() {
    }

//    public static Set<ErrorResponse.Error> mapResponseCodesToErrorMessage(Set<ErrorResponse.Error> errors,
//            String lang) {
//        errors.stream().forEach(t -> t.setMessage(getLocalizedMessage(t.getCode(), lang)));
//        return errors;
//    }

//    public static ErrorResponse.Error getErrorResponseByResponseCodes(String code, String lang) {
//        ErrorResponse.Error error = new ErrorResponse.Error();
//        error.setCode(code);
//        error.setMessage(getLocalizedMessage(code, lang));
//        return error;
//    }

//    private static String getLocalizedMessage(String code, String lang) {
//        try {
//            Locale locale = HeaderRequestUtil.extractLocale(lang);
//            return PropertiesLoad.getValue(code, locale);
//        } catch (Exception messageLoadException) {
//            LOGGER.error("Failed to load a message for {} code with {}", code, messageLoadException);
//            return code;
//        }
//    }


    public String getCause(Exception exception) {
        if (Objects.nonNull(exception.getCause())) {
            return exception.getCause().toString();
        }
        if (!isNullOrEmpty(exception.getMessage())) {
            return exception.getMessage();
        }
        return "Unknown Cause";
    }
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

}
