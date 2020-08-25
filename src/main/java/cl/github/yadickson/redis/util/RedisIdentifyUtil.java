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
     * Build group identify.
     *
     * @param key the redis key.
     * @param groupId the group's identify.
     * @return the new group's identify
     */
    String build(String key, String groupId);
}
