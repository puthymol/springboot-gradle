package com.softvider.provider.redis.controller;

import com.softvider.model.BaseResponse;
import com.softvider.provider.redis.service.RedisService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    RedisService cacheService = new RedisService();

    @RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    public BaseResponse Get(@PathVariable(value="key") String key) {
        return cacheService.Get(key);
    }

    @RequestMapping(value = "/set/{key}/{value}", method = RequestMethod.GET)
    public BaseResponse Set(@PathVariable(value="key") String key, @PathVariable(value="value") String value) {
        return cacheService.Set(key, value);
    }

}
