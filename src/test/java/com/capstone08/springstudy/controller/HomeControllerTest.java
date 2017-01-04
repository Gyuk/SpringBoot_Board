package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.AppConfig;
import com.capstone08.springstudy.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class HomeControllerTest {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("posts"));
    }

    @Test
    public void writePost() throws Exception {
        mockMvc.perform(post("/write")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nick", "NICK")
                .param("subject", "SUBJECT")
                .param("content", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        Post expectedPost = new Post();
        expectedPost.setId(1);
        expectedPost.setIsposted("posted");
        expectedPost.setNick("NICK");
        expectedPost.setSubject("SUBJECT");
        expectedPost.setContent("test");
        Date d = new Date();
        SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        expectedPost.setDate(today.format(d));
        expectedPost.setHit(0);

        List<Post> expectedPosts = new ArrayList<Post>();
        expectedPosts.add(expectedPost);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("posts"))
                .andExpect(model().attribute("posts", hasSize(1)))
                .andExpect(model().attribute("posts", contains(samePropertyValuesAs(expectedPost))));
    }
}