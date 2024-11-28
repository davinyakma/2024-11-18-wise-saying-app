package com.programmers;

import com.programmers.domain.wiseSaying.controller.SystemController;
import com.programmers.domain.wiseSaying.controller.WiseSayingController;

import java.util.Scanner;

public class App {
    private final Scanner scanner;
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;

    public App() {
        scanner = new Scanner(System.in);
        wiseSayingController = new WiseSayingController(scanner);
        systemController = new SystemController();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        wiseSayingController.makeSampleData();

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                systemController.actionExit();
                break;
            } else if (cmd.equals("등록")) {
                wiseSayingController.actionAdd();
            } else if (cmd.equals("목록")) {
                wiseSayingController.actionList();
            } else if (cmd.startsWith("삭제")) {
                wiseSayingController.actionDelete(cmd);
            } else if (cmd.startsWith("수정")) {
                wiseSayingController.actionModify(cmd);
            }
        }

        scanner.close();
    }
}
