package com.vista.accouting.aspect;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AllControllerAspect {




    @Around("com.vista.accouting.aspect.Interception.servicesPointCut()")
    public ResponseEntity invoke(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Starting ..");


        //check sms number and  set in GlobalObject

        //statrt  for calculate time

        Object response = proceedingJoinPoint.proceed();

        //handel error in response


        // impl log in data base

        log.info("Ending ...");
        return null;
    }
}
