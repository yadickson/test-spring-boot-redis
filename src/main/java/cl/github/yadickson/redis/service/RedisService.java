/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.service;

import cl.github.yadickson.redis.model.MessageModel;
import java.util.List;

/**
 * Redis service.
 *
 * @author Yadickson Soto
 */
public interface RedisService {

    /**
     * Save message.
     *
     * @param message message to save.
     */
    void saveMessage(MessageModel message);

    /**
     * Getter message by identification.
     *
     * @param messageId the message's identification to find
     * @return message model
     */
    MessageModel getMessageById(String messageId);

    /**
     * Getter all messages by group identify.
     *
     * @param groupId group's identify
     * @return message list
     */
    List<MessageModel> getAllMessagesByGroupId(String groupId);
}
