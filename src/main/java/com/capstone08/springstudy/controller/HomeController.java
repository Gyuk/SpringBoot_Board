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

import java.text.SimpleDateFormat;
import java.util.Date;
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
        Date d = new Date();
        SimpleDateFormat today = new SimpleDateFormat("yyyy/MM/dd");
        post.setDate(today.format(d));

        postRepository.save(post);
        return "redirect:/";
    }


    @RequestMapping(value = "/hit/{id}", method = RequestMethod.GET)
    public String hit(@PathVariable int id){
        Post post = postRepository.findById(id);
        post.setHit(1);
        postRepository.save(post);
        return "redirect:/postview/{id}";
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



    @RequestMapping(value = "/postview/modify/{id}", method = RequestMethod.GET)
    public String modify(@PathVariable int id, Model model) {
        Post post = postRepository.findById(id);
        model.addAttribute("post", post);

        return "modify";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.POST)
    public String modi(@PathVariable int id, Post post){
        postRepository.save(post);
        return "redirect:/postview/{id}";
    }


    @RequestMapping("/postview/{id}")
    public String PostView(@PathVariable int id, ModelMap modelMap){
        Post post = postRepository.findById(id);
        // post.setHit(1);
        System.out.print(post.getHit());
        modelMap.put("post", post);
        return "postview";
    }
}