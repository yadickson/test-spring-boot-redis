/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.service;

import cl.github.yadickson.redis.repository.RedisRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Logger Service test.
 *
 * @author Yadickson Soto
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService service;

    @MockBean
    private RedisRepository repository;
    
    @Test
    public void testProcess() {
        /*
        String response = service.process();
        Assert.assertNotNull(response);
        Assert.assertEquals("Logger Message", response);
*/
    }
}
