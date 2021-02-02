package com.softvider.config.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class RedisConfig {
    private static final Logger log = LogManager.getLogger(RedisConfig.class);
    private static JedisPool redisPool;

    static {
        Properties config = new Properties();
        try {
            log.debug("Loading classpath:application.properties");
            config.load(new ClassPathResource("/application.properties").getInputStream());

            JedisPoolConfig redisConfig = new JedisPoolConfig();
            redisConfig.setMaxIdle(100);
            redisConfig.setMaxTotal(100);
            redisConfig.setTestOnBorrow(false);
            redisConfig.setTestOnReturn(false);

            String redisHost = config.getProperty("softvider.redis.host");
            int redisPort = Integer.parseInt(config.getProperty("softvider.redis.port"));
            String auth = config.getProperty("softvider.redis.password");
            redisPool = new JedisPool(redisConfig, redisHost, redisPort, 3000, auth);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public JedisPool getRedisPool(){
        return redisPool;
    }

}
