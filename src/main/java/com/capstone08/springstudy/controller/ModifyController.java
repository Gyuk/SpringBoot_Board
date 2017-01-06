package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.data.PostRepository;
import com.capstone08.springstudy.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ModifyController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String modifyPost(@PathVariable int id, Post post){
        postRepository.save(post);

        return "redirect:/postview/{id}";
    }
}
