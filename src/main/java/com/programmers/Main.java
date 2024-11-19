package com.programmers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Quote {
    int id;
    String content;
    String author;

    public Quote(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Quote> quotes = new ArrayList<>();
        int count = 0;

        System.out.println("== 명언 앱 ==\n");

        while (true) {
            System.out.print("명령) ");
            String command = scan.nextLine();

            if (command.equals("종료")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언: ");
                String content = scan.nextLine();
                System.out.print("작가: ");
                String author = scan.nextLine();

                count++;
                quotes.add(new Quote(count, content, author));
                System.out.println(count + "번 명언이 등록되었습니다.");
            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = quotes.size() - 1; i >= 0; i--) { // 최신순 출력
                    Quote quote = quotes.get(i);
                    System.out.println(quote.id + " / " + quote.author + " / " + quote.content);
                }
            } else if (command.startsWith("삭제?id=")) {
                try {
                    int id = Integer.parseInt(command.split("=")[1]);
                    boolean found = false;

                    for (int i = 0; i < quotes.size(); i++) {
                        if (quotes.get(i).id == id) {
                            quotes.remove(i);
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
            } else if (command.startsWith("수정?id=")) {
                try {
                    int id = Integer.parseInt(command.split("=")[1]);
                    boolean found = false;

                    for (Quote quote : quotes) {
                        if (quote.id == id) {
                            System.out.println("명언(기존): " + quote.content);
                            System.out.print("명언: ");
                            String newContent = scan.nextLine();
                            System.out.println("작가(기존): " + quote.author);
                            System.out.print("작가: ");
                            String newAuthor = scan.nextLine();

                            quote.content = newContent.isEmpty() ? quote.content : newContent;
                            quote.author = newAuthor.isEmpty() ? quote.author : newAuthor;

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

        scan.close();
    }
}
