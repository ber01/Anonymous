package me.kyunghwan.review.moviegenre;

import me.kyunghwan.review.movie.Genre;
import me.kyunghwan.review.movie.Movie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieGenreTest {

    @DisplayName("MovieGenre 객체 생성 테스트")
    @Test
    void beanTest() {
        String code = "code";
        Movie movie = Movie.builder()
                .code(code)
                .name("name")
                .runningTime(100)
                .openingDate(LocalDate.of(2000, 01, 01))
                .imageUrl("imageUrl")
                .build();

        String name = "name";
        Genre genre = Genre.builder()
                .name(name)
                .build();

        MovieGenre movieGenre = MovieGenre.builder()
                .movie(movie)
                .genre(genre)
                .build();

        assertThat(movieGenre.getMovie().getCode()).isEqualTo(code);
        assertThat(movieGenre.getGenre().getName()).isEqualTo(name);
    }

}