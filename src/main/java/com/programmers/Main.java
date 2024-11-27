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

                System.out.println(wiseSaying); // 그러나 실제로는 com.programmers.WiseSaying@5jkdjkle이런 식으로 객체의 주소값이 출력됨.
                // WiseSaying (id=1, content="명언", author="작가") 이렇게 출력하고 싶음. 이 출력문처럼 나오도록 커스터마이징 가능하다.
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

// toString 메소드가 Object클래스에 내장되어 있다.
class WiseSaying extends Object{
    int id;
    String content;
    String author;

    WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    @Override //String클래스의 toString을 오버라이딩하여 커스텀을 할 수 있음
    public String toString() {
        return "WiseSaying (id=%d, content=\"%s\", author=\"%s\")".formatted(id, content, author);
    }
}

