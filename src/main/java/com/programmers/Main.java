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

                wiseSayings[wiseSayingsSize] = wiseSaying;
                wiseSayingsSize++;

                System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
            } else if (cmd.equals("목록")){
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");

                //문제점: 크기가 10인 배열에 명언을 10개 미만으로 저장했을때 나머지 wiseSaying에는 null이 들어가서 NullPointerException이 발생. 예외처리를 해줘야함.
                for(WiseSaying wiseSaying : wiseSayings) { //향상된 for문. 기존에 (int i = 0; i < wiseSayingsSize; i++)를 수정.
                    if( wiseSaying == null) break; //이전의 for문은 wiseSayingsSize로 배열의 요소들을 출력했기에 널포인터 에러처리가 필요없었음.
                    //그러나 향상된 for문에는 wiseSaying이 null이 될 수 있기에 break;를 설정해 줘야함.
                    System.out.println("%d / %s / %s".formatted(wiseSaying.id, wiseSaying.author, wiseSaying.content));
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

