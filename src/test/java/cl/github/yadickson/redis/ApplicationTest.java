/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis;

import cl.github.yadickson.redis.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Application main class test.
 *
 * @author Yadickson Soto
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class ApplicationTest {

    @Autowired
    Application application;

    @Test(expected = Test.None.class)
    public void testApplication() {
        Application.main(new String[]{});
    }

    @Test
    public void testAnnotations() {
        Assert.assertTrue(application.getClass().isAnnotationPresent(SpringBootApplication.class));
    }
}
