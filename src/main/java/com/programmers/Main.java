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

                //명언에 같이 저장되는 데이터 패키징(묶음)
                WiseSaying wiseSaying = new WiseSaying(); //빈 객체 생성. 목록을 저장하기 위한 객체
                wiseSaying.id = id; //명언 번호
                wiseSaying.content = content; //내용
                wiseSaying.author = author; //작가
                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            }
        }
        scanner.close();
    }
}

class WiseSaying{
    int id;
    String content;
    String author;
}

