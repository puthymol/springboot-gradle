package com.softvider.provider.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AppAspectJ {
    private static final Logger log = LoggerFactory.getLogger(AppAspectJ.class);

    @Pointcut("execution(* com.softvider.provider.aspectj.service.impl.AspectJServiceImpl.Home*(..)))" )
    private void Home(){}

    @Before("Home() && args(request)")
    public void beforeExecute(String request) {
        log.info("This is do before method Home execute");
        log.info("Request => {}", request);
    }

    @AfterReturning(pointcut = "Home() && args(request)", argNames = "request, response", returning = "response")
    public void afterExecute(String request, Object response) {
        log.info("This is do after method Home execute");
        log.info("Request => {}", request);
        log.info("Response <= {}", response);
    }
}
