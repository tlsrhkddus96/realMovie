package org.zerock.realmovie.service;

import org.zerock.realmovie.dto.MovieDTO;
import org.zerock.realmovie.dto.MovieImageDTO;
import org.zerock.realmovie.entity.Movie;
import org.zerock.realmovie.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);



    //Map타입으로 Movie 객체와 MovieImage 객체의리스트 처리
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();

        //MovieImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0){

            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO -> {

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;

            }).collect(Collectors.toList());

            entityMap.put("imgList", movieImageList);

        }

        return entityMap;

    }

}
