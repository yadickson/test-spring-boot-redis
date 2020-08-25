package cl.github.yadickson.redis.controller;

import cl.github.yadickson.redis.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cl.github.yadickson.redis.service.RedisService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Redis controller definition.
 *
 * @author yadickson
 */
@RestController
@RequestMapping("/service/redis")
public final class RedisController {

    /**
     * The redis service.
     */
    @Autowired
    private RedisService service;

    /**
     * RESTful to save message.
     *
     * @param message the message
     * @return success.
     */
    @PostMapping(value = "/message")
    @ResponseBody
    public ResponseEntity<Void> saveMessage(
            @RequestBody final MessageModel message) {
        service.saveMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * RESTful to get message id.
     *
     * @param messageId the message's identification
     * @return the message model found.
     */
    @GetMapping(value = "/message")
    @ResponseBody
    public ResponseEntity<MessageModel> getMessageById(
            @RequestParam(name = "messageId") final String messageId) {
        return ResponseEntity.ok(service.getMessageById(messageId));
    }

    /**
     * RESTful to get all messages by group's identify.
     *
     * @param groupId the group's identify to find.
     * @return the message list.
     */
    @GetMapping(value = "/messages")
    @ResponseBody
    public ResponseEntity<List<MessageModel>> getMessagesByGroupId(
            @RequestParam(name = "groupId", required = false) final String groupId) {
        return ResponseEntity.ok(service.getAllMessagesByGroupId(groupId));
    }
}
