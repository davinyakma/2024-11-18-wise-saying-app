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
        WiseSaying lastWiseSaying = null; //WiseSaying 객체의 수명을 늘리기 위해 while문 밖에 생성
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

                WiseSaying wiseSaying = new WiseSaying(id, content, author); // 한 번에 객체 생성하려면 WiseSaying에서 매개변수 받는 생성자 생성
//                wiseSaying.id = id;
//                wiseSaying.content = content;
//                wiseSaying.author = author;

                lastWiseSaying = wiseSaying;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                System.out.println("%d / %s / %s".formatted(lastWiseSaying.id, lastWiseSaying.author, lastWiseSaying.content));
            }
        }
        scanner.close();
    }
}

class WiseSaying{
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

