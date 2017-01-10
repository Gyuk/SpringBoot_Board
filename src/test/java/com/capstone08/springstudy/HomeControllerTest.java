package com.capstone08.springstudy;

import com.capstone08.springstudy.data.PostRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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

    @Autowired
    private PostRepository postRepository;

    @Autowired
    public void setPostRepository(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void testBoard() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attributeExists("posts"));
    }

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(post("/write")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nick", "NICK")
                .param("subject", "SUBJECT")
                .param("content", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        Post expectedPost = postRepository.findByNick("NICK");

        try {
            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("home"))
                    .andExpect(model().attributeExists("posts"))
                    .andExpect(model().attribute("posts", hasSize((int)postRepository.count())))
                    .andExpect(model().attribute("posts", contains(samePropertyValuesAs(expectedPost))));
    }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            postRepository.delete(expectedPost.getId());
        }
    }

    @Test
    public void testHit() throws Exception {
        mockMvc.perform(post("/write")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nick", "NICK")
                .param("subject", "SUBJECT")
                .param("content", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        Post expectedPost = postRepository.findByNick("NICK");
        int expectedHit = expectedPost.getHit();

        try {
            mockMvc.perform(get("/hit/{id}", expectedPost.getId()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/postview/" + expectedPost.getId()));

            mockMvc.perform(get("/postview/{id}", expectedPost.getId()))
                    .andExpect(status().isOk())
                    .andExpect(view().name("postview"))
                    .andExpect(model().attributeExists("post"))
                    .andExpect(model().attribute("post", hasProperty("hit", is(expectedHit + 1))));
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        finally {
            postRepository.delete(expectedPost.getId());
        }
    }

    @Test
    public void testDel() throws Exception {
        mockMvc.perform(post("/write")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nick", "NICK")
                .param("subject", "SUBJECT")
                .param("content", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location", "/"));

        Post expectedPost =  postRepository.findByNick("NICK");
        int expectedCount = (int)postRepository.count();

        try {
            mockMvc.perform(get("/postview/del/{id}", expectedPost.getId()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(header().string("Location", "/"));

            mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("home"))
                    .andExpect(model().attributeExists("posts"))
                    .andExpect(model().attribute("posts", hasSize(expectedCount - 1)));
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Test
    public void testWrite() throws Exception {

    }
}