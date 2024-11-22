package com.programmers;

class WiseSaying {
    int id;
    String content;
    String author;

    public WiseSaying(int id, String content, String author) {
        this.id = id;
        this.content = content;
        this.author = author;
    }

    public String toJson() {
        return String.format("{\"id\":%d,\"content\":\"%s\",\"author\":\"%s\"}", id, content, author);
    }

    //WiseSaying.fromJson 메서드에서 json 문자열을 파싱할 때 발생했음.
    // " 1"과 같은 문자열에 공백이 포함된 경우, Integer.parseInt가 이를 숫자로 변환하지 못해
    // NumberFormatException이 발생한다.
    // 따라서 json 문자열을 다룰 때 trim() 메서드를 사용하여 양쪽 공백을 제거.
    public static WiseSaying fromJson(String json) {
        String[] parts = json.replace("{", "").replace("}", "").replace("\"", "").split(",");
        int id = Integer.parseInt(parts[0].split(":")[1].trim()); // 공백 제거
        String content = parts[1].split(":")[1].trim(); // 공백 제거
        String author = parts[2].split(":")[1].trim(); // 공백 제거
        return new WiseSaying(id, content, author);
    }
}
