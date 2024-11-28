package com.programmers;

public class WiseSaying {
    public final int id; //명언 번호는 명언의 고유번호여서 id를 수정하면 안됨. final은 제약
    public String content;
    public String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}
