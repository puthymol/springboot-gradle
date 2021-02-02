package com.softvider.provider.redis.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.config.redis.RedisConfig;
import com.softvider.model.BaseResponse;
import com.softvider.utils.AppUtil;

import java.util.HashMap;
import java.util.Map;

public class RedisService {

    RedisConfig redisConfig = new RedisConfig();

    public BaseResponse Set(String key, String value){
        redisConfig.getRedisPool().getResource().set(key, value);
        redisConfig.getRedisPool().getResource().expire(key, 30);
        Map<String, String> obj = new HashMap<>();
        obj.put("key", key);
        obj.put("value", value);
        return new BaseResponse(AppUtil.convert(obj, JsonNode.class));
    }

    public BaseResponse Get(String key){
        String value = redisConfig.getRedisPool().getResource().get(key);
        Map<String, String> obj = new HashMap<>();
        obj.put("key", key);
        obj.put("value", value);
        return new BaseResponse(AppUtil.convert(obj, JsonNode.class));
    }
}
