/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.github.yadickson.redis.controller;

import cl.github.yadickson.redis.Application;
import cl.github.yadickson.redis.model.MessageModel;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import cl.github.yadickson.redis.service.RedisService;
import org.hamcrest.Matchers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Configuration Service test.
 *
 * @author Yadickson Soto
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, RedisController.class})
@AutoConfigureMockMvc
public class RedisControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RedisController controller;

    @MockBean
    private RedisService service;

    @Test
    public void testGetMessageById() {
        MessageModel messageMock = new MessageModel();
        Mockito.when(service.getMessageById(Mockito.eq("id"))).thenReturn(messageMock);

        ResponseEntity<MessageModel> response = controller.getMessageById("id");

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertSame(messageMock, response.getBody());

        Mockito.verify(service, Mockito.times(1)).getMessageById(Mockito.eq("id"));
    }

    @Test
    public void testRestGetMessageById() throws Exception {

        MessageModel messageMock = new MessageModel();

        messageMock.setId("messageIdMock");
        messageMock.setDescription("descriptionMock");
        messageMock.setGroupId("groupIdMock");

        Mockito.when(service.getMessageById(Mockito.eq("id2"))).thenReturn(messageMock);

        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .path("/service/redis/message").query("messageId={keyword}").buildAndExpand("id2");

        mvc.perform(MockMvcRequestBuilders.get(uriComponents.toUriString())
                .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasEntry("id", "messageIdMock")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasEntry("description", "descriptionMock")))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasEntry("groupId", "groupIdMock")));

        Mockito.verify(service, Mockito.times(1)).getMessageById(Mockito.eq("id2"));
    }

}
