package me.kyunghwan.review.movie.dto;

import lombok.*;
import me.kyunghwan.review.movie.Movie;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MovieInfo {

    private String code;

    private String name;

    private Integer runningTime;

    private LocalDate openingDate;

    private String imageUrl;

    private List<Long> movieGenres;

    public Movie toEntity() {
        return Movie.builder()
                .code(this.code)
                .name(this.name)
                .runningTime(this.runningTime)
                .openingDate(this.openingDate)
                .imageUrl(this.imageUrl)
                .build();
    }

}
