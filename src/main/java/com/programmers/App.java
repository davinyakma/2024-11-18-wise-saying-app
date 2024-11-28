package com.programmers;

import com.programmers.domain.wiseSaying.controller.WiseSayingController;
import com.programmers.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private int lastId;
    private final List<WiseSaying> wiseSayings;
    private final WiseSayingController wiseSayingController;

    public App() {
        scanner = new Scanner(System.in);
        lastId = 0;
        wiseSayings = new ArrayList<>();
        wiseSayingController = new WiseSayingController();
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
                wiseSayingController.actionList(wiseSayings);
            } else if (cmd.startsWith("삭제")) {
                String idStr = cmd.substring(6);
                int id = Integer.parseInt(idStr);

                actionDelete(id);
            } else if (cmd.startsWith("수정")) {
                String idStr = cmd.substring(6);
                int id = Integer.parseInt(idStr);

                actionModify(id);
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

    private void actionDelete(int id) {
        boolean removed = wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);

        if (removed) System.out.println("%d번 명언을 삭제했습니다.".formatted(id));
        else System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
    }

    private void actionModify(int id) {
        WiseSaying foundWiseSaying = null;

        for(WiseSaying wiseSaying : wiseSayings){
            if(wiseSaying.getId() == id) {
                foundWiseSaying = wiseSaying;
                break;
            }
        }

        if ( foundWiseSaying == null ) {
            System.out.println("%d번 명언은 존재하지 않습니다.".formatted(id));
            return;
        }

        System.out.println("명언(기존) : %s" .formatted(foundWiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = scanner.nextLine();

        System.out.println("작가(기존) : %s" .formatted(foundWiseSaying.getAuthor()));
        System.out.print("작가 : ");
        String author = scanner.nextLine();

        foundWiseSaying.setContent(content);
        foundWiseSaying.setAuthor(author);

        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }
}
