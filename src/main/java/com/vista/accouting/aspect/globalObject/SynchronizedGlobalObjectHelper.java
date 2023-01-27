package com.vista.accouting.aspect.globalObject;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class SynchronizedGlobalObjectHelper {

    private final static String ACCOUNTING_KEY = "ACCOUNTING_KEY";

    public static GlobalObject getGlobalObject() {
        RequestAttributes requestAttributes;
        if ((requestAttributes = RequestContextHolder.getRequestAttributes()) == null) {
            return null;
        }
        return (GlobalObject) requestAttributes.getAttribute(ACCOUNTING_KEY, RequestAttributes.SCOPE_REQUEST);
    }

    public static void putGlobalObject(GlobalObject globalObject) {
        RequestAttributes requestAttributes;
        if ((requestAttributes = RequestContextHolder.getRequestAttributes()) != null) {
            requestAttributes.setAttribute(ACCOUNTING_KEY, globalObject, RequestAttributes.SCOPE_REQUEST);
        }
    }
}
