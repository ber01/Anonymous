package me.kyunghwan.review.movie.dto;

import lombok.Getter;
import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.Movie;
import me.kyunghwan.review.moviegenre.MovieGenre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MovieResponseDto {

    private Long idx;

    private String code;

    private String name;

    private Integer runningTime;

    private LocalDate openingDate;

    private String imageUrl;

    private List<String> genres = new ArrayList<>();

    public MovieResponseDto(Movie movie) {
        this.idx = movie.getIdx();
        this.code = movie.getCode();
        this.name = movie.getName();
        this.runningTime = movie.getRunningTime();
        this.openingDate = movie.getOpeningDate();
        this.imageUrl = movie.getImageUrl();
        for (MovieGenre movieGenre : movie.getMovieGenres()) {
            Genre genre = movieGenre.getGenre();
            genres.add(genre.getName());
        }
    }
}
