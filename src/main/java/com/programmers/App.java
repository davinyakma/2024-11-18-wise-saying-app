package com.programmers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class App {
    private static final String DB_PATH = "db/wiseSaying";
    private static final String LAST_ID_FILE = DB_PATH + "/lastId.txt";
    private static final String DATA_FILE = DB_PATH + "/data.json"; //빌드 파일 경로

    public void run() {
        // Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        // 프로그램 시작 전 폴더와 파일 초기화
        initDB();

        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = scanner.nextLine();

            if (cmd.equals("종료")) {
                break;
            } else if (cmd.equals("등록")) {
                registerWiseSaying(scanner);
            } else if (cmd.equals("목록")) {
                printAllWiseSayings();
            } else if (cmd.startsWith("삭제?id=")) {
                try {
                    int id = Integer.parseInt(cmd.split("=")[1].trim());
                    deleteWiseSaying(id);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 명령 형식입니다. 예: 삭제?id=1");
                }
            } else if (cmd.startsWith("수정?id=")) {
                try {
                    int id = Integer.parseInt(cmd.split("=")[1].trim());
                    modifyWiseSaying(scanner, id);
                } catch (NumberFormatException e) {
                    System.out.println("잘못된 명령 형식입니다. 예: 수정?id=1");
                }
            } else if(cmd.equals("빌드")){
                buildDataFile();
            }
            else {
                System.out.println("알 수 없는 명령입니다.");
            }
        }

        scanner.close();
    }

    private void initDB() {
        File dbFolder = new File(DB_PATH);
        if (!dbFolder.exists()) {
            dbFolder.mkdirs();
        }

        File lastIdFile = new File(LAST_ID_FILE);
        if (!lastIdFile.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(lastIdFile))) {
                writer.write("0");
            } catch (IOException e) {
                System.out.println("초기화 중 오류 발생: " + e.getMessage());
            }
        }
    }

    private int getLastId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_ID_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            return 0;
        }
    }

    private void updateLastId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_ID_FILE))) {
            writer.write(String.valueOf(id));
        } catch (IOException e) {
            System.out.println("lastId 업데이트 중 오류 발생: " + e.getMessage());
        }
    }

    private void registerWiseSaying(Scanner scanner) {
        System.out.print("명언: ");
        String content = scanner.nextLine();

        System.out.print("작가: ");
        String author = scanner.nextLine();

        int newId = getLastId() + 1;
        WiseSaying wiseSaying = new WiseSaying(newId, content, author);
        saveWiseSayingToFile(wiseSaying);

        updateLastId(newId);
        System.out.println(newId + "번 명언이 등록되었습니다.");
    }

    private void saveWiseSayingToFile(WiseSaying wiseSaying) {
        String filePath = DB_PATH + "/" + wiseSaying.id + ".json";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(wiseSaying.toJson());
        } catch (IOException e) {
            System.out.println("명언 저장 중 오류 발생: " + e.getMessage());
        }
    }

    private void printAllWiseSayings() {
        File folder = new File(DB_PATH);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null || files.length == 0) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        List<WiseSaying> wiseSayings = new ArrayList<>();
        for (File file : files) {
            wiseSayings.add(loadWiseSayingFromFile(file));
        }

        wiseSayings.sort((a, b) -> b.id - a.id);
        for (WiseSaying wiseSaying : wiseSayings) {
            System.out.printf("%d / %s / %s%n", wiseSaying.id, wiseSaying.author, wiseSaying.content);
        }
    }

    private WiseSaying loadWiseSayingFromFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return WiseSaying.fromJson(json.toString());
        } catch (IOException e) {
            System.out.println("명언 읽기 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    private void deleteWiseSaying(int id) {
        File file = new File(DB_PATH + "/" + id + ".json");
        if (file.exists() && file.delete()) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        } else {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
        }
    }

    private void modifyWiseSaying(Scanner scanner, int id) {
        File file = new File(DB_PATH + "/" + id + ".json");
        if (!file.exists()) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        WiseSaying wiseSaying = loadWiseSayingFromFile(file);

        System.out.println("명언(기존): " + wiseSaying.content);
        System.out.print("명언: ");
        String newContent = scanner.nextLine();

        System.out.println("작가(기존): " + wiseSaying.author);
        System.out.print("작가: ");
        String newAuthor = scanner.nextLine();

        wiseSaying.content = newContent.isEmpty() ? wiseSaying.content : newContent;
        wiseSaying.author = newAuthor.isEmpty() ? wiseSaying.author : newAuthor;

        saveWiseSayingToFile(wiseSaying);
        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    private void buildDataFile() {
        File folder = new File(DB_PATH);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));

        if (files == null || files.length == 0) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }

        List<WiseSaying> wiseSayings = new ArrayList<>();
        for (File file : files) {
            wiseSayings.add(loadWiseSayingFromFile(file));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            writer.write("[");
            for (int i = 0; i < wiseSayings.size(); i++) {
                writer.write(wiseSayings.get(i).toJson());
                if (i < wiseSayings.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        } catch (IOException e) {
            System.out.println("data.json 파일 생성 중 오류 발생: " + e.getMessage());
        }
    }
}
