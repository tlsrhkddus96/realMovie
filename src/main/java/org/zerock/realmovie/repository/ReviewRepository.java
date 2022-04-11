package org.zerock.realmovie.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.realmovie.entity.Member;
import org.zerock.realmovie.entity.Movie;
import org.zerock.realmovie.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
    /*
       @EntityGraph를 통해 Review 객체를 가져올 때 Member객체를 로딩
       엔티티의 특정한 속성을 같이 로딩하도록 표현하는 어노테이션
       보통 연관관계의 FATCH 속성값은 Lazy이지만 특정 기능을 수행할떄만 Eager로딩을 하도록 지정
     */
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    //특정 영화의 모든 리뷰와 회원의 닉네임
    List<Review> findByMovie(Movie movie);

    @Modifying
    @Query("delete from Review mr where mr.member= :member")
    void deleteByMember(Member member);

}
