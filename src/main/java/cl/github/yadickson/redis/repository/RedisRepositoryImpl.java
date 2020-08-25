/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.repository;

import cl.github.yadickson.redis.model.MessageModel;
import cl.github.yadickson.redis.util.RedisIdentifyUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Redis repository implementation.
 *
 * @author Yadickson Soto
 */
@Repository
public class RedisRepositoryImpl implements RedisRepository {

    /**
     * Hash by identify key.
     */
    private final static String REDIS_KEY = "messages";

    /**
     * Redis template.
     */
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String, MessageModel> redisTemplate;

    /**
     * Redis identify utility.
     */
    @Autowired
    private RedisIdentifyUtil utility;

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final MessageModel message) {
        final String newId = utility.build(REDIS_KEY, message.getGroupId());

        redisTemplate.opsForHash()
                .put(REDIS_KEY, message.getMessageId(), message);

        redisTemplate.opsForHash()
                .put(newId, message.getMessageId(), message);

        // Date expireAt = Date.from(LocalDateTime.now().with(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
        // redisTemplate.expireAt(groupId, expireAt);
        // redisTemplate.expire(REDIS_KEY, 10L, TimeUnit.of(ChronoUnit.SECONDS));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveAll(final List<MessageModel> messages) {
        messages.forEach(this::save);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsById(final String messageId) {
        return redisTemplate.opsForHash().hasKey(REDIS_KEY, messageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageModel findById(final String messageId) {
        return (MessageModel) redisTemplate.opsForHash()
                .get(REDIS_KEY, messageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsByGroupId(final String groupId) {
        final String newId = utility.build(REDIS_KEY, groupId);
        return redisTemplate.hasKey(newId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MessageModel> findAllByGroupId(final String groupId) {

        List<MessageModel> messages = new ArrayList<>();
        final String newId = utility.build(REDIS_KEY, groupId);

        redisTemplate.opsForHash()
                .entries(newId)
                .values()
                .stream()
                .map(x -> (MessageModel) x)
                .forEach(messages::add);

        return messages;
    }
}
