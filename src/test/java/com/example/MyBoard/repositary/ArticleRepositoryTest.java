package com.example.MyBoard.repositary;

import com.example.MyBoard.entity.Article;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class ArticleRepositoryTest {
    @Autowired//자동으로 인스턴스를 만들어줌
    ArticleRepository articleRepository;

    @Test @Order(value = 4) @Transactional
    void 자료입력테스트(){
        //Given
        Article expectArticle = new Article(4L,"라라라","4444");
        //When
        Article newArticle = new Article(null,"라라라","4444");
        articleRepository.save(newArticle);
        //Then
        Article actualArticle = articleRepository.findById(4L).orElse(null);
        assertThat(actualArticle.toString()).isEqualTo(expectArticle.toString());
    }
    @Test
    void findAllTest_Clear(){
        //Given
        Article article1 = new Article(1L,"가가가","1111");
        Article article2 = new Article(2L,"나나나","2222");
        Article article3 = new Article(3L,"다다다","3333");
//        List<Article> expectList = new ArrayList<>(Arrays.asList(article1,article2,article3));
        List<Article> expectList = new ArrayList<>();
        expectList.add(article1);
        expectList.add(article2);
        expectList.add(article3);
        //When
        List<Article> resultList = articleRepository.findAll();
        //Then
        assertThat(resultList.toString()).isEqualTo(expectList.toString());
    }

    @Test
    void findAllTest_Fail(){
        //Given
        Article article1 = new Article(1L,"라라라","1111");
        Article article2 = new Article(2L,"나나나","2222");
        Article article3 = new Article(3L,"다다다","3333");
//        List<Article> expectList = new ArrayList<>(Arrays.asList(article1,article2,article3));
        List<Article> expectList = new ArrayList<>();
        expectList.add(article1);
        expectList.add(article2);
        expectList.add(article3);
        //When
        List<Article> resultList = articleRepository.findAll();
        //Then
        assertThat(resultList.toString()).isNotEqualTo(expectList.toString());
    }

    @Test @Order(value = 5)
    void 전체데이터검색_개수_Clear(){
        //Given
        int expectCount = 3;
        //When
        int actualCount = articleRepository.findAll().size();
        //Then
        assertThat(actualCount).isEqualTo(expectCount);
    }
    /*    @Test
        void 자료삭제_테스트(){
            //Given
            Long deleteId = 1L;
            //When
            articleRepository.deleteById(1L);
            //Then
            Article actualResult = articleRepository.findById(1L).orElse(null);
            assertThat(actualResult).isEqualTo(null);
        }*/
    @Test @Order(value = 1)
    void 자료삭제_테스트4L(){
        //Given
        Long deleteId = 4L;
        //When
        articleRepository.deleteById(4L);
        //Then
        Article actualResult = articleRepository.findById(4L).orElse(null);
        assertThat(actualResult).isEqualTo(null);
    }

    @Test
    void 자료수정_테스트(){
        //Given
        Article expectArticle = new Article(1L,"타이틀수정","컨텐트수정");
        //When
        articleRepository.save(expectArticle);
        Article actualArticle = articleRepository.findById(1L).orElse(null);
        //Then
        assertThat(actualArticle.toString()).isEqualTo(expectArticle.toString());
    }
}