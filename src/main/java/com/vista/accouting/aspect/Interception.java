package com.vista.accouting.aspect;

import org.aspectj.lang.annotation.Pointcut;

public interface Interception {

    @Pointcut("target(com.vista.accouting.controller)")
    default void servicesPointCut() {}
}
