/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.configuration;

import cl.github.yadickson.redis.model.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis configuration factory.
 *
 * @author Yadickson Soto
 */
@Configuration
public class RedisConfig {

    /**
     * Redis host configuration.
     */
    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * Redis port configuration.
     */
    @Value("${spring.redis.port}")
    private Integer redisPort;

    /**
     * Build jedis connection factory.
     *
     * @return the connection factory.
     */
    @Bean(name = "jedisConnectionFactory")
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration config;
        config = new RedisStandaloneConfiguration(redisHost, redisPort);
        return new JedisConnectionFactory(config);
    }

    /**
     * Build redis template.
     *
     * @return the redis template.
     */
    @Bean(name = "redisTemplate")
    @DependsOn(value = {"jedisConnectionFactory"})
    public RedisTemplate<String, MessageModel> redisTemplate() {
        RedisTemplate<String, MessageModel> template = new RedisTemplate<>();

        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());

        return template;
    }
}
