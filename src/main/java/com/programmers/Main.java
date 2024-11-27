package com.programmers;

public class Main {
    public static void main(String[] args) {
        App app = new App(); //app이라는 레퍼런스 변수. App클래스의 객체 생성.
        //그 객체의 주소값(리모컨)이 app에 저장!
        app.run(); //ctrl + 1 하면 자동으로 필요한 run메소드 생성해줌.
    }
}

class App {

    public void run() {
        System.out.println("== 명언 앱 ==");

        System.out.print("명령) ");
    }
}

