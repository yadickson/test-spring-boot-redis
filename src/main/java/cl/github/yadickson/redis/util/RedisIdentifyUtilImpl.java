/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.util;

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
     * {@inheritDoc}
     */
    @Override
    public String build(final String key, final String groupId) {
        return Stream.of(key, groupId)
                .filter(s -> s != null)
                .collect(Collectors.joining(":"));
    }
}
