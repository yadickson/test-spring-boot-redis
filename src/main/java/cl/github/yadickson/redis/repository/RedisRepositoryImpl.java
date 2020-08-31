/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.repository;

import cl.github.yadickson.redis.model.MessageModel;
import cl.github.yadickson.redis.util.RedisIdentifyUtil;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

/**
 * Redis repository implementation.
 *
 * @author Yadickson Soto
 */
@Repository
public class RedisRepositoryImpl implements RedisRepository {

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
        final String messageId = utility.buildMessageId(message.getId());
        final String groupId = utility.buildGroupId(message.getGroupId());

        redisTemplate
                .opsForValue()
                .set(messageId, message);

        redisTemplate
                .opsForHash()
                .put(groupId, message.getId(), message);

        Date expireAt = Date.from(
                LocalDateTime
                        .now()
                        .with(LocalTime.MAX)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );

        redisTemplate.expireAt(messageId, expireAt);
        redisTemplate.expireAt(groupId, expireAt);
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
        final String keyId = utility.buildMessageId(messageId);
        return redisTemplate.hasKey(keyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageModel findById(final String messageId) {
        final String keyId = utility.buildMessageId(messageId);
        return redisTemplate.opsForValue().get(keyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean existsByGroupId(final String groupId) {
        final String keyId = utility.buildGroupId(groupId);
        return redisTemplate.hasKey(keyId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MessageModel> findAllByGroupId(final String groupId) {

        List<MessageModel> messages = new ArrayList<>();
        final String patternId = utility.findGroupId(groupId);

        ScanOptions options = ScanOptions.scanOptions()
                .match(patternId)
                .build();

        final Cursor<byte[]> scan = redisTemplate
                .getConnectionFactory()
                .getConnection()
                .scan(options);

        while (scan.hasNext()) {
            byte[] next = scan.next();
            String keyId = new String(next, StandardCharsets.UTF_8);

            redisTemplate.opsForHash()
                    .values(keyId)
                    .stream()
                    .map(x -> (MessageModel) x)
                    .forEach(messages::add);
        }

        return messages;
    }
}
