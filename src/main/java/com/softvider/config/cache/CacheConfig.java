package com.softvider.config.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

    @Bean("getKeyGenerator")
    public KeyGenerator getKeyGenerator() {
        return new GetKeyGenerator();
    }

    @Bean("getWithParamKeyGenerator")
    public KeyGenerator getWithParamKeyGenerator() {
        return new GetWithParamKeyGenerator();
    }

    @Bean("postKeyGenerator")
    public KeyGenerator postKeyGenerator() {
        return new PostKeyGenerator();
    }
}
