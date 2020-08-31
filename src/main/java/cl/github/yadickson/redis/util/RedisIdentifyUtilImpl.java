/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.util;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Redis roup identify utility.
 *
 * @author Yadickson Soto
 */
@Component
public final class RedisIdentifyUtilImpl implements RedisIdentifyUtil {

    /**
     * Redis name to identify key.
     */
    private static final String REDIS_KEY = "messages";

    /**
     * Split character key.
     */
    private static final String SPLIT_KEY = ":";

    /**
     * Message character key.
     */
    private static final String MESSAGE_KEY = "m";

    /**
     * Group character key.
     */
    private static final String GROUP_KEY = "g";

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildMessageId(final String messageId) {
        return Stream.of(REDIS_KEY, MESSAGE_KEY, messageId)
                .collect(Collectors.joining(SPLIT_KEY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String buildGroupId(final String groupId) {
        return Stream.of(REDIS_KEY, GROUP_KEY, groupId)
                .collect(Collectors.joining(SPLIT_KEY));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String findGroupId(final String groupId) {
        final String id = Objects.isNull(groupId) ? "*" : groupId;
        return Stream.of(REDIS_KEY, GROUP_KEY, id)
                .collect(Collectors.joining(":"));
    }
}
