package com.capstone08.springstudy.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int no = 0;         // 번호
    private String subject;     // 제목
    private String nick;        // 글쓴이
    private String content;     // 내용
    private String date;          // 날짜
    private int hit;            // 조회

    public Post(int no, String subject, String nick, String content, int hit) {
        this.no = no;
        this.subject = subject;
        this.nick = nick;
        this.content = content;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.date = dateFormat.format(new Date());
        this.hit = hit;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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