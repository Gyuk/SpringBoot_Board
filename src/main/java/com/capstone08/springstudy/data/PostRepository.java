package com.capstone08.springstudy.data;

import com.capstone08.springstudy.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByisposted(String isposted);

    Post findById(int id);
}