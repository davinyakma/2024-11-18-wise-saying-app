package com.programmers.domain.wiseSaying.service;

import com.programmers.domain.wiseSaying.entity.WiseSaying;
import com.programmers.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService() {
        this.wiseSayingRepository = new WiseSayingRepository();
    }

    public WiseSaying add(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(0, content, author);

        wiseSayingRepository.add(wiseSaying);

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.wiseSayings;
    }

    public boolean removeById(int id) {
        return wiseSayingRepository.removeById(id);
    }

    public Optional<WiseSaying> findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content); //요리사가 정보를 가져오는 것 (return값이 필요한 것)은 레포지토리가 하는 일인데 단순히 정보를 저장하는것은 요리법에 해당하므로 service로직임.
        wiseSaying.setAuthor(author);

        wiseSayingRepository.modify(wiseSaying); //메모리에 저장되는지 어디에 저장되는지 모를때는 modify를 추가적으로 작성해야 함. 확실히 repository에 저장하는 과정.
    }
}
