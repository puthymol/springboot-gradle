package com.softvider.config.aspectj;

import com.softvider.provider.aspectj.AppAspectJ;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AspectJConfig {

    @Bean
    public AppAspectJ appAspectJ() {
        return new AppAspectJ();
    }
}
