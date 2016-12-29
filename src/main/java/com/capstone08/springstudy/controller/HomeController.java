package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.data.PostRepository;
import com.capstone08.springstudy.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/")
    public String Board(ModelMap modelMap) {
        List<Post> allPosts = postRepository.getALL_POSTS();
        modelMap.put("posts", allPosts);
        return "home";
    }

    @RequestMapping("/write")
    public String Write(){
        return "write";
    }
}
