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

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if(cmd.equals("종료")){ // == 대신에 .equals를 써야 함.
                break; //while문을 빠져나갈 break; 문이 없다면 while문 다음의 코드가 오류가 남.
            }
        }
        scanner.close();
    }
}

