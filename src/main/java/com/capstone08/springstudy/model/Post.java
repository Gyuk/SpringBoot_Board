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
    //private String date;          // 날짜
    //private int hit =0;            // 조회

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
}