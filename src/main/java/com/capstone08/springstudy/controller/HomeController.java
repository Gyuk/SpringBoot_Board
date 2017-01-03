package com.capstone08.springstudy.controller;

import com.capstone08.springstudy.data.PostRepository;
import com.capstone08.springstudy.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {
    private static final String isposted = "posted";

    @Autowired
    private PostRepository postRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Board(Model model) {
        List<Post> postList = postRepository.findByisposted(isposted);
        model.addAttribute("posts", postList);
        return "home";
    }

    @RequestMapping(value = "/write", method = RequestMethod.POST)
    public String Post(Post post){
        post.setDate("mmyy/mm/mm");
        post.setHit(123);
        post.setIsposted(isposted);
        postRepository.save(post);
        return "redirect:/";
    }

    @RequestMapping("/postview/del/{id}")
    public String del(@PathVariable int id) {
        postRepository.delete(id);
        return "redirect:/";
    }

    @RequestMapping("/write")
    public String Write(){
        return "write";
    }

    @RequestMapping("/postview/modify/{id}")
    public String modify(@PathVariable int id) {
        Post post = postRepository.findById(id);

        return "write";
    }

    @RequestMapping("/postview/{id}")
    public String PostView(@PathVariable int id, ModelMap modelMap){
        Post post = postRepository.findById(id);
        modelMap.put("post", post);
        return "postview";
    }
}