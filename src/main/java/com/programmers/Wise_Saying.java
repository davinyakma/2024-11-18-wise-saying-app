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

        Scanner scanner = new Scanner(System.in);

        System.out.print("명령) ");
        String cmd = scanner.nextLine();

        System.out.println("입력된 명령어 : %s".formatted(cmd));

        scanner.close();
    }
}

