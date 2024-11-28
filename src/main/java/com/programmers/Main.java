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

        //샘플 데이터 2개 생성. 항상 명언1,2번임.
        addWiseSaying("나의 죽음을 적들에게 알리지 말라", "이순신 장군");
        addWiseSaying("삶이 있는 한 희망은 있다.", "키케로");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionAdd();
            } else if (cmd.equals("목록")){
                actionList();
            }
        }
        scanner.close();
    }

    //데이터 저장 방식이 배열에서 리스트로 바뀌어도 actionAdd에선 사용자에게 정보를 받는 역할만 하고
    //이 함수에서 명언을 저장하는 방법을 구현하면 이 코드만 유지보수 하면 됨.
    WiseSaying addWiseSaying(String content, String author) {
        int id = ++lastId; //번호는 자동으로 매겨지는 것임. 매개변수로 전달받을 필요 없음.

        WiseSaying wiseSaying = new WiseSaying(id, content, author);

        wiseSayings[wiseSayingsSize] = wiseSaying;
        wiseSayingsSize++;

        return wiseSaying; //'n'번 명언이 등록되었음을 출력하려면 id가 리턴되어야 하는데 이 함수에서 명언 wiseSaying을 생성하니깐 명언을 리턴하면 id를 조회할 수 있음.
    }
    void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine(); //scanner, lastId, wiseSayings, wiseSayingsSize는 run함수와 공유해야 함. 따라서 run()과 actionAdd()바깥으로 빼낸다.
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        WiseSaying wiseSaying = addWiseSaying(content, author); //명언을 생성한 뒤 리모컨을 저장해서 리모컨이 가리키는 id를 조회 가능함.

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.id));
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

