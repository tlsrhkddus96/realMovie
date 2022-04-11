package org.zerock.realmovie.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.realmovie.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    //페이지 처리되는 영화별 평균 점수/리뷰 개수
    @Query("select m, mi, avg(coalesce(r.grade,0)), count (distinct r) from Movie m" +
            " left outer join MovieImage mi on mi.movie = m " +
            " left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);


    //특정 영화의 모든 이미지와 평균 평점/리뷰 개수
    @Query("select m,mi,avg(coalesce(r.grade,0)), count (r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno =:mno group by mi")
    List<Object[]> getMovieWithAll(Long mno);



}
