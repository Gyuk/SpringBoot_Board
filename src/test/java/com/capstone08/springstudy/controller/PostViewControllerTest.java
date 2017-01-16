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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@RunWith(MockitoJUnitRunner.class)
public class PostViewControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private PostViewController postViewController;

    @Mock
    private PostRepository postRepository;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(postViewController)
                .build();
    }

    @Test
    public void deletePostTest() throws Exception {

        doAnswer(invocation -> {
            Post post = (Post) invocation.getArguments()[0];
            post.setId(2);
            return null;
        }).when(postRepository).save(any(Post.class));

        mockMvc.perform(
                get("/postview/del/2"))
                .andExpect(redirectedUrl("/"));
        verify(postRepository, times(1)).delete(2);
    }
}