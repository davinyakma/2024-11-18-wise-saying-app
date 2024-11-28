package com.programmers.domain.wiseSaying.repository;

import com.programmers.domain.wiseSaying.entity.WiseSaying;

import java.util.List;
import java.util.Optional;

public abstract interface WiseSayingRepository { //추상메소드가 하나라도 있다면 클래스는 추상메소드여야 함.
    // 명언 레포지토리는 5가지 기능이 있다는 약속을 함. 클래스 안의 모든 메소드가 추상메소드라면 인터페이스!
    void add(WiseSaying wiseSaying);

    List<WiseSaying> findAll() ;

    boolean removeById(int id);

    Optional<WiseSaying> findById(int id);

    void modify(WiseSaying wiseSaying);
}