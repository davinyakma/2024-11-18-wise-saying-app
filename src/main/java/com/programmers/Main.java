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

        //생성
        Scanner scanner = new Scanner(System.in); //System.in: 키보드, Scanner: 모니터, 키보드 감시자(전달자)

        System.out.print("명령) ");
        String cmd = scanner.nextLine(); //enter가 입력될 때까지의 문장 전체를 입력받음.

        System.out.println("입력된 명령어 : %s".formatted(cmd)); //.formatted(cmd): cmd의 값을 %s에 넣어준다.
        scanner.close();
    }
}

