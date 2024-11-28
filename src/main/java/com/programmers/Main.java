package com.programmers;

// 같은 패키지 안에 존재하는 여러 형재파일들은 import문 없이 서로의 메소드를 호출 가능하다.
public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}