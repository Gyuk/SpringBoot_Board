package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.data.PostRepository;
import com.capstone08.springstudy.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.capstone08.springstudy.model.Post.PostBuilder;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private HomeController homeController;

    @Mock
    private PostRepository postRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(homeController)
                .build();
    }

    @Test
    public void boardTest() throws Exception {
        List<Post> posts = Arrays.asList(
                new PostBuilder(1).withNick("n1").withSubject("s1").withContent("c1").withDate("d1").withHit(1).build(),
                new PostBuilder(2).withNick("n2").withSubject("s2").withContent("c2").withDate("d2").withHit(2).build()
        );
        when(postRepository.findAllByOrderByIdDesc()).thenReturn(posts);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("Home"))
                .andExpect(model().attribute("posts", posts));
        verify(postRepository).findAllByOrderByIdDesc();
    }

    @Test
    public void moveToWriteTest() throws Exception {
        mockMvc.perform(get("/write"))
                .andExpect(view().name("Write"));
    }

    @Test
    public void moveToPostViewTest() throws Exception {
        Post post = new PostBuilder(1).withNick("n1").withSubject("s1").withContent("c1").withDate("d1").withHit(1).build();
        when(postRepository.findById(1)).thenReturn(post);

        mockMvc.perform(get("/postview/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("PostView"))
                .andExpect(model().attribute("post", post));
        verify(postRepository).findById(1);
    }
}
