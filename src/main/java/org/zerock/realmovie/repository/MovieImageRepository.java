package org.zerock.realmovie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.realmovie.entity.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {
}
