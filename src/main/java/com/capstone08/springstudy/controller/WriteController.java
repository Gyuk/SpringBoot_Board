package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.data.PostRepository;
import com.capstone08.springstudy.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class WriteController {

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String writePost(Post post) {
        Date d = new Date();
        SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        post.setDate(today.format(d));

        if(post.getNick() == "" || post.getSubject() == "") {
            return "redirect:/fail";
        }
        else {
            postRepository.save(post);
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/fail")
    @ResponseBody
    public String fail() {
        return "FAIL";
    }
}
