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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
public class WriteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private WriteController writeController;

    @Mock
    private PostRepository postRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(writeController)
                .build();
    }

    @Test
    public void writePostTest() throws Exception {
        doAnswer(invocation -> {
            Post post = (Post)invocation.getArguments()[0];
            post.setId(1);
            return null;
        }).when(postRepository).save(any(Post.class));

        mockMvc.perform(
                post("/write")
        ).andExpect(redirectedUrl("/postview/1"));
        verify(postRepository).save(any(Post.class));
    }

    @Test
    public void writeFailTest() throws Exception {
        mockMvc.perform(
                post("/write")
                        .param("subject", "")
        ).andExpect(view().name("ErrorPage"));

        mockMvc.perform(
                post("/write")
                        .param("nick", "")
        ).andExpect(view().name("ErrorPage"));
    }
}