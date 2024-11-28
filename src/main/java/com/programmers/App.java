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
        wiseSayings = new ArrayList<>();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        makeSampleData();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                actionAdd();
            } else if (cmd.equals("목록")) {
                actionList();
            } else if (cmd.startsWith("삭제")) { //cmd.equals("")를 하면 명령어와 같은 문자열만 인식 가능함. startsWith으로 '삭제?id='를 포함한 명령어를 인식하도록 수정
                String idStr = cmd.substring(6); //'='다음부터 추출한다(id추출 가능)
                int id = Integer.parseInt(idStr); //String타입을 int로 변환. '1' -> 1(정수)
                actionDelete(id); //삭제 명령으로 입력한 id에 해당한 명언 삭제
            }
        }
        scanner.close();
    }

    private void makeSampleData() {
        addWiseSaying("나의 죽음을 적들에게 알리지 말라", "이순신 장군");
        addWiseSaying("삶이 있는 한 희망은 있다.", "키케로");
    }

    private WiseSaying addWiseSaying(String content, String author) {
        int id = ++lastId;

        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayings.add(wiseSaying);

        return wiseSaying;
    }

    //액션 함수들
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

        for (WiseSaying wiseSaying : wiseSayings.reversed()) {
            System.out.println("%d / %s / %s".formatted(wiseSaying.getId(), wiseSaying.getAuthor(), wiseSaying.getContent()));
        }
    }

    private void actionDelete(int id) {
        boolean removed = wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id); // removeIf는 boolean값을 리턴함.

        if (removed) System.out.println("%d번 명언을 삭제했습니다.".formatted(id)); //removeIf가 true를 리턴했다면 실행
    }
}
