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

        WiseSaying lastWiseSaying = null;
        WiseSaying[] wiseSayings =  new WiseSaying[10]; //크기가 10인 wiseSayings배열. 명언이 10가 생성된 것이 아니라 명언을 10개까지 저장할 수 있는것임.
        int wiseSayingsSize = 0; //실제로 명언들이 저장된 크기를 알야하 해서 size 0으로 초기화

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
                lastWiseSaying = wiseSaying;

                wiseSayings[wiseSayingsSize] = wiseSaying; // 0~ 9인덱스의 명언 저장 가능함.
                wiseSayingsSize++; //ArrayList 대신에 배열 방식을 사용하면 인덱스 증가 필요함.

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                if (lastWiseSaying != null) {
                    System.out.println("%d / %s / %s".formatted(lastWiseSaying.id, lastWiseSaying.author, lastWiseSaying.content));
                } else {
                    System.out.println("등록된 명언이 없습니다.");
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

