package com.programmers;

import java.util.Scanner;

public class Wise_Saying {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {
    public void run() {
        System.out.println("== 명언 앱 ==");

        //입력을 받음. 스캐너 설정 ()
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            //.formatted(cmd)는 +cmd 와 같은 표현임
            //System.out.println("입력된 명령어 : %s".formatted(cmd));

            if (cmd.equals("종료")) {
                break;
            }
        }

        scanner.close();
    }
}

