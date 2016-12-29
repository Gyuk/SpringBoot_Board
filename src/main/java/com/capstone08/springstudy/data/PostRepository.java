package com.capstone08.springstudy.data;

import com.capstone08.springstudy.model.Post;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PostRepository {
    private List<Post> ALL_POSTS = Arrays.asList(
            new Post(1, "This is cat", "Hong", "catcatcatcatcat", 1),
            new Post(2, "This is cat", "Hong", "catcatcatcatcat", 2),
            new Post(3, "This is cat", "Hong", "catcatcatcatcat", 3),
            new Post(4, "This is cat", "Hong", "catcatcatcatcat", 4),
            new Post(5, "This is cat", "Hong", "catcatcatcatcat", 5)
    );

    public List<Post> getALL_POSTS() {
        return ALL_POSTS;
    }



}