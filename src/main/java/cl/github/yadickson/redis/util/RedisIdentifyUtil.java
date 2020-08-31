/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.util;

/**
 * Redis roup identify utility.
 *
 * @author Yadickson Soto
 */
public interface RedisIdentifyUtil {

    /**
     * Build message identify.
     *
     * @param messageId the message's identify.
     * @return the new group's identify
     */
    String buildMessageId(String messageId);

    /**
     * Build group identify.
     *
     * @param groupId the group's identify.
     * @return the new group's identify
     */
    String buildGroupId(String groupId);

    /**
     * Build find group identify pattern.
     *
     * @param groupId the group's identify.
     * @return the find identify pattern
     */
    String findGroupId(String groupId);
}
