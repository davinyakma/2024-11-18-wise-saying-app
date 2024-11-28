package com.programmers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {
    Scanner scanner;
    int lastId;
    WiseSaying[] wiseSayings;
    int wiseSayingsSize;

    App() {
        scanner = new Scanner(System.in);
        lastId = 0;
        wiseSayings =  new WiseSaying[100];
        wiseSayingsSize = 0;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionAdd();
            } else if (cmd.equals("목록")){
                actionList(); //actionAdd()처럼 actionList()로 run()함수를 분기함.
            }
        }
        scanner.close();
    }

    void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine(); //scanner, lastId, wiseSayings, wiseSayingsSize는 run함수와 공유해야 함. 따라서 run()과 actionAdd()바깥으로 빼낸다.
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);

        wiseSayings[wiseSayingsSize] = wiseSaying;
        wiseSayingsSize++;

        System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
    }

    void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for(WiseSaying wiseSaying : wiseSayings) {
            if( wiseSaying == null) break;
            System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
        }
    }
}

class WiseSaying extends Object{
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}

