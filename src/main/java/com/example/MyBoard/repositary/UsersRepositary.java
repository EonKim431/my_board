package com.example.MyBoard.repositary;

import com.example.MyBoard.constant.Gender;
import com.example.MyBoard.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsersRepositary extends JpaRepository<Users,Long> {
    //이름으로 검색
    List<Users> findByName(String name);

    //pink색상 데이터 중 상위 3개 데이터만 가져오기
    List<Users> findTop3ByLikeColorOrderByLikeColorDesc(String likeColor);

    //gender와 color로 테이블 검색
    List<Users> findByGenderAndLikeColor(Gender gender,String color);

    //범위로 검색(After,Before) -- 날짜,시간 데이터 한정
    List<Users> findByCreatedAtAfter(LocalDateTime searchDate);

    List<Users> findByNameLike(String name);

    List<Users> findByNameStartingWith(String name);

    List<Users> findTop10ByLikeColorOrderByGenderDescCreatedAtAsc(String color);

    List<Users> findByLikeColorIn(List<String> color);

    List<Users> findByUpdatedAtIsNotNull();

    List<Users> findByLikeColor(String color, Sort sort);

    //페이징 처리
    //주어진 아이디보다 큰 데이터를 내림차순으로 검색한 다음 페이징 처리
    // 조건 (id=200,5번째page,한페이지당10개씩)
    Page<Users> findByIdGreaterThanEqualOrderByIdDesc(Long id, Pageable paging);

    //quiz
    //01
    List<Users> findByNameLikeAndGender(String name,Gender gender);
    List<Users> findByNameLikeOrNameLike(String str1,String str2);
    List<Users> findByNameLikeAndGenderOrNameLikeAndGender(String str1,Gender gender1,String str2 , Gender gender2);

    //02
    List<Users> findByEmailLike(String email);
    //03
    List<Users> findByNameLikeAndUpdatedAtGreaterThanEqual(String name,LocalDateTime searchDate);
    //04
    List<Users> findTop10ByOrderByCreatedAtDesc();
    //05
    List<Users> findByLikeColorAndGender(String color,Gender gender);
    //06
    List<Users> findByCreatedAtGreaterThan(LocalDateTime searchDate);
    List<Users> findByUpdatedAt(LocalDateTime searchDate);
    //07
    List<Users> findByEmailLikeAndGenderOrderByCreatedAtDesc(String email,Gender gender);
    //08
//    List<Users> findBy(Sort sort);
    List<Users> findByLikeColor(String color);
    //09
    Page<Users> findByOrderByCreatedAtDesc(Pageable paging);
    //10
    Page<Users> findByGenderOrderByIdDesc(Gender gender,Pageable paging);
    //11
    List<Users> findByCreatedAtBetween(LocalDateTime startDate,LocalDateTime endDate);
}
