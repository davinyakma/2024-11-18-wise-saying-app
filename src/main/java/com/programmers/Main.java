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
        //초기값이 없어서 오류나니까 null로 초기화. Variable 'lastWiseSaying' might not have been initialized
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

                WiseSaying wiseSaying = new WiseSaying();
                wiseSaying.id = id;
                wiseSaying.content = content;
                wiseSaying.author = author;

                lastWiseSaying = wiseSaying; //while문까지만 사용 가능한 wiseSaying을 while문 바깥의 lastWiseSaying에 리모컨을 전달해줌. 수명 늘어남.

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                //최근 등록한 명언만 출력됨. wiseSaying가 하나여서 명언 한개만 저장 가능함.
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
}

