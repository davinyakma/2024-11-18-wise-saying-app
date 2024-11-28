package com.programmers;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {

    public void run() {
        System.out.println("== 명언 앱 ==");

        Scanner scanner = new Scanner(System.in);

        int lastId = 0;

        WiseSaying[] wiseSayings =  new WiseSaying[10]; //마지막으로 저장된 명언 하나만 기억하는 lastWiseSaying은 삭제. 그보다는 배열이 여러개의 명언들을 저장할 수 있다.
        int wiseSayingsSize = 0;

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();
                System.out.print("작가 : ");
                String author = scanner.nextLine();
                int id = ++lastId;

                WiseSaying wiseSaying = new WiseSaying(id, content, author);

                wiseSayings[wiseSayingsSize] = wiseSaying;
                wiseSayingsSize++;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                int i = 0;
                while(i < wiseSayingsSize) { //명언을 등록하면 wiseSayingsSize가 증가해서 while문을 선회하게 된다. 따라서 배열에 저장한 요소들을 다 출력 가능함.
                    WiseSaying wiseSaying = wiseSayings[i];
                    System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
                    i++;
                }
            }
        }
        scanner.close();
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

