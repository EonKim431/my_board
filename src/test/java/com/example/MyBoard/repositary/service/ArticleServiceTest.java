package com.example.MyBoard.repositary.service;

import com.example.MyBoard.dto.ArticleDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest @Transactional @TestPropertySource(locations = "classpath:application-test.properties")
class ArticleServiceTest {
    @Autowired
    ArticleService service;

    @Test @DisplayName("전체데이터_읽기")
    void findAll() {
        //given
        int totalCount = 3;
        //when
        List<ArticleDto> lists = service.findAll();
        int actualCount = lists.size();
        //then
        assertThat(actualCount).isEqualTo(totalCount);
    }

    @Test
    void add() {
        //given
        ArticleDto expectDto = ArticleDto.builder().id(4L).title("라라라").content("444").build();
        //when
        ArticleDto actualDto = ArticleDto.builder().title("라라라").content("444").build();
        service.add(actualDto);
        //then
        assertThat(actualDto.getContent()).isEqualTo(expectDto.getContent());
        assertThat(actualDto.getTitle()).isEqualTo(expectDto.getTitle());
    }

    @Test @DisplayName("단건자료검색_테스트")
    void findById() {
        //given
        ArticleDto expectDto = ArticleDto.builder().id(2L).title("나나나").content("2222").build();
        //when
        ArticleDto actualDto = service.findById(2L);
        //then
        assertThat(actualDto.toString()).isEqualTo(expectDto.toString());
    }

    @Test
    void update() {
        //given
        ArticleDto expectDto = ArticleDto.builder().id(2L).title("나나나나").content("22222").build();
        //when
        service.update(expectDto);
        //then
        ArticleDto actualDto = service.findById(2L);
        assertThat(actualDto.toString()).isEqualTo(expectDto.toString());
    }

    @Test
    void deleteById() {
        //given
        Long id = 3L;
        //when
        service.deleteById(id);
        //then
        ArticleDto dto = service.findById(id);
        assertThat(dto).isEqualTo(null);
    }
}