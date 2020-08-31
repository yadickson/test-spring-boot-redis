/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * The message model.
 *
 * @author Yadickson Soto
 */
@Getter
@Setter
@SuppressWarnings({"serial"})
public final class MessageModel implements Serializable {

    /**
     * The message's identification.
     */
    private String id;

    /**
     * The description of the message.
     */
    private String description;

    /**
     * The group's identification.
     */
    private String groupId;
}
