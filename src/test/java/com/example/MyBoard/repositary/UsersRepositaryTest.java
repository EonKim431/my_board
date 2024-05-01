package com.example.MyBoard.repositary;

import com.example.MyBoard.constant.Gender;
import com.example.MyBoard.entity.Users;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositaryTest {
    @Autowired
    UsersRepositary repository;

    @Test @DisplayName("Users 테이블 입력 테스트")
    void inputUsers(){
        Users users = Users.builder()
                .name("홍길동")
                .email("test@test.com")
                .gender(Gender.Male)
                .likeColor("Red")
                .build();
        repository.save(users);
    }
    @Test @DisplayName("Users 테이블 수정 테스트")
    void updateUsers(){
        Users users = Users.builder()
                .id(1L)
                .name("홍길순")
                .email("test@test.com")
                .gender(Gender.Female)
                .likeColor("Yellow")
                .build();
        repository.save(users);
    }
    @Test
    void findByName(){
        repository.findByName("Tabatha").forEach(users -> System.out.println(users));
    }
    @Test
    void findTop3ByLikeColorOrderByDesc(){
        String color = "Pink";
        repository.findTop3ByLikeColorOrderByLikeColorDesc(color).forEach(users -> System.out.println(users));
    }
    @Test
    void findByGenderAndLikeColor(){
        String color = "Pink";
        repository.findByGenderAndLikeColor(Gender.Male,color)
                .forEach(users -> System.out.println(users));
    }
    @Test
    void findByCreatedAtAfter(){
        repository.findByCreatedAtAfter(LocalDateTime.now().minusDays(10L))
                .forEach(users -> System.out.println(users));
    }
    @Test
    void like(){
        repository.findByNameLike("%n%")
                .forEach(users -> System.out.println(users));
    }
    @Test
    void  startWith(){
        repository.findByNameStartingWith("A")
                .forEach(users -> System.out.println(users));
    }
    @Test
    void top10col(){
        repository.findTop10ByLikeColorOrderByGenderDescCreatedAtAsc("Orange")
                .forEach(users -> System.out.println(users));
    }
    @Test @DisplayName("findByLikeColor 테스트")
    void findByLikeColorTest(){
        repository.findByLikeColor("Orange", Sort.by(Sort.Order.desc("gender"), Sort.Order.asc("createdAt")))
                .forEach(users -> System.out.println(users));
    }
    @Test @DisplayName("findByLikeColorIn 테스트") void findByLikeColorInTest(){
        repository.findByLikeColorIn(Lists.newArrayList("Red", "Orange"))
                .forEach(users -> System.out.println(users));
    }
    @Test
    void findByUpdatedAtIsNotNull(){
        System.out.println("--- InNotNull Count : " +
            repository.findByUpdatedAtIsNotNull().stream().count());
    }

    //전체 페이징
    @Test
    void pagingTest(){
        System.out.println("페이지 = 0,페이지당 리스트 수 : 5");
        repository.findAll(
                PageRequest.of(0,5,Sort.by(Sort.Order.desc("id"))))
                .getContent()
                .forEach(users -> System.out.println(users));
        System.out.println("페이지 = 1,페이지당 리스트 수 : 5");
        repository.findAll(
                PageRequest.of(1,5,Sort.by(Sort.Order.desc("id"))))
                .getContent()
                .forEach(users -> System.out.println(users));
        System.out.println("페이지 = 2,페이지당 리스트 수 : 5");
        repository.findAll(
                PageRequest.of(2,5,Sort.by(Sort.Order.desc("id"))))
                .getContent()
                .forEach(users -> System.out.println(users));

    }
    @Test
    void pagingTest2(){
        Pageable pageable = PageRequest.of(4,10);
        Page<Users> result = repository.findByIdGreaterThanEqualOrderByIdDesc(
                200L,pageable
        );
        //총 페이지 수
        System.out.println("Total Page : " + result.getTotalPages());
        //전체 데이터 개수
        System.out.println("Total Contents Count : " + result.getTotalElements());
        //현재 페이지 번호
        System.out.println("Current Page Number : " + result.getNumber());
        //다음 페이지 존재 여부
        System.out.println("Next Page? : " + result.hasNext());
        System.out.println("Previous Page? : " + result.hasPrevious());
        //시작 페이지 여부
        System.out.println("Is First Page? : " + result.isFirst());
        result.getContent().forEach(users -> System.out.println(users));
    }

    //Quiz
    //01
    @Test
    void quiz01(){
//        List<Users> result = repository.findByNameLikeAndGender("%w%",Gender.Female);
//        result.addAll(
//                repository.findByNameLikeAndGender("%m%",Gender.Female)
//        );
//        result.forEach(x-> System.out.println(x));
        repository
                .findByNameLikeOrNameLike("%w%","%m%")
                .forEach(
                        users -> {
                            if(users.getGender() == Gender.Female){
                                System.out.println(users);
                            }
                        }
                );
        repository
                .findByNameLikeAndGenderOrNameLikeAndGender("%w%", Gender.Female,"%m%",Gender.Female)
                .forEach(users -> System.out.println(users));
    }
    //02
    @Test
    void quiz02(){
//        repository.findByEmailLike("%net%")
//                .forEach(x -> System.out.println(x));
        System.out.println(repository.findByEmailLike("%net%")
                .stream().count());
    }
    //03
    @Test
    void quiz03(){
        repository.findByNameLikeAndUpdatedAtGreaterThanEqual("J%",LocalDateTime.now().minusMonths(1L))
                .forEach(x-> System.out.println(x));
    }
    //04
/*    @Test
    void quiz04(){
        repository.findAll(
                PageRequest.of(0,10,
                Sort.by(Sort.Order.desc("createdAt"))
                )
        )
                .getContent()
                .forEach(x-> System.out.println(
                        " id : " + x.getId() +
                        " name : " + x.getName() +
                        " Gender : " + x.getGender() +
                        " Created At : " + x.getCreatedAt()
                ));
    }*/

    @Test
    void quiz04(){
        repository.findTop10ByOrderByCreatedAtDesc()
                .forEach(x-> System.out.println(
                        " id : " + x.getId() +
                        " name : " + x.getName() +
                        " Gender : " + x.getGender() +
                        " Created At : " + x.getCreatedAt()
                ));
    }
    //05
    @Test
    void quiz05(){
        repository.findByGenderAndLikeColor(Gender.Male,"Red")
                .forEach(x-> System.out.println("Email Account : " + x.getEmail().substring(0,x.getEmail().indexOf("@")) + "   Email : " + x.getEmail()));
    }
    //06
    @Test
    void quiz06(){
        List<Users> result = new ArrayList<>();
//        repository.findAll().forEach(
//                x->{
//                    if (x.getCreatedAt().compareTo(x.getUpdatedAt()) > 0){
//                        result.add(x);
//                    }
//                }
//        );
        repository.findAll().forEach(
                x->{
                    if (x.getCreatedAt().isAfter(x.getUpdatedAt())){
                        result.add(x);
                    }
                }
        );
        result.forEach(x-> System.out.println(x));
    }
    //07
    @Test
    void quiz07(){
        repository.findByEmailLikeAndGenderOrderByCreatedAtDesc("%edu%",Gender.Female)
                .forEach(x-> System.out.println(x));
    }
    //08
/*    @Test
    void quiz08(){
        String color = "Pink";
        repository.findByLikeColor(color,Sort.by(Sort.Order.asc("likeColor"),Sort.Order.desc("name")))
                .forEach(x-> System.out.println(x));
    }*/
    @Test
    void quiz08(){
        String color = "Pink";
        repository.findAll(
                Sort.by(Sort.Order.asc("likeColor"),Sort.Order.desc("name"))
        ).forEach(x-> System.out.println(x));
    }
    //09
    @Test
    void quiz09(){
        repository.findByOrderByCreatedAtDesc(PageRequest.of(0,10
                        ,Sort.by(Sort.Order.desc("createdAt"))))
                .getContent()
                .forEach(x-> System.out.println(x));
    }
    //10
    @Test
    void quiz10(){
        repository.findByGenderOrderByIdDesc(Gender.Male,PageRequest.of(1,3))
                .forEach(x-> System.out.println(x));
    }
    //11
    @Test
    void quiz11(){
        repository.findByCreatedAtBetween(
                LocalDateTime.now()
                        .minusMonths(1L)
                        .with(TemporalAdjusters.firstDayOfMonth()),
                LocalDateTime.now()
                        .minusMonths(1L)
                        .with(TemporalAdjusters.lastDayOfMonth()))
                .forEach(x-> System.out.println(x));
    }
}