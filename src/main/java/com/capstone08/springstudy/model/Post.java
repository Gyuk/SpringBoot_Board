package com.capstone08.springstudy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;         // 번호
    private String isposted;
    private String nick;        // 글쓴이
    private String subject;     // 제목
    private String content;     // 내용
    private String date;          // 날짜
    private int hit;            // 조회

    public Post() {
        //this.id = 1;
        this.isposted = "posted";
        this.nick = "testnick";
        this.subject = "test subject";
        this.content = "test content";
        this.date = "test date";
        this.hit = 1;
    }

    public Post(Post post) {
        //this.id = 1;
        //this.isposted = "posted";
        this.nick = post.getNick();
        this.subject = post.getSubject();
        this.content = post.getContent();
        //this.date = "test date";
        //this.hit = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsposted() {
        return isposted;
    }

    public void setIsposted(String isposted) {
        this.isposted = isposted;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }
}