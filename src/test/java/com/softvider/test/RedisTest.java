package com.softvider.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootTest
public class RedisTest {

    @Test
    public void testRedis(){

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMaxTotal(100);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);

        String redisHost = "157.230.246.60";
        int redisPort = 16379;
        String auth = "1234";
        JedisPool pool = new JedisPool(config, redisHost, redisPort, 3000, auth);
        pool.getResource().set("puthy", "12001");
        pool.getResource().expire("puthy", 30);
        System.out.println("Connected to Redis");
    }
}
