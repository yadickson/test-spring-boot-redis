/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.util;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisIdentifyUtilTest {

    @Autowired
    private RedisIdentifyUtil util;

    @Test
    public void testBuildMessageId() {
        String messageId = "id";
        String result = util.buildMessageId(messageId);
        Assert.assertEquals("messages:m:id", result);
    }

    @Test
    public void testBuildGroupId() {
        String groupId = "id";
        String result = util.buildGroupId(groupId);
        Assert.assertEquals("messages:g:id", result);
    }

    @Test
    public void testFindGroupIdIfInputIsNull() {
        String groupId = null;
        String result = util.findGroupId(groupId);
        Assert.assertEquals("messages:g:*", result);
    }

    @Test
    public void testFindGroupIdIfInputIsPresent() {
        String groupId = "id";
        String result = util.findGroupId(groupId);
        Assert.assertEquals("messages:g:id", result);
    }

}
