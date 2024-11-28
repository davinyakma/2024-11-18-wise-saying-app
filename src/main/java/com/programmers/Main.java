package com.programmers;

import java.util.Arrays;
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
        WiseSaying[] wiseSayings =  new WiseSaying[10];
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
                lastWiseSaying = wiseSaying;

                wiseSayings[wiseSayingsSize] = wiseSaying;
                wiseSayingsSize++;

                System.out.println(wiseSayings); // wiseSayings의 주소값이 나옴.
                //[WiseSaying(id=1, content="명언", author="작가"), WiseSaying(id=2, ~), WiseSaying(id=3, ~)] 처럼 출력되도록 하는게 이상적임.
                System.out.println(Arrays.toString(wiseSayings));
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

