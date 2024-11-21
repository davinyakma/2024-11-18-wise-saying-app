package com.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}

//try-catch써야 하는 이유: NumberFormatException 발생. 삭제나 수정에 ?id=뒤에 숫자 말고 다른걸 작성하면 프로그램 종료됨.
class App {
    public void run() {
        //입력을 받음. 스캐너는 한번만 생성()
        Scanner scanner = new Scanner(System.in);
        List<WiseSaying> wss = new ArrayList<>();
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
            } else if (cmd.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = wss.size() - 1; i >= 0; i--) { //최신순으로 출력되게
                    WiseSaying ws = wss.get(i);
                    System.out.println(ws.id + " / " + ws.author + " / " + ws.content);
                }
            } else if (cmd.startsWith("삭제?id=")) {
                try {
                    int id = Integer.parseInt(cmd.split("=")[1]);
                    boolean found = false;

                    for (int i = 0; i < wss.size(); i++) {
                        if (wss.get(i).id == id) {
                            wss.remove(i);
                            System.out.println(id + "번 명언이 삭제되었습니다.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 명령 형식입니다. 예: 삭제?id=1");
                }
            } else if (cmd.startsWith("수정?id=")) {
                try {
                    int id = Integer.parseInt(cmd.split("=")[1]);
                    boolean found = false;

                    for (WiseSaying ws : wss) {
                        if (ws.id == id) {
                            System.out.println("명언(기존): " + ws.content);
                            System.out.print("명언: ");
                            String newContent = scanner.nextLine();
                            System.out.println("작가(기존): " + ws.author);
                            System.out.print("작가: ");
                            String newAuthor = scanner.nextLine();

                            ws.content = newContent.isEmpty() ? ws.content : newContent;
                            ws.author = newAuthor.isEmpty() ? ws.author : newAuthor;

                            System.out.println(id + "번 명언이 수정되었습니다.");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println(id + "번 명언은 존재하지 않습니다.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 명령 형식입니다. 예: 수정?id=2");
                }
            } else {
                System.out.println("알 수 없는 명령입니다.");
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