/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.repository;

import cl.github.yadickson.redis.model.MessageModel;
import java.util.List;

/**
 * Redis repository definition.
 *
 * @author Yadickson Soto
 */
public interface RedisRepository {

    /**
     * Save message on redis.
     *
     * @param message message model to save.
     */
    void save(MessageModel message);

    /**
     * Save all messages list on redis.
     *
     * @param messages messages list model to save.
     */
    void saveAll(List<MessageModel> messages);

    /**
     * Getter if exists message by identify.
     *
     * @param messageId message's identify to find.
     * @return true if exists
     */
    Boolean existsById(String messageId);

    /**
     * Find message by identification.
     *
     * @param messageId message's identify to find.
     * @return the message model found
     */
    MessageModel findById(String messageId);

    /**
     * Getter if exists message by group identify.
     *
     * @param groupId group's identify to find.
     * @return true if exists
     */
    Boolean existsByGroupId(String groupId);

    /**
     * Find all message models by group identification.
     *
     * @param groupId the group's identification to find.
     * @return message model list.
     */
    List<MessageModel> findAllByGroupId(String groupId);

}
