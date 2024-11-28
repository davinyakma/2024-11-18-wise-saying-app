package com.programmers.domain.wiseSaying.entity;

public class WiseSaying {
    private int id; //id를 변경 가능하도록 수정
    private String content;
    private String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getContent(){
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id){ //id를 변경할 수 있도록 세터 추가
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}
