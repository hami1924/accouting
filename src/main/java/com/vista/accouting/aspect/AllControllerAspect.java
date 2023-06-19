package com.vista.accouting.aspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vista.accouting.aspect.globalObject.GlobalObject;
import com.vista.accouting.aspect.globalObject.SynchronizedGlobalObjectHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.xml.bind.JAXBElement;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.mapstruct.ap.shaded.freemarker.template.utility.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

import static java.util.Optional.ofNullable;
import static org.apache.cxf.helpers.ServiceUtils.getMethodName;

@Component
@Aspect
@Slf4j
public class AllControllerAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(AllControllerAspect.class);


    @Around("com.vista.accouting.aspect.Interception.servicesPointCut()")
    public Object invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Starting ..");
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String appLogId = UUID.randomUUID().toString();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] parameters = proceedingJoinPoint.getArgs();
        String methodName = getMethodName(method);
        String requestInString = null;
        try {
            requestInString = getRequestInfo(parameters, request);
            LOGGER.info("\"*** => Service {} => Request: {} => [LogDate {}]", methodName, requestInString,
                    new Date());
        } catch (Throwable throwable) {
            LOGGER.error("error on log incoming request...", throwable);
        }
        GlobalObject globalObject=new GlobalObject();

        SynchronizedGlobalObjectHelper.putGlobalObject(globalObject);
        //check sms number and  set in GlobalObject

        //statrt  for calculate time

        Object response = proceedingJoinPoint.proceed();

        //handel error in response


        // impl log in data base

        log.info("Ending ...");
        return response;
    }

    public String getRequestInfo(Object[] parameters, HttpServletRequest request)
            throws IOException {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            if (Objects.nonNull(key)) {
                map.put(key, request.getHeader(key));
            }
        }
        ObjectMapper objectMapper = createMapper();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Header ");
        stringBuilder.append(objectMapper.writeValueAsString(map));
        for (Object parameter : parameters) {
            ofNullable(parameter).map(Object::getClass).map(Class::getSimpleName).ifPresent(simpleName -> {
                if (!simpleName.equals("StandardMultipartFile")) {
                    stringBuilder.append(" Body ");
                    try {
                        stringBuilder.append(objectMapper.writeValueAsString(parameters));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        return String.valueOf(stringBuilder);
    }
    public static ObjectMapper createMapper() {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        jsonMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jsonMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return jsonMapper;
    }
}
