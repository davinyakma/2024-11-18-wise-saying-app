package com.programmers.domain.wiseSaying.repository;

import com.programmers.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WiseSayingRepository {
    public final List<WiseSaying> wiseSayings;
    private int lastId;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
        this.lastId = 0;
    }

    public void add(WiseSaying wiseSaying) {
        wiseSaying.setId(++lastId); //id는 service로직에서 정해주는 것이 아니라 레포지토리에서 정해준다. 따라서 setId를 통해
        // WiseSaying wiseSaying = new WiseSaying(0, content, author); 처음을 0으로 지정해놓고 레포지토리 로직에서 ++을 한다.
        wiseSayings.add(wiseSaying);
    }

    public List<WiseSaying> findAll() {
        return wiseSayings;
    }

    public boolean removeById(int id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    public void modify(WiseSaying wiseSaying) {
        //현재는 메모리에 저장되기 때문에 여기서 딱히 할일이 없다.
    }
}