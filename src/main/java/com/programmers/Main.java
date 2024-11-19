package com.programmers;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

class App {
    public void run() {
        //입력을 받음. 스캐너는 한번만 생성()
        Scanner scanner = new Scanner(System.in);
        ArrayList<WiseSaying> wss = new ArrayList<>();
        int count = 0;

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();
            //.formatted(cmd)는 +cmd 와 같은 표현임
            //System.out.println("입력된 명령어 : %s".formatted(cmd));

            if (cmd.equals("종료")) break;
            else if (cmd.equals("등록")) {
                System.out.print("명언 : ");
                String content = scanner.nextLine();

                System.out.print("작가 : ");
                String author = scanner.nextLine();

                count++;
                wss.add(new WiseSaying(count, content, author));
                System.out.println(count + "번 명언이 등록되었습니다.");
            }
            else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wss.size() -1; i >= 0; i--) { //최신순으로 출력되게
                    WiseSaying ws = wss.get(i);
                    System.out.println(ws.id + " / " + ws.author + " / " + ws.content);
                }
            }
            else if (cmd.startsWith("삭제?id=")) {
                int id = Integer.parseInt(cmd.split("=")[1]);

                for (int i = 0; i < wss.size(); i++) {
                    if (wss.get(i).id == id) {
                        wss.remove(i);
                        System.out.println(id + "번 명언이 삭제되었습니다.");
                        break;
                    }
                }
            }
        }
        scanner.close();
    }
}

class WiseSaying{
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}