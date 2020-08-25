/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.service;

import cl.github.yadickson.redis.model.MessageModel;
import cl.github.yadickson.redis.repository.RedisRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Redis service implement.
 *
 * @author Yadickson Soto
 */
@Service
public final class RedisServiceImpl implements RedisService {

    /**
     * Redis repository.
     */
    @Autowired
    private RedisRepository repository;

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveMessage(final MessageModel message) {
        repository.save(message);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageModel getMessageById(final String messageId) {
        return repository.findById(messageId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MessageModel> getAllMessagesByGroupId(final String groupId) {
        return repository.findAllByGroupId(groupId);
    }
}
