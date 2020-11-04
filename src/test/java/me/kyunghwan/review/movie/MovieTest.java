package me.kyunghwan.review.movie;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class MovieTest {

    @DisplayName("Movie 객체 생성 테스트")
    @Test
    void beanTest() {
        String code = "45290";
        String name = "인터스텔라";
        int runningTime = 169;
        LocalDate localDate = LocalDate.of(2014, 11, 6);
        String imageUrl = "imageUrl";

        Movie movie = Movie.builder()
                .code(code)
                .name(name)
                .runningTime(runningTime)
                .openingDate(localDate)
                .imageUrl(imageUrl)
                .build();

        assertThat(movie.getCode()).isEqualTo(code);
        assertThat(movie.getName()).isEqualTo(name);
        assertThat(movie.getRunningTime()).isEqualTo(runningTime);
        assertThat(movie.getOpeningDate()).isEqualTo(localDate);
        assertThat(movie.getImageUrl()).isEqualTo(imageUrl);
        assertThat(movie.getMovieGenres()).isEmpty();
    }

}