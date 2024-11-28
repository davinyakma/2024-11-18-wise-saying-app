package com.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int lastId;
    private final List<WiseSaying> wiseSayings;

    public App() {
        scanner = new Scanner(System.in);
        lastId = 0;
        wiseSayings = new ArrayList<>(); //배열은 크기가 유한해서 크기 지정하는 것이 번거로운데 리스트는 크기 지정을 하지 않아도 됨.
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        addWiseSaying("나의 죽음을 적들에게 알리지 말라", "이순신 장군");
        addWiseSaying("삶이 있는 한 희망은 있다.", "키케로");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionAdd();
            } else if (cmd.equals("목록")) {
                actionList();
            }
        }
        scanner.close();
    }

    private WiseSaying addWiseSaying(String content, String author) {
        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        System.out.println("wiseSayings = " + wiseSayings); //배열은 wiseSayings하면 주소값이 출력되어서 Arrays.toString을 사용했는데
        //List는 wiseSayings의 모든 명언 내용을 다 출력해준다.

        return wiseSaying;
    }

    private void actionAdd() {
        System.out.print("명언 : ");
        String content = scanner.nextLine();
        System.out.print("작가 : ");
        String author = scanner.nextLine();
        WiseSaying wiseSaying = addWiseSaying(content, author);

        System.out.println("%d번 명언이 등록되었습니다.".formatted(wiseSaying.getId()));
    }

    private void actionList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying wiseSaying : wiseSayings) { //리스트는 자동으로 크기 조절이 가능해서 wiseSaying이 null인 경우가 없다. wiseSayings의 size에 맞게끔만 loop 돈다.
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }
}
